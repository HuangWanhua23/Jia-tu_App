package com.example.app;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class jiance  extends Activity implements OnClickListener {
	private LineChart mLineChart; //����ͼ
	private BarChart mChart;     //����ͼ
	private TextView jrlj;      //�����ۼ�
	private Handler handler=new Handler(){
		//������Ϣ
		public void handleMessage(Message msg){
			String str=(String)msg.obj;
			if(str.length()==3){
				jrlj.setPadding(145, 0, 0, 0);
				jrlj.setText(str);
			}else if(str.length()==5){
				jrlj.setPadding(100, 0, 0, 0);
				jrlj.setText(str);
			}else if(str.length()==6){
				jrlj.setPadding(60, 0, 0, 0);
				jrlj.setText(str);
			}
		}
	};
	private TextView sskl;      //˲ʱ����
	private Handler handler1=new Handler(){
		//������Ϣ
		public void handleMessage(Message msg){
			String str1=(String)msg.obj;
			if(str1.length()==3){
				sskl.setPadding(145, 0, 0, 0);
				sskl.setText(str1);
			}else if(str1.length()==5){
				sskl.setPadding(95, 0, 0, 0);
				sskl.setText(str1);
			}else if(str1.length()==6){
				sskl.setPadding(60, 0, 0, 0);
				sskl.setText(str1);
			}
		}
	};
	private Button return1;
	private Spinner spinner1;  //�����б��
	private Spinner spinner2;  //�����б��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jiance);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		initSpinner1();
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		initSpinner2();
		jrlj = (TextView) findViewById(R.id.jrlj);
		//�������̲߳�����
		MyThread myth=new MyThread();
		myth.start();
		sskl = (TextView) findViewById(R.id.sskl);
		MyThread1 myth1=new MyThread1();
		myth1.start();		
		return1 = (Button) findViewById(R.id.return1);
		return1.setOnClickListener(this);
		mLineChart = (LineChart) findViewById(R.id.spread_line_chart); 
		// mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Bold.ttf"); 
		LineData mLineData = getLineData(24, 12); 
		showChart(mLineChart, mLineData, Color.rgb(255,255,255)); 
		mChart = (BarChart) findViewById(R.id.chart);
	    showChart(getBarData());
	}
	//�Զ������߳�
	class MyThread extends Thread{
		public void run(){
			try{
				String[] str={"200","589","1,153","2,025","3,149","4,469","5,929","7,473","9,045","10,554","11,951","13,180","14,185"};
				for(int i=0;i<str.length;i++){
					Message msg=new Message();
					msg.obj=str[i];
					//������Ϣ
					Thread.sleep(900);
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
					String[] str1={"200","389","564","872","1,124","1,320","1,460","1,544","1,572","1,509","1,397","1,229","1,005","872"};
					for(int i=0;i<str1.length;i++){
						Message msg=new Message();
						msg.obj=str1[i];
						//������Ϣ
						Thread.sleep(900);
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
			mLegend.setTextColor(Color.BLUE);// ��ɫ 
	 		//mLegend.setTypeface(mTf);// ���� 
			lineChart.animateX(14800); // ����ִ�еĶ���,x�� 
			
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
//	       leftAxis.setValueFormatter();//�Զ���Y�����ݸ�ʽ
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
				xValues.add(""+i+":00"); 
			} 
			// y������� 
			ArrayList<Entry> yValues = new ArrayList<Entry>(); 
			for (int i = 0; i <=count; i++) { 
				//float value = (float) (Math.random() * range) + 3; 
				float value1 = (float) (-7*i*i+196*i+200); //ģ�⺯��������ʾģ���ʱ��ڵ��Ͼ���������
				yValues.add(new Entry(value1, i)); 
			} 
			// create a dataset and give it a type 
			// y������ݼ��� 
			application app=application.getInstance();
			String result=(String)app.getMap().get("result0");
			LineDataSet lineDataSet = new LineDataSet(yValues, "2019��2��9��"+result+"ʵʱ����" /*��ʾ�ڱ���ͼ��*/); 
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
	        lineDataSet.setFillColor(Color.rgb(0, 255, 255));//����ͼ�·������ɫ����
			LineData lineData = new LineData(xValues, lineDataSets); 
	 		return lineData;
		}

		   /**
		    * ��ʾ��״ͼ��
		    *
		    * @param barData
		    */
		   private void showChart(BarData barData) {
		       // ��������
		       mChart.setDescription("");
		       mChart.setTouchEnabled(true); // �����Ƿ���Դ��� 
				// enable scaling and dragging 
		       mChart.setDragEnabled(true);// �Ƿ������ק 
		       mChart.setScaleEnabled(true);// �Ƿ�������� 
				// if disabled, scaling can be done on x- and y-axis separately 
		       mChart.setPinchZoom(true);// X,Y��ͬʱ����
		       mChart.getAxisRight().setEnabled(false); // �����ұ� ��������
		       // ����ͼ������
		       mChart.setData(barData);
		       // ���ö���
		       mChart.animateY(3000);
		       /**
		         * ����X��
		         * */
		        XAxis xAxis = mChart.getXAxis();
		        xAxis.setEnabled(false);//��ʾX��
		        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//X��λ��
		        xAxis.setDrawGridLines(true);//����x����ÿ�����Ӧ����
		        xAxis.setSpaceBetweenLabels(3);
		        xAxis.setDrawGridLines(true);   //x���������
		   }

		   /**
		    * ��ȡ��״����
		    *
		    * @return
		    */
		   private BarData getBarData() {
			   ArrayList<String> xValues = new ArrayList<String>(); 
				for (int i = 0; i <=9; i++) { 
					// x����ʾ�����ݣ�����Ĭ��ʹ�������±���ʾ 
					xValues.add(""+i); 
				} 
		       ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();

		       float quarterly1 = 3120;
		       float quarterly2 = 2650;
		       float quarterly3 = 3105;
		       float quarterly4 = 4550;
		       float quarterly5 = 4860;
		       float quarterly6 = 4250;
		       float quarterly7 = 4020;
		       float quarterly8 = 3550;
		       float quarterly9 = 3150;
		       float quarterly10 = 3450;

		       yValues.add(new BarEntry(quarterly1, 0));
		       yValues.add(new BarEntry(quarterly2, 1));
		       yValues.add(new BarEntry(quarterly3, 2));
		       yValues.add(new BarEntry(quarterly4, 3));
		       yValues.add(new BarEntry(quarterly5, 4));
		       yValues.add(new BarEntry(quarterly6, 5));
		       yValues.add(new BarEntry(quarterly7, 6));
		       yValues.add(new BarEntry(quarterly8, 7));
		       yValues.add(new BarEntry(quarterly9, 8));
		       yValues.add(new BarEntry(quarterly10, 9));
		       application app=application.getInstance();
		       String result=(String)app.getMap().get("result0");
		       BarDataSet barDataSet = new BarDataSet(yValues, "2019��2��7��-17��"+result+"�ۼ�����");
		       /*ArrayList<Integer> colors = new ArrayList<Integer>();
		       colors.add(Color.rgb(255, 123, 124));
		       barDataSet.setColors(colors);*/
		       BarData barData = new BarData(xValues, barDataSet);
		       return barData;
		   }
	/** ��ʼ��spinner1 */
	private void initSpinner1() {
		String[] strArr = new String[] { "2019-02-09", "2019-02-01", "2019-01-25", "2019-01-02" };
		// 1.������ 2.�б����layout ��ԴID 3.spinner �����б�Ҫ������
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, strArr);
		spinner1.setAdapter(adapter);
	}
	/** ��ʼ��spinner2 */
	private void initSpinner2() {
		String[] strArr = new String[] { "2019-02-07", "2019-02-05", "2019-01-20", "2019-01-08" };
		// 1.������ 2.�б����layout ��ԴID 3.spinner �����б�Ҫ������
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, strArr);
		spinner2.setAdapter(adapter);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.return1:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent2 =new Intent(jiance.this,search.class);
			//����
			startActivity(intent2);
			break;
		}
	}
}
