package com.example.app;

import java.util.ArrayList;

import com.example.app.jiance.MyThread;
import com.example.app.jiance.MyThread1;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class yuce  extends Activity implements OnClickListener {
	private Button return1;
	private LineChart mLineChart; //����ͼ
	private LineChart mLineChart1; //����ͼ
	private TextView nowpeople;      //�����ۼ�
	private Handler handler=new Handler(){
		//������Ϣ
		public void handleMessage(Message msg){
			String str=(String)msg.obj;
			if(str.length()==3){
				nowpeople.setPadding(145, 0, 0, 0);
				nowpeople.setText(str);
			}else if(str.length()==5){
				nowpeople.setPadding(100, 0, 0, 0);
				nowpeople.setText(str);
			}else if(str.length()==6){
				nowpeople.setPadding(60, 0, 0, 0);
				nowpeople.setText(str);
			}else if(str.length()==2){
				nowpeople.setPadding(185, 0, 0, 0);
				nowpeople.setText(str);
			}
		}
	};
	private TextView bear;      //������
	private Handler handler1=new Handler(){
		//������Ϣ
		public void handleMessage(Message msg){
			String str1=(String)msg.obj;
			if(str1.length()==3){
				bear.setPadding(145, 0, 0, 0);
				bear.setText(str1);
			}else if(str1.length()==5){
				bear.setPadding(95, 0, 0, 0);
				bear.setText(str1);
			}else if(str1.length()==6){
				bear.setPadding(60, 0, 0, 0);
				bear.setText(str1);
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yuce);
		return1 = (Button) findViewById(R.id.return1);
		return1.setOnClickListener(this);
		nowpeople = (TextView) findViewById(R.id.nowpeople);
		//�������̲߳�����
		MyThread myth=new MyThread();
		myth.start();
		bear = (TextView) findViewById(R.id.bear);
		MyThread1 myth1=new MyThread1();
		myth1.start();	
		mLineChart = (LineChart) findViewById(R.id.spread_line_chart); 
		LineData mLineData = getLineData(24, 12); 
		showChart(mLineChart, mLineData, Color.rgb(255,255,255));
		mLineChart1 = (LineChart) findViewById(R.id.spread1_line_chart); 
		LineData mLineData1 = getLineData1(7); 
		showChart1(mLineChart1, mLineData1, Color.rgb(255,255,255));
	}
	//�Զ������߳�
	class MyThread extends Thread{
		public void run(){
			try{
				String[] str={"3,149","5,469","8,529","11,473","13,545","14,954","14,851","13,550","10,480","6,785","7,550","6,032","3,200","1,200","400","50","20","15","12","25","320","1,200","2,400","3,700","5,250"};
				String[] str1={"3149"};
				for(int i=0;i<str1.length;i++){
					Message msg=new Message();
					msg.obj=str[i];
					//������Ϣ
					Thread.sleep(500);
					handler.sendMessage(msg);
				}		
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	//�Զ������߳�
		class MyThread1 extends Thread{
			public void run(){
				try{
					String[] str1={"17,500"};
					for(int i=0;i<str1.length;i++){
						Message msg=new Message();
						msg.obj=str1[i];
						//������Ϣ
						Thread.sleep(500);
						handler1.sendMessage(msg);
					}		
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		// ������ʾ����ʽ 
				private void showChart(LineChart lineChart, LineData lineData, int color) { 
					//lineChart.setDrawBorders(true); //�Ƿ�������ͼ����ӱ߿� 
					// no description text 
					lineChart.setDescription(" ");// �������� 
					// ���û�����ݵ�ʱ�򣬻���ʾ���������listview��emtpyview 
					//lineChart.setNoDataTextDescription("You need to provide data for the chart."); 
					// enable / disable grid background 
					lineChart.setDrawGridBackground(false); // �Ƿ���ʾ�����ɫ 
					lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // ���ĵ���ɫ�����������Ǹ���ɫ����һ��͸���� 
					// enable touch gestures 
					lineChart.setTouchEnabled(true); // �����Ƿ���Դ��� 
					// enable scaling and dragging 
					lineChart.setDragEnabled(true);// �Ƿ������ק 
					lineChart.setScaleEnabled(true);// �Ƿ�������� 
					// if disabled, scaling can be done on x- and y-axis separately 
					lineChart.setPinchZoom(true);// X,Y��ͬʱ����
					lineChart.getAxisRight().setEnabled(false); // �����ұ� ��������
					//lineChart.getXAxis().setGridColor(Color.TRANSPARENT);//ȥ�����������ߵ���ʾ
					lineChart.setBackgroundColor(color);// ���ñ��� 
					// add data 
					lineChart.setData(lineData); // �������� 
					// get the legend (only possible after setting data) 
					Legend mLegend = lineChart.getLegend(); // ���ñ���ͼ��ʾ�������Ǹ�һ��y��value�� 
					// modify the legend ... 
					//mLegend.setPosition(LegendPosition.LEFT_OF_CHART); 
					mLegend.setForm(LegendForm.CIRCLE);// ��ʽ 
					mLegend.setFormSize(7f);// ���� 
					mLegend.setTextColor(Color.BLACK);// ��ɫ 
			 		//mLegend.setTypeface(mTf);// ���� 
					lineChart.animateX(12000); // ����ִ�еĶ���,x�� 					
					/**
			         * ����X��
			         * */
			        XAxis xAxis = lineChart.getXAxis();
			        xAxis.setEnabled(true);//��ʾX��
			        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X��λ��
			        xAxis.setDrawGridLines(true);//����x����ÿ�����Ӧ����
			        xAxis.setSpaceBetweenLabels(3);
			        xAxis.setDrawGridLines(true);   //x���������
			        /**
			        *
			        * �������Y��
			        * */
			       YAxis leftAxis = lineChart.getAxisLeft();
			       leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//			       leftAxis.setValueFormatter();//�Զ���Y�����ݸ�ʽ
			       leftAxis.setStartAtZero(true);//����Y������ݲ��Ǵ�0��ʼ
			       leftAxis.setDrawTopYLabelEntry(true);
				} 
				/** 
				 * ����һ������ 
				 * @param count ��ʾͼ�����ж��ٸ������ 
				 * @param range ��������range���ڵ������ 
				 * @return 
				 */
				private LineData getLineData(int count, float range) { 
					ArrayList<String> xValues = new ArrayList<String>(); 
					for (int i = 0; i <=count; i++) { 
						// x����ʾ�����ݣ�����Ĭ��ʹ�������±���ʾ 
						if((i+8)>24)
						{
							xValues.add(""+(i-16)+":00");
						}else{
							xValues.add(""+(i+8)+":00"); 
						}	
					} 
					// y������� 
					ArrayList<Entry> yValues = new ArrayList<Entry>(); 
//					for (int i = 0; i <=count; i++) { 
						//float value = (float) (Math.random() * range) + 3; 
						//float value1 = (float) (-7*i*i+196*i+200); //ģ�⺯��������ʾģ���ʱ��ڵ��Ͼ���������
						//yValues.add(new Entry(value1, i)); 
						   float quarterly1 = 3149;
					       float quarterly2 = 5469;
					       float quarterly3 = 8529;
					       float quarterly4 = 11473;
					       float quarterly5 = 13545;
					       float quarterly6 = 14954;
					       float quarterly7 = 14851;
					       float quarterly8 = 13550;
					       float quarterly9 = 10480;
					       float quarterly10 = 6785;
					       float quarterly11 = 7550;
					       float quarterly12 = 6032;
					       float quarterly13 = 3200;
					       float quarterly14 = 1200;
					       float quarterly15 = 400;
					       float quarterly16 = 50;
					       float quarterly17 = 20;
					       float quarterly18 = 15;
					       float quarterly19 = 12;
					       float quarterly20 = 25;
					       float quarterly21 = 320;
					       float quarterly22 = 1200;
					       float quarterly23 = 2400;
					       float quarterly24 = 3700;
					       float quarterly25 = 5250;

					       yValues.add(new Entry(quarterly1, 0));
					       yValues.add(new Entry(quarterly2, 1));
					       yValues.add(new Entry(quarterly3, 2));
					       yValues.add(new Entry(quarterly4, 3));
					       yValues.add(new Entry(quarterly5, 4));
					       yValues.add(new Entry(quarterly6, 5));
					       yValues.add(new Entry(quarterly7, 6));
					       yValues.add(new Entry(quarterly8, 7));
					       yValues.add(new Entry(quarterly9, 8));
					       yValues.add(new Entry(quarterly10, 9));
					       yValues.add(new Entry(quarterly11, 10));
					       yValues.add(new Entry(quarterly12, 11));
					       yValues.add(new Entry(quarterly13, 12));
					       yValues.add(new Entry(quarterly14, 13));
					       yValues.add(new Entry(quarterly15, 14));
					       yValues.add(new Entry(quarterly16, 15));
					       yValues.add(new Entry(quarterly17, 16));
					       yValues.add(new Entry(quarterly18, 17));
					       yValues.add(new Entry(quarterly19, 18));
					       yValues.add(new Entry(quarterly20, 19));
					       yValues.add(new Entry(quarterly21, 20));
					       yValues.add(new Entry(quarterly22, 21));
					       yValues.add(new Entry(quarterly23, 22));
					       yValues.add(new Entry(quarterly24, 23));
					       yValues.add(new Entry(quarterly25, 24));
//					} 
					// create a dataset and give it a type 
					// y������ݼ��� 
					application app=application.getInstance();
					String result=(String)app.getMap().get("result0");
					LineDataSet lineDataSet = new LineDataSet(yValues, "δ��24Сʱ"+result+"Ԥ������" /*��ʾ�ڱ���ͼ��*/); 
					// mLineDataSet.setFillAlpha(110); 
					// mLineDataSet.setFillColor(Color.RED); 
					//��y��ļ��������ò��� 
					lineDataSet.setLineWidth(1.75f); // �߿� 
					lineDataSet.setCircleSize(4f);// ��ʾ��Բ�δ�С 
					lineDataSet.setColor(Color.CYAN);// ��ʾ��ɫ 
					lineDataSet.setCircleColor(Color.TRANSPARENT);// Բ�ε���ɫ 
					lineDataSet.setHighLightColor(Color.GREEN); // �������ߵ���ɫ 
					lineDataSet.setDrawValues(true);//��������ͼÿ�����ݵ��ֵ
					ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>(); 
					lineDataSets.add(lineDataSet); // add the datasets 
					// create a data object with the datasets 
					lineDataSet.setDrawCircles(true);//ͼ���ϵ����ݵ��Ƿ���СԲȦ��ʾ
			        lineDataSet.setDrawCubic(true);//��������ƽ������
			        lineDataSet.setDrawFilled(true);//�Ƿ���������·�
			        //lineDataSet.setFillColor(Color.rgb(0, 255, 255));//����ͼ�·������ɫ����
					LineData lineData = new LineData(xValues, lineDataSets); 
			 		return lineData;
				}
				
				
				// ������ʾ����ʽ 
				private void showChart1(LineChart lineChart, LineData lineData, int color) { 
					//lineChart.setDrawBorders(true); //�Ƿ�������ͼ����ӱ߿� 
					// no description text 
					lineChart.setDescription("");// �������� 
					// ���û�����ݵ�ʱ�򣬻���ʾ���������listview��emtpyview 
					//lineChart.setNoDataTextDescription("You need to provide data for the chart."); 
					// enable / disable grid background 
					lineChart.setDrawGridBackground(false); // �Ƿ���ʾ�����ɫ 
					lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // ���ĵ���ɫ�����������Ǹ���ɫ����һ��͸���� 
					// enable touch gestures 
					lineChart.setTouchEnabled(true); // �����Ƿ���Դ��� 
					// enable scaling and dragging 
					lineChart.setDragEnabled(true);// �Ƿ������ק 
					lineChart.setScaleEnabled(true);// �Ƿ�������� 
					// if disabled, scaling can be done on x- and y-axis separately 
					lineChart.setPinchZoom(true);// X,Y��ͬʱ����
					lineChart.getAxisRight().setEnabled(false); // �����ұߵ�������
					//lineChart.getAxisLeft().setEnabled(false);  // ������ߵ�������
					//lineChart.getXAxis().setGridColor(Color.TRANSPARENT);//ȥ�����������ߵ���ʾ
					lineChart.setBackgroundColor(color);// ���ñ��� 
					// add data 
					lineChart.setData(lineData); // �������� 
					// get the legend (only possible after setting data) 
					Legend mLegend = lineChart.getLegend(); // ���ñ���ͼ��ʾ�������Ǹ�һ��y��value�� 
					// modify the legend ... 
					//mLegend.setPosition(LegendPosition.LEFT_OF_CHART); 
					mLegend.setForm(LegendForm.CIRCLE);// ��ʽ 
					mLegend.setFormSize(7f);// ���� 
					mLegend.setTextColor(Color.BLACK);// ��ɫ 
			 		//mLegend.setTypeface(mTf);// ���� 
					lineChart.animateX(13500); // ����ִ�еĶ���,x�� 					
					/**
			         * ����X��
			         * */
			        XAxis xAxis = lineChart.getXAxis();
			        xAxis.setEnabled(true);//��ʾX��
			        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X��λ��
			        xAxis.setDrawGridLines(true);//����x����ÿ�����Ӧ����
			        xAxis.setSpaceBetweenLabels(3);
			        xAxis.setDrawGridLines(true);   //x���������
			        /**
			        *
			        * �������Y��
			        * */
			       YAxis leftAxis = lineChart.getAxisLeft();
			       leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//			       leftAxis.setValueFormatter();//�Զ���Y�����ݸ�ʽ
			       leftAxis.setStartAtZero(true);//����Y������ݲ��Ǵ�0��ʼ
			       leftAxis.setDrawTopYLabelEntry(false);
				} 
				/** 
				 * ����һ������ 
				 * @param count ��ʾͼ�����ж��ٸ������ 
				 * @return 
				 */
				private LineData getLineData1(int count) { 
					ArrayList<String> xValues = new ArrayList<String>(); 
					for (int i = 0; i <=count; i++) { 
						// x����ʾ�����ݣ�����Ĭ��ʹ�������±���ʾ 
						if(i==0)
						{
							xValues.add("����"); 
						}else{
							xValues.add("3/"+(i+23)); 	
						}				
					} 
					// y������� 
					ArrayList<Entry> yValues = new ArrayList<Entry>(); 
//					for (int i = 0; i <=count; i++) { 
						//float value = (float) (Math.random() * range) + 3; 
						//float value1 = (float) (-7*i*i+196*i+200); //ģ�⺯��������ʾģ���ʱ��ڵ��Ͼ���������
						//yValues.add(new Entry(value1, i)); 
						   float quarterly1 = 13550;
					       float quarterly2 = 14000;
					       float quarterly3 = 14250;
					       float quarterly4 = 13473;
					       float quarterly5 = 12745;
					       float quarterly6 = 13454;
					       float quarterly7 = 14051;
					       float quarterly8 = 14150;
					      
					       yValues.add(new Entry(quarterly1, 0));
					       yValues.add(new Entry(quarterly2, 1));
					       yValues.add(new Entry(quarterly3, 2));
					       yValues.add(new Entry(quarterly4, 3));
					       yValues.add(new Entry(quarterly5, 4));
					       yValues.add(new Entry(quarterly6, 5));
					       yValues.add(new Entry(quarterly7, 6));
					       yValues.add(new Entry(quarterly8, 7));
					      
//					} 
					// create a dataset and give it a type 
					// y������ݼ��� 
					application app=application.getInstance();
					String result=(String)app.getMap().get("result0");
					LineDataSet lineDataSet = new LineDataSet(yValues, "δ��7��"+result+"Ԥ������" /*��ʾ�ڱ���ͼ��*/); 
					// mLineDataSet.setFillAlpha(110); 
					// mLineDataSet.setFillColor(Color.RED); 
					//��y��ļ��������ò��� 
					lineDataSet.setLineWidth(1.75f); // �߿� 
					lineDataSet.setCircleSize(4f);// ��ʾ��Բ�δ�С 
					lineDataSet.setColor(Color.CYAN);// ��ʾ��ɫ 
					lineDataSet.setCircleColor(Color.TRANSPARENT);// Բ�ε���ɫ 
					lineDataSet.setHighLightColor(Color.GREEN); // �������ߵ���ɫ 
					lineDataSet.setDrawValues(true);//��������ͼÿ�����ݵ��ֵ
					ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>(); 
					lineDataSets.add(lineDataSet); // add the datasets 
					// create a data object with the datasets 
					lineDataSet.setDrawCircles(true);//ͼ���ϵ����ݵ��Ƿ���СԲȦ��ʾ
			        lineDataSet.setDrawCubic(true);//��������ƽ������
			        lineDataSet.setDrawFilled(true);//�Ƿ���������·�
			        //lineDataSet.setFillColor(Color.rgb(0, 255, 255));//����ͼ�·������ɫ����
					LineData lineData = new LineData(xValues, lineDataSets); 
			 		return lineData;
				}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.return1:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent2 =new Intent(yuce.this,search.class);
			//����
			startActivity(intent2);
			break;
		}
	}
}
