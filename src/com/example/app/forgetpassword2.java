package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class forgetpassword2 extends Activity implements OnClickListener{
	private Button nowload;  //��һ��
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgetpassword2);
		nowload = (Button) findViewById(R.id.nowload);
		nowload.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.nowload:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent1 =new Intent(forgetpassword2.this,login.class);
			//����
			startActivity(intent1);
			break;
		}
	}
}
