package com.example.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends Activity implements OnClickListener {
	private CheckBox remenber;  //��ס����
	private SharedPreferences config;  //�����ļ�
	private Button btn_login;   //��½
	private TextView register;     //ע��
	private TextView forgetpassword;   //�һ�����
	private EditText user;
	private EditText password;
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
							String usernumber=user.getText().toString().trim();
							String password1=password.getText().toString().trim();
							String sendmsg=usernumber+"|"+password1+"/"+"�����¼";
							dos.writeUTF(sendmsg);
							// ��ȡ����������������
							cContent = dis.readUTF();
							if(cContent.equals("�����¼")){
								cHandler.sendEmptyMessage(0x02);
							}else if(cContent.equals("�������¼")){
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
				Intent intent1 =new Intent(login.this,MainActivity.class);
				Toast.makeText(login.this, "��½�ɹ�", Toast.LENGTH_SHORT).show();
				//����
				//application app=application.getInstance();
				//app.getMap().put("user", usernumber2);
				startActivity(intent1);
				break;
			case 0x03:
				Toast.makeText(login.this, "��½ʧ�ܣ�����δע��", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		btn_login = (Button) findViewById(R.id.btn_login);  //��¼
		btn_login.setOnClickListener(this);
		register = (TextView) findViewById(R.id.register);  //ע��
		register.setOnClickListener(this);
		forgetpassword = (TextView) findViewById(R.id.forgetpassword); //��������
		forgetpassword.setOnClickListener(this); 
		user = (EditText) findViewById(R.id.user);     //�ֻ��ſ�
		/*user.addTextChangedListener(new TextWatcher() {
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
					user.setText(sb.toString());
					user.setSelection(index);
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
		password = (EditText) findViewById(R.id.password);   //�����
		remenber = (CheckBox) findViewById(R.id.remenber);  //��ס����
		config=getSharedPreferences("config", MODE_PRIVATE);   //�ļ�����ģʽ
		//�Ƿ��ס������
		boolean ischecked=config.getBoolean("ischecked", false);
		//�����ס�ˣ���һ�δ򿪾��Զ���д��Ӧ��״̬
		if(ischecked){
			user.setText(config.getString("usernumber2", ""));
			password.setText(config.getString("password2", ""));
		}
		remenber.setChecked(ischecked);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.btn_login:
			Editor edit=config.edit();  //�õ��༭����Ȼ�������������������˺ź������Լ�ѡ�е�״̬
			String usernumber2=user.getText().toString().trim();
			String password2=password.getText().toString().trim();
			application app=application.getInstance();
			app.getMap().put("USER",usernumber2);
			boolean ischecked=remenber.isChecked();
			//��CheckBox��״̬
			edit.putBoolean("ischecked", ischecked);
			if(ischecked){
				edit.putString("usernumber2", usernumber2).putString("password2", password2);
			}else{
				edit.remove("usernumber2").remove("password2");
			}
			//�ύ�ڴ��������Ϣ������
			edit.commit();
			if((usernumber2.equals("001"))&&(password2.equals("login"))){
				cHandler.sendEmptyMessage(0x02);	
			}else{
				cHandler.sendEmptyMessage(0x01);
			}
			break;
		case R.id.register:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent2 =new Intent(login.this,register.class);
			//����
			startActivity(intent2);
			break;	
		case R.id.forgetpassword:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent3 =new Intent(login.this,forgetpassword.class);
			//����
			startActivity(intent3);
			break;
		}
	}
}
