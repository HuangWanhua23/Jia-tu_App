package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class hotel  extends Activity implements OnClickListener {
	private Button return1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotel);
		return1 = (Button) findViewById(R.id.return1);
		return1.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.return1:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent2 =new Intent(hotel.this,MainActivity.class);
			//����
			startActivity(intent2);
			break;
		}
	}
}
