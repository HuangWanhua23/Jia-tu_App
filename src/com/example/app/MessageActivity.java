package com.example.app;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageActivity extends Activity implements OnClickListener{
	application app=application.getInstance();   //�˺�ͬ��
	//String path1 =Environment.getExternalStorageDirectory()+ "/sign/";
	private ImageView ivHead;//ͷ����ʾ  
	private Button btn_find;
	private Button btn_data;
	private Button btn_run;
	private Button iv_head;  //����ҵ�ͷ������ҵ��˺����ý���
	private Button m2;    //����
	private Button c;    //ͷ�����
	//private TextView text_sign; //����ǩ������
	//private Button btn_sign; //����ǩ������
	//private Button bwl;  //����¼
	//private TextView now_id;  //�˺�
	//private static String path = "/sdcard/DemoHead/";//sd·��  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		btn_find = (Button) findViewById(R.id.btn_find);
		btn_find.setOnClickListener(this);
		btn_run  = (Button) findViewById(R.id.btn_run);
		btn_run.setOnClickListener(this);
		btn_data  = (Button) findViewById(R.id.btn_data);
		btn_data.setOnClickListener(this);
		iv_head  = (Button) findViewById(R.id.iv_head);
		iv_head.setOnClickListener(this);
		m2  = (Button) findViewById(R.id.m2);   
		m2.setOnClickListener(this);
		/*bwl  = (Button) findViewById(R.id.bwl);          //����¼
		bwl.setOnClickListener(this);
		tv_id  = (TextView) findViewById(R.id.tv_id);   //����ǩ��
		btn_sign  = (Button) findViewById(R.id.btn_sign);   //����ǩ��
		btn_sign.setOnClickListener(this);
		text_sign  = (TextView) findViewById(R.id.text_sign);   //����ǩ��
		DataInputStream in1;
		try {
			in1 = new DataInputStream(new FileInputStream(path1 + "m1.txt"));
			text_sign.setText(in1.readUTF());
			in1.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		c=(Button) findViewById(R.id.c);              //ͷ�����
		c.setOnClickListener(this);
		ivHead = (ImageView) findViewById(R.id.iv_head); 
		Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");//��Sd����ͷ��ת����Bitmap 
		if (bt != null) {  
	           //���������ͷ��ͼƬ�Ļ�  
	           ivHead.setImageBitmap(bt);  
	       } else {  
	           //�������û��ͷ��ͼƬ��ӷ�����ȡͷ��Ȼ�󱣴���SD���У���Demo����������ͷ�񲿷ֺ���  
	             
	       } 
		
		String a=(String)app.getMap().get("user");
		now_id.setText(a);
	}
	public void onDestroy(){
		super.onDestroy();
		app.getMap().remove("user");*/
	}
	@Override
	public void onClick(View v4) {
		// TODO Auto-generated method stub
		switch (v4.getId()){
		case R.id.btn_find:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent1 =new Intent(MessageActivity.this,Main2Activity.class);
			//����
			startActivity(intent1);
			break;
		case R.id.btn_run:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent2 =new Intent(MessageActivity.this,Main3Activity.class);
			//����
			startActivity(intent2);
			break;
		case R.id.btn_data:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent3 =new Intent(MessageActivity.this,MainActivity.class);
			//����
			startActivity(intent3);
			break;
		case R.id.iv_head:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent4 =new Intent(MessageActivity.this,account.class);
			//����
			startActivity(intent4);
			break;
		case R.id.m2:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent5 =new Intent(MessageActivity.this,set.class);
			//����
			startActivity(intent5);
			break;
		/*case R.id.c:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent4 =new Intent(MessageActivity.this,ImagechangeActivity1.class);
			//����
			startActivity(intent4);
			break;
		case R.id.btn_sign:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent5 =new Intent(MessageActivity.this,signature.class);
			//����
			startActivity(intent5);
			break;
		case R.id.bwl:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent6 =new Intent(MessageActivity.this,bwl.class);
			//����
			startActivity(intent6);
			break;*/
		}
	}
}