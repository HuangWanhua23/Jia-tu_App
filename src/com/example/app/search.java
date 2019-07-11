package com.example.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
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
import android.widget.TextView;
import android.widget.Toast;

public class search extends Activity implements OnClickListener{
	private Button return1;
	private Button searchbutton;   //����
	private Button sjiance;  //�������
	private Button syuce;    //����Ԥ��
	private Button hot;  //�������
	private Button analyse;    //����Ԥ��
	private ImageView juzizhou;
	private ImageView split;
	private TextView m9_1;
	private TextView m10_1;
	private TextView m11_1;
	private TextView m12_1;
	private EditText search;
	private TextView text;
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
								// ���������������
								String searchtext=search.getText().toString().trim();
								String sendmsg=searchtext+"/"+"��������";
								dos.writeUTF(sendmsg);
								// ��ȡ����������������
								cContent = dis.readUTF();
								Message msg = cHandler.obtainMessage();
								msg.obj = cContent;
								//if(cContent.equals("�����ɹ�"))
								cHandler.sendMessage(msg);
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
//				case 0x02:
//					handleMessage(msg);
//					String result=(String)msg.obj;
//					text.setText(result);
//					break;
				case 0x03:
					Toast.makeText(search.this, "�Բ������޸þ�����Ϣ��", Toast.LENGTH_SHORT).show();
					break;
				}
				String result=(String)msg.obj;
				text.setText(result);
				if(result!=null)  //ֻ�дӷ��������󵽾�����Ϣ��Ż���ʾ���صĿؼ�
				{
					sjiance.setVisibility(View.VISIBLE);
					syuce.setVisibility(View.VISIBLE);
					hot.setVisibility(View.VISIBLE);
					analyse.setVisibility(View.VISIBLE);
					juzizhou.setVisibility(View.VISIBLE);
					split.setVisibility(View.VISIBLE);
					m9_1.setVisibility(View.VISIBLE);
					m10_1.setVisibility(View.VISIBLE);
					m11_1.setVisibility(View.VISIBLE);
					m12_1.setVisibility(View.VISIBLE);
				}else
				{
					sjiance.setVisibility(View.INVISIBLE);
					syuce.setVisibility(View.INVISIBLE);
					hot.setVisibility(View.INVISIBLE);
					analyse.setVisibility(View.INVISIBLE);
					juzizhou.setVisibility(View.INVISIBLE);
					split.setVisibility(View.INVISIBLE);
					m9_1.setVisibility(View.INVISIBLE);
					m10_1.setVisibility(View.INVISIBLE);
					m11_1.setVisibility(View.INVISIBLE);
					m12_1.setVisibility(View.INVISIBLE);
				}
			}
		};
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		return1 = (Button) findViewById(R.id.return1);
		return1.setOnClickListener(this);
		searchbutton = (Button) findViewById(R.id.searchbutton);  //����
		searchbutton.setOnClickListener(this);
		sjiance = (Button) findViewById(R.id.sjiance);  //�������
		sjiance.setOnClickListener(this);
		syuce = (Button) findViewById(R.id.syuce);      //����Ԥ��
		syuce.setOnClickListener(this);
		hot = (Button) findViewById(R.id.hot);  //����ͼ
		hot.setOnClickListener(this);
		analyse = (Button) findViewById(R.id.analyse);  //���ܷ���
		analyse.setOnClickListener(this);
		search = (EditText) findViewById(R.id.search);     //������
		text = (TextView) findViewById(R.id.text);     //����������ʾ
		juzizhou=(ImageView) findViewById(R.id.juzizhou);
		split=(ImageView) findViewById(R.id.split);
		m9_1 = (TextView) findViewById(R.id.m9_1);   
		m10_1 = (TextView) findViewById(R.id.m10_1); 
		m11_1 = (TextView) findViewById(R.id.m11_1); 
		m12_1 = (TextView) findViewById(R.id.m12_1); 
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.searchbutton:
			String searchtext=search.getText().toString().trim();
			if((searchtext.equals("������"))|(searchtext.equals("��´ɽ�羰��ʤ��"))|(searchtext.equals("����ʡ�����"))){
				if(searchtext.equals("������")){
					 int a=getimages("jzzt1");
					 juzizhou.setImageResource(a);
				}else if(searchtext.equals("��´ɽ�羰��ʤ��")){
					 int a=getimages("aiwanting4");
					 juzizhou.setImageResource(a);
				}else if(searchtext.equals("����ʡ�����")){
					 int a=getimages("bowuguan");
					 juzizhou.setImageResource(a);
				}
				cHandler.sendEmptyMessage(0x01);
				String result=search.getText().toString().trim();
				application app=application.getInstance();
				app.getMap().put("result0", result);
			}else{
				cHandler.sendEmptyMessage(0x03);
			}
//			sjiance.setVisibility(View.VISIBLE);
//			syuce.setVisibility(View.VISIBLE);
			break;
		case R.id.return1:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent2 =new Intent(search.this,MainActivity.class);
			//����
			startActivity(intent2);
			search.getText().clear();
			text.setText("");
			sjiance.setVisibility(View.INVISIBLE);
			syuce.setVisibility(View.INVISIBLE);
			hot.setVisibility(View.INVISIBLE);
			analyse.setVisibility(View.INVISIBLE);
			juzizhou.setVisibility(View.INVISIBLE);
			split.setVisibility(View.INVISIBLE);
			m9_1.setVisibility(View.INVISIBLE);
			m10_1.setVisibility(View.INVISIBLE);
			m11_1.setVisibility(View.INVISIBLE);
			m12_1.setVisibility(View.INVISIBLE);		
			break;
		case R.id.sjiance:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent3 =new Intent(search.this,jiance.class);
			//����
			startActivity(intent3);
			break;
		case R.id.syuce:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent4 =new Intent(search.this,yuce.class);
			//����
			startActivity(intent4);
			break;
		case R.id.analyse:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent5 =new Intent(search.this,travelbooking.class);
			//����
			startActivity(intent5);
			break;
		}
	}
	public static int getimages(String name){
	       Class drawable = R.drawable.class;
	       Field field = null;
	       try {
	           field =drawable.getField(name);
	           int images = field.getInt(field.getName());
	           return images;
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	       return 0;
	 }
}