package com.example.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgetpassword1 extends Activity implements OnClickListener{
	private Button uploadpassword;  //��һ��
	private EditText setpassword;   //��������
	private EditText confirm;       //�ٴ�����
	// Socket�������ӷ�������ȡ���������
		private Socket cSocket;
		// ������server/IP��ַ(��ǰPC��IP��ַ)
		private final String ADDRESS = "134.175.66.2";
		// �������˿�
		private final int PORT = 8888;
		// ��Ϣ������߳�
		private Thread cThread;
			// ��Ϣ������
		private String cContent;
			// ������Ϣ����
		Handler cHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case 0x01:
					cThread = new Thread() {
						@Override
						public void run() {
							super.run();
							DataInputStream dis = null;
							DataOutputStream dos = null;
							try {
								cSocket = new Socket(ADDRESS, PORT);
								// �����������������Ӻ�Ż����¼���ִ��
								dis = new DataInputStream(cSocket.getInputStream());
								dos = new DataOutputStream(
										cSocket.getOutputStream());
								// �������д����
								application app=application.getInstance();
								String user=(String)app.getMap().get("userzh1");
								String setpassword1=setpassword.getText().toString().trim();
								String confirm1=confirm.getText().toString().trim();
								//�һ��������������
								String sendmsg=user+"|"+confirm1+"/"+"��������";
								dos.writeUTF(sendmsg);
								// ��ȡ����������������
								cContent = dis.readUTF();
								if(cContent.equals("���óɹ�")){
									cHandler.sendEmptyMessage(0x02);
								}
							} catch (UnknownHostException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							} finally {
								try {
									if (dis != null) {
										dis.close();
									}
									if (dos != null) {
										dos.close();
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}

					};
					cThread.start();
					break;
				case 0x02:
					Intent intent1 =new Intent(forgetpassword1.this,forgetpassword2.class);
					//����
					startActivity(intent1);
					break;
				/*case 0x03:
					Toast.makeText(forgetpassword1.this, "��������ʧ�ܣ����Ժ����ԣ���", Toast.LENGTH_SHORT).show();
					break;*/
				case 0x04:
					Toast.makeText(forgetpassword1.this, "�������룡=ȷ�����룬����������", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgetpassword1);
		uploadpassword = (Button) findViewById(R.id.uploadpassword);
		setpassword = (EditText) findViewById(R.id.setpassword);
		confirm = (EditText) findViewById(R.id.confirm);
		uploadpassword.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.uploadpassword:
			String setpassword2=setpassword.getText().toString().trim();
			String confirm2=confirm.getText().toString().trim();
			if(setpassword2.equals(confirm2)){
				cHandler.sendEmptyMessage(0x01);
			}else{
				cHandler.sendEmptyMessage(0x04);
			}
		}
	}
}
