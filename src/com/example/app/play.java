package com.example.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class play  extends Activity implements OnClickListener {
	private Button return1;
	private TextView play1;  //���湥�Գ�����
	private TextView play2;
	private TextView play3;
	private TextView play4;
	private TextView play5;
	private TextView play6;
	private TextView play7;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);
		return1 = (Button) findViewById(R.id.return1);
		return1.setOnClickListener(this);
		play1 = (TextView) findViewById(R.id.play1);
		play2 = (TextView) findViewById(R.id.play2);
		play3 = (TextView) findViewById(R.id.play3);
		play4 = (TextView) findViewById(R.id.play4);
		play5 = (TextView) findViewById(R.id.play5);
		play6 = (TextView) findViewById(R.id.play6);
		play7 = (TextView) findViewById(R.id.play7);
		
		
		String text1="<font color='red'><i>�����������εص�</i></font><br/>";
		text1+="<a href='http://www.colorfulworld.cn/'>��ɳ����֮��</a>";
		play1.setText(Html.fromHtml(text1));     //��������Ч��
		play1.setMovementMethod(LinkMovementMethod.getInstance());
		
		String text2="<a href='http://scjzzt.360500.com/'>������ͷ</a>";
		play2.setText(Html.fromHtml(text2));     //��������Ч��
		play2.setMovementMethod(LinkMovementMethod.getInstance());
		
		String text3="<a href='http://www.hnyls.com/'>��´ɽ�羰��ʤ��</a>";
		play3.setText(Html.fromHtml(text3));     //��������Ч��
		play3.setMovementMethod(LinkMovementMethod.getInstance());
		
		String text4="<a href='http://www.csm.hn.cn/#/home'>��ɳ�����</a>";
		play4.setText(Html.fromHtml(text4));     //��������Ч��
		play4.setMovementMethod(LinkMovementMethod.getInstance());
		
		String text5="<a href='http://www.cszoo.com.cn/'>��ɳ��̬����԰</a>";
		play5.setText(Html.fromHtml(text5));     //��������Ч��
		play5.setMovementMethod(LinkMovementMethod.getInstance());
		
		String text6="<a href='http://www.mafengwo.cn/poi/3756484.html'>��һ�㳡</a>";
		play6.setText(Html.fromHtml(text6));     //��������Ч��
		play6.setMovementMethod(LinkMovementMethod.getInstance());

		String text7="<a href='http://www.hnsslzwy.com/'>����ʡɭ��ֲ��԰</a>";
		play7.setText(Html.fromHtml(text7));     //��������Ч��
		play7.setMovementMethod(LinkMovementMethod.getInstance());
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.return1:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent2 =new Intent(play.this,MainActivity.class);
			//����
			startActivity(intent2);
			break;
		}
	}
}
