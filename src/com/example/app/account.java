package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class account extends Activity implements OnClickListener{
	private Button return1;   //���ظ�����Ϣ����
	private Button exitlogin;  //�˳���¼
	private TextView mobile;   //�ֻ�����
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account);
		return1  = (Button) findViewById(R.id.return1);
		return1.setOnClickListener(this);
		mobile  = (TextView) findViewById(R.id.mobile);
		application app=application.getInstance();
		String user=(String)app.getMap().get("USER");
		mobile.setText(user);
		exitlogin  = (Button) findViewById(R.id.exitlogin);
		exitlogin.setOnClickListener(this);
	}
	@Override
	public void onClick(View v1) {
		// TODO Auto-generated method stub
		switch (v1.getId()){
		case R.id.return1:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent1 =new Intent(account.this,MessageActivity.class);
			//����
			startActivity(intent1);
			break;
		/*case R.id.exitlogin:
			// ��bnt1��ӵ����Ӧ�¼�
			ActivityCollector.finishAll();  // �������л
			Intent intent2 =new Intent(account.this,login.class);
			//����
			startActivity(intent2);
			break;*/
		}
	}
}
