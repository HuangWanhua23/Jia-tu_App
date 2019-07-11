package com.example.app;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import com.amap.api.maps.MapView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable.Callback;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class Main3Activity extends Activity implements OnClickListener{
	private Button btn_data; //����
	private Button btn_find; //����
	//private Button btn_sq;  //����
	private Button btn_message; //������Ϣ
	private WebView webview;
	//MapView mMapView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		btn_data  = (Button) findViewById(R.id.btn_data);
		btn_data.setOnClickListener(this);
		btn_find  = (Button) findViewById(R.id.btn_find);
		btn_find.setOnClickListener(this);
		btn_message  = (Button) findViewById(R.id.btn_message);
		btn_message.setOnClickListener(this);
	    //mMapView = (MapView) findViewById(R.id.map);
		    //��activityִ��onCreateʱִ��mMapView.onCreate(savedInstanceState)��������ͼ
		//mMapView.onCreate(savedInstanceState);
		 webview = (WebView) findViewById(R.id.webView);
	        //����WebView���ԣ��ܹ�ִ��Javascript�ű�
	        webview.getSettings().setJavaScriptEnabled(true);
	        try {
	        	WebSettings web=webview.getSettings();
	        	String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath(); 

	        	//���õ���λ  
	        	web.setGeolocationEnabled(true);  
	        	//���ö�λ�����ݿ�·��  
	        	web.setGeolocationDatabasePath(dir);  
	        	web.setJavaScriptEnabled(true);
	        	//һ��Ҫ���ã���Ȼ��λ����
	        	web.setDomStorageEnabled(true);
	        	webview.setWebChromeClient(new WebChromeClient()
	        	{
	        		//����Ȩ�ޣ�ͬ����WebChromeClient��ʵ�֣�
	                @Override
	               //��ǰҳ�������Ƿ�����H5��λ
	                public void onGeolocationPermissionsShowPrompt(String origin,GeolocationPermissions.Callback callback) {
	                	callback.invoke(origin, true, false);
	                	super.onGeolocationPermissionsShowPrompt(origin, callback);
	                }
	        	});
	        	
	            //���ô򿪵�ҳ���ַ
	            webview.loadUrl("http://www.amap.com/");
	            webview.setWebViewClient(new WebViewClient());
	        }
	        catch(Exception ex)
	        {
	            ex.printStackTrace();
	        }
//		try{
//			URL url=new URL("https://www.amap.com/");
//			HttpURLConnection con=(HttpURLConnection) url.openConnection();
//			con.setRequestMethod("GET"); //ʹ��get���� 
//			InputStream is = con.getInputStream();   //��ȡ����������ʱ��������������  
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
	}
	@Override
	public void onClick(View v3) {
		// TODO Auto-generated method stub
		switch (v3.getId()){
		case R.id.btn_data:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent1 =new Intent(Main3Activity.this,MainActivity.class);
			//����
			startActivity(intent1);
			break;
		case R.id.btn_find:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent2 =new Intent(Main3Activity.this,Main2Activity.class);
			//����
			startActivity(intent2);
			break;
		case R.id.btn_message:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent3 =new Intent(Main3Activity.this,MessageActivity.class);
			//����
			startActivity(intent3);
			break;
		}
	}
//	@Override
//	  protected void onDestroy() {
//	    super.onDestroy();
//	    //��activityִ��onDestroyʱִ��mMapView.onDestroy()�����ٵ�ͼ
//	    mMapView.onDestroy();
//	  }
//	 @Override
//	 protected void onResume() {
//	    super.onResume();
//	    //��activityִ��onResumeʱִ��mMapView.onResume ()�����»��Ƽ��ص�ͼ
//	    mMapView.onResume();
//	    }
//	 @Override
//	 protected void onPause() {
//	    super.onPause();
//	    //��activityִ��onPauseʱִ��mMapView.onPause ()����ͣ��ͼ�Ļ���
//	    mMapView.onPause();
//	    }
//	 @Override
//	 protected void onSaveInstanceState(Bundle outState) {
//	    super.onSaveInstanceState(outState);
//	    //��activityִ��onSaveInstanceStateʱִ��mMapView.onSaveInstanceState (outState)�������ͼ��ǰ��״̬
//	    mMapView.onSaveInstanceState(outState);
//	  } 
}
