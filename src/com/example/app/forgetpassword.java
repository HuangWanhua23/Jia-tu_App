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
import android.widget.ImageView;
import android.widget.Toast;

public class forgetpassword extends Activity implements OnClickListener{
	private Button btn_next1;  //��һ��
	private EditText userzh;   //�ֻ���
	private EditText ma;       //ע�����һ�
	private ImageView iv_two;  //��ȡ��֤��
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
							String userzh1=userzh.getText().toString().trim();
							String ma1=ma.getText().toString().trim();
							String sendmsg=userzh1+"|"+ma1+"/"+"�һ�����";
							dos.writeUTF(sendmsg);
							// ��ȡ����������������
							cContent = dis.readUTF();
							if(cContent.equals("�ɹ��һ����룡��")){
								cHandler.sendEmptyMessage(0x02);
							}else if(cContent.equals("����")){
								cHandler.sendEmptyMessage(0x03);
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
				// ��bnt1��ӵ����Ӧ�¼�
				Intent intent1 =new Intent(forgetpassword.this,forgetpassword1.class);
				String usernumber=userzh.getText().toString().trim();
				application app=application.getInstance();
				app.getMap().put("userzh1", usernumber);
				//����
				startActivity(intent1);
				break;
			case 0x03:
				Toast.makeText(forgetpassword.this, "����,�޷��һ�", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgetpassword);
		btn_next1 = (Button) findViewById(R.id.btn_next1);
	    userzh = (EditText) findViewById(R.id.userzh);
	    ma = (EditText) findViewById(R.id.ma);
		btn_next1.setOnClickListener(this);
		iv_two = (ImageView) findViewById(R.id.iv_two);   //��֤��
		iv_two.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.btn_next1:
			cHandler.sendEmptyMessage(0x01);
		case R.id.iv_two:
			iv_two.setImageResource(R.drawable.y2);
		}
	}
}
