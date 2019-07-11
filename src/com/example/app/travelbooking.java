package com.example.app;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class travelbooking<IWXAPI>  extends Activity implements OnClickListener {
	private TextView fx;
	private Button return1;
	private Button btn_start;
	private EditText destination;
	private EditText start;
	private EditText time;
	private EditText way;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.travelbooking);
		return1 = (Button) findViewById(R.id.return1);
		return1.setOnClickListener(this);
		btn_start = (Button) findViewById(R.id.btn_start);
		btn_start.setOnClickListener(this);
		fx = (TextView) findViewById(R.id.fx);
		fx.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG );
		fx.setTextColor(android.graphics.Color.BLUE);
		
		time = (EditText) findViewById(R.id.time);
		String result3=time.getText().toString().trim();
		application app3=application.getInstance();
		app3.getMap().put("time0", result3);
		
		way = (EditText) findViewById(R.id.way);
		String result4=way.getText().toString().trim();
		application app4=application.getInstance();
		app4.getMap().put("time0", result4);
		
		start = (EditText) findViewById(R.id.start);
		String result1=start.getText().toString().trim();
		application app1=application.getInstance();
		app1.getMap().put("time0", result1);
		start.setOnTouchListener(new OnTouchListener() {

	            @Override
	            public boolean onTouch(View v, MotionEvent event) {
	                // et.getCompoundDrawables()�õ�һ������Ϊ4�����飬�ֱ��ʾ������������ͼƬ
	                Drawable drawable = start.getCompoundDrawables()[2];
	                //����ұ�û��ͼƬ�����ٴ���
	                if (drawable == null)
	                    return false;
	                //������ǰ����¼������ٴ���
	                if (event.getAction() != MotionEvent.ACTION_UP)
	                    return false;
	                if (event.getX() > start.getWidth()
	                        - start.getPaddingRight()
	                        - drawable.getIntrinsicWidth()){
	                	start.setText("���ϲ�������ѧԺ");
	                }
	                    return false;
	            }
	        });
		
		destination = (EditText) findViewById(R.id.destination);
		application app=application.getInstance();
		String result=(String)app.getMap().get("result0");
		destination.setText(result);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.return1:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent1 =new Intent(travelbooking.this,search.class);
			//����
			startActivity(intent1);
			break;
		case R.id.btn_start:
			if((start.getText().length()!=0)&&(destination.getText().length()!=0)&&(time.getText().length()!=0)&&(way.getText().length()!=0)){
				// ��bnt1��ӵ����Ӧ�¼�
				Intent intent2 =new Intent(travelbooking.this,fenxi.class);
				//����
				startActivity(intent2);
			}			
				break;
		}
	}
}

