package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class set extends Activity implements OnClickListener {
	private Button return1;   //���ظ�����Ϣ����
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set);
		return1  = (Button) findViewById(R.id.return1);
		return1.setOnClickListener(this);
	}
	@Override
	public void onClick(View v1) {
		// TODO Auto-generated method stub
		switch (v1.getId()){
		case R.id.return1:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent1 =new Intent(set.this,MessageActivity.class);
			//����
			startActivity(intent1);
			break;
		}
	}
}
