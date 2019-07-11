package com.example.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class register extends Activity implements OnClickListener {
	private Button zhuce;   //ע��󷵻�
	private EditText userzc;   //ע���ֻ���
	private EditText passwordzc;  //��������
	private EditText passwordqd;  //ȷ������
	private ImageView iv_one;   //��֤���ȡ
	private EditText ma;     //������֤��
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
								// �����������������Ӻ�Ż����¼���ִ��
								cSocket = new Socket(ADDRESS, PORT);
								dis = new DataInputStream(cSocket.getInputStream());
								dos = new DataOutputStream(
										cSocket.getOutputStream());
								// �������д����
								String userzc1=userzc.getText().toString().trim();
								String passwordzc1=passwordzc.getText().toString().trim();
								String passwordqd1=passwordqd.getText().toString().trim();
								String sendmsg=userzc1+"#"+passwordzc1+"|"+passwordqd1+"/"+"����ע��";
								dos.writeUTF(sendmsg);
								// ��ȡ����������������
								cContent = dis.readUTF();
								if(cContent.equals("ע��ɹ�����")){
									cHandler.sendEmptyMessage(0x02);
								}/*else if(cContent.equals("������ע��")){
									cHandler.sendEmptyMessage(0x03);
								}*/else if(cContent.equals("���˻��Ѿ�ע�ᣡ��")){
									cHandler.sendEmptyMessage(0x04);
								}/*else if(cContent.equals("ע������!=ȷ������")){
									cHandler.sendEmptyMessage(0x05);
								}*/
								
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
					Intent intent1=new Intent(register.this,login.class);
					Toast.makeText(register.this, "ע��ɹ�", Toast.LENGTH_SHORT).show();
					startActivity(intent1);
					break;
				/*case 0x03:
					Toast.makeText(register.this, "ע��ʧ��", Toast.LENGTH_SHORT).show();
					break;*/
				case 0x04:
					Toast.makeText(register.this, "���û��Ѿ�ע����������������˺�,���룡��", Toast.LENGTH_SHORT).show();
					break;
				/*case 0x05:
					Toast.makeText(register.this, "ע������!=ȷ������,��������������", Toast.LENGTH_SHORT).show();
					break;*/
				case 0x06:
					Toast.makeText(register.this, "��������ȷ���ֻ����룡", Toast.LENGTH_SHORT).show();
					break;
				case 0x07:
					Toast.makeText(register.this, "ע������!=ȷ������,��������������", Toast.LENGTH_SHORT).show();
					break;
				case 0x08:
					Toast.makeText(register.this, "��֤���������������", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		};
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		zhuce = (Button)findViewById(R.id.zhuce);
		zhuce.setOnClickListener(this);
		userzc = (EditText) findViewById(R.id.userzc);     //�ֻ��ſ�
		/*userzc.addTextChangedListener(new TextWatcher() {
				public void onTextChanged(CharSequence s, int start, int before, int count) {	
					if (s.length()== 0)						
						return;
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < s.length(); i++) {	
						if (i != 3 && i != 8 && s.charAt(i) == ' ') {
							continue;
						}else {
							sb.append(s.charAt(i));
							if ((sb.length() == 4 || sb.length() == 9)
									&& sb.charAt(sb.length() - 1) != ' ') {
								sb.insert(sb.length() - 1, ' ');
							}
						}
					}
					if (!sb.toString().equals(s.toString())) {
						int index = start + 1;
						if (sb.charAt(start) == ' ') {
							if (before == 0) {
								index++;
							} else {
								index--;
							}
						} else {
							if (before == 1) {
								index--;
							}
						}
						userzc.setText(sb.toString());
						userzc.setSelection(index);
					}
				}

				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void beforeTextChanged(CharSequence s, int arg1,
						int arg2, int arg3) {
					// TODO Auto-generated method stub
				}
	        });*/		
		passwordzc = (EditText) findViewById(R.id.passwordzc);   //�����ע��
		passwordqd = (EditText) findViewById(R.id.passwordqd);   //�����ȷ��
		ma = (EditText) findViewById(R.id.ma);            //��֤����֤
		iv_one = (ImageView) findViewById(R.id.iv_one);   //��֤��ͼƬ��ȡ
		iv_one.setOnClickListener(this);
		Drawable drawable = getResources().getDrawable(R.drawable.b);
	        drawable .setBounds(0, 0, 95, 95);//��һ�� 0 �Ǿ���߾��룬�ڶ��� 0 �Ǿ��ϱ߾��룬40 �ֱ��ǳ���
	        userzc.setCompoundDrawables(drawable , null, null, null);//ֻ�����
	    Drawable drawable1 = getResources().getDrawable(R.drawable.i17);
	        drawable1 .setBounds(0, 0, 95, 95);//��һ�� 0 �Ǿ���߾��룬�ڶ��� 0 �Ǿ��ϱ߾��룬40 �ֱ��ǳ���
	        passwordzc.setCompoundDrawables(drawable1 , null, null, null);//ֻ�����
	    Drawable drawable2 = getResources().getDrawable(R.drawable.i17);
	        drawable2 .setBounds(0, 0, 95, 95);//��һ�� 0 �Ǿ���߾��룬�ڶ��� 0 �Ǿ��ϱ߾��룬40 �ֱ��ǳ���
	        passwordqd.setCompoundDrawables(drawable2 , null, null, null);//ֻ�����
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.zhuce:
			String userzc2=userzc.getText().toString().trim();
			String passwordzc2=passwordzc.getText().toString().trim();
			String passwordqd2=passwordqd.getText().toString().trim();
			String ma1=ma.getText().toString().trim();
			//����ֻ��Ų���11λ����
			boolean boo = isMobileNO(userzc2);
			if (boo) {
				//System.out.println("�绰������ȷ��-->" + userzc2);
				if(passwordzc2.equals(passwordqd2)){
				//System.out.println("������ȷ��-->" + passwordzc2);	
					if(ma1.equals("0408")){
						cHandler.sendEmptyMessage(0x01);
					}else {
						cHandler.sendEmptyMessage(0x08);
					}
				}else{
					cHandler.sendEmptyMessage(0x07);
				}
			}else {
				//System.out.println("�绰�������***>" + userzc2);
				cHandler.sendEmptyMessage(0x06);
			}
		case R.id.iv_one:
			iv_one.setImageResource(R.drawable.y3);
		}
	}
	public static boolean isMobileNO(String mobiles) {
		boolean flag = false;
		try {
			//�ж��Ƿ�Ϊ�ֻ��� 13********* ,15********,18*********
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,2-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
 
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
