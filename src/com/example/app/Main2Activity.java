package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main2Activity extends Activity implements OnClickListener{
	private Button btn_data; //����
	private Button btn_run;  //�г�
	private Button btn_message; //������Ϣ
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main3);
		btn_data  = (Button) findViewById(R.id.btn_data);
		btn_data.setOnClickListener(this);
		btn_run  = (Button) findViewById(R.id.btn_run);
		btn_run.setOnClickListener(this);
		btn_message  = (Button) findViewById(R.id.btn_message);
		btn_message.setOnClickListener(this);
	}
	@Override
	public void onClick(View v1) {
		// TODO Auto-generated method stub
		switch (v1.getId()){
		case R.id.btn_data:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent2 =new Intent(Main2Activity.this,MainActivity.class);
			//����
			startActivity(intent2);
			break;
		case R.id.btn_run:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent3 =new Intent(Main2Activity.this,Main3Activity.class);
			//����
			startActivity(intent3);
			break;
		case R.id.btn_message:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent4 =new Intent(Main2Activity.this,MessageActivity.class);
			//����
			startActivity(intent4);
			break;
		}
	}
}
