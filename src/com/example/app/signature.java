package com.example.app;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class signature extends Activity implements OnClickListener {
	String path =Environment.getExternalStorageDirectory()+ "/sign/";
	File filex = new File(path);
	File m1 = new File(path + "sign1.txt");
	private TextView oldsign;   //��ʷǩ��
	private Button btn_signchange;  //������ǩ��
	private Button btn_signreturn;  //����
	private EditText newsign;   //��ǩ��
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signature);
		if (!filex.exists()) {
			try {
				filex.mkdirs();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (!m1.exists()) {
			try {
				m1.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		oldsign = (TextView) findViewById(R.id.oldsign);
		DataInputStream in1;
		try {
			in1 = new DataInputStream(new FileInputStream(path + "m1.txt"));
			oldsign.setText(in1.readUTF());
			in1.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		newsign = (EditText) findViewById(R.id.newsign);
		btn_signchange = (Button) findViewById(R.id.btn_signchange);
		btn_signchange.setOnClickListener(this);
		btn_signreturn = (Button) findViewById(R.id.btn_signreturn);
		btn_signreturn.setOnClickListener(this);
	}
	@Override
	public void onClick(View v5) {
		// TODO Auto-generated method stub
		switch (v5.getId()){
		case R.id.btn_signchange:
			DataOutputStream out1;
			String s=newsign.getText().toString().trim();   //��ȡ�������ǩ��
			try {
				if (s.length() != 0) {
					m1.delete();
					m1.createNewFile();
				}
				out1 = new DataOutputStream(new FileOutputStream(path
						+ "m1.txt"));
				out1.writeUTF(s);
				out1.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			oldsign.setText(s);  
		case R.id.btn_signreturn:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent1 =new Intent(signature.this,MessageActivity.class);
			//����
			startActivity(intent1);
			break;
		}
	}
}
