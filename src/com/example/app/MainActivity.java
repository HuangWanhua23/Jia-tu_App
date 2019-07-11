package com.example.app;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {
	private Button btn_find;   //����
	private Button btn_run;   //�г�
	private Button btn_message;  //������Ϣ
	private Button btn_jiance;   //�������
	private Button btn_yuce;     //����Ԥ��
	private Button btn_travelbooking;   //���ж�Ʊ
	private Button btn_feature;   //������ɫ
	private Button btn_play;      //���湥��
	private Button btn_hotel;     //�ܱ߾Ƶ�
	private Button centerright; //ͼƬ��ת
	private Button image_i12;//ͼƬ��ת
	private EditText et_rsyc; //������
	
	
	private ImageView[] imageViews = null;
	private ImageView imageView = null;
	private ViewPager advPager = null;
	private AtomicInteger what = new AtomicInteger(0);
	private boolean isContinue = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initViewPager();
		btn_find = (Button) findViewById(R.id.btn_find);
		btn_find.setOnClickListener(this);
		btn_run  = (Button) findViewById(R.id.btn_run);
		btn_run.setOnClickListener(this);
		btn_message  = (Button) findViewById(R.id.btn_message);
		btn_message.setOnClickListener(this);
		//centerright = (Button) findViewById(R.id.centerright);
		//centerright.setOnClickListener(this);
		image_i12 = (Button) findViewById(R.id.image_i12);
		image_i12.setOnClickListener(this);
		btn_jiance  = (Button) findViewById(R.id.btn_jiance);
		btn_jiance.setOnClickListener(this);
		btn_yuce  = (Button) findViewById(R.id.btn_yuce);
		btn_yuce.setOnClickListener(this);
		btn_travelbooking  = (Button) findViewById(R.id.btn_travelbooking);
		btn_travelbooking.setOnClickListener(this);
		btn_feature  = (Button) findViewById(R.id.btn_feature);
		btn_feature.setOnClickListener(this);
		btn_play  = (Button) findViewById(R.id.btn_play);
		btn_play.setOnClickListener(this);
		btn_hotel  = (Button) findViewById(R.id.btn_hotel);
		btn_hotel.setOnClickListener(this);
		et_rsyc  = (EditText) findViewById(R.id.et_rsyc);
		et_rsyc.setFocusable(false);
		et_rsyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intents=new Intent(MainActivity.this, search.class);
                startActivity(intents);
            }
        });
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.btn_find:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent2 =new Intent(MainActivity.this,Main2Activity.class);
			//����
			startActivity(intent2);
			break;
		case R.id.btn_run:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent3 =new Intent(MainActivity.this,Main3Activity.class);
			//����
			startActivity(intent3);
			break;
		case R.id.btn_message:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent4 =new Intent(MainActivity.this,MessageActivity.class);
			//����
			startActivity(intent4);
			break;
		/*case R.id.centerright:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent11 =new Intent(MainActivity.this,scenicl.class);
			//����
			startActivity(intent11);
			break;*/
		case R.id.image_i12:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent12 =new Intent(MainActivity.this,centerright.class);
			//����
			startActivity(intent12);
			break;
		case R.id.btn_jiance:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent5 =new Intent(MainActivity.this,search.class);
			//����
			startActivity(intent5);
			break;
		case R.id.btn_yuce:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent6 =new Intent(MainActivity.this,search.class);
			//����
			startActivity(intent6);
			break;
		case R.id.btn_travelbooking:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent7 =new Intent(MainActivity.this,search.class);
			//����
			startActivity(intent7);
			break;
		case R.id.btn_feature:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent8 =new Intent(MainActivity.this,feature.class);
			//����
			startActivity(intent8);
			break;
		case R.id.btn_play:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent9 =new Intent(MainActivity.this,play.class);
			//����
			startActivity(intent9);
			break;
		case R.id.btn_hotel:
			// ��bnt1��ӵ����Ӧ�¼�
			Intent intent10 =new Intent(MainActivity.this,hotel.class);
			//����
			startActivity(intent10);
			break;
		}
	}
	

	private void initViewPager() {
		advPager = (ViewPager) findViewById(R.id.adv_pager);
		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);

		List<View> advPics = new ArrayList<View>();

		ImageView img1 = new ImageView(this);
		img1.setBackgroundResource(R.drawable.d1);
		advPics.add(img1);

		ImageView img2 = new ImageView(this);
		img2.setBackgroundResource(R.drawable.a10);
		advPics.add(img2);

		ImageView img3 = new ImageView(this);
		img3.setBackgroundResource(R.drawable.a7);
		advPics.add(img3);

		ImageView img4 = new ImageView(this);
		img4.setBackgroundResource(R.drawable.a9);
		advPics.add(img4);
		
		imageViews = new ImageView[advPics.size()];

		for (int i = 0; i < advPics.size(); i++) {
			imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(20, 20));
			imageView.setPadding(5, 5, 5, 5);
			imageViews[i] = imageView;
			if (i == 0) {
				imageViews[i]
						.setBackgroundResource(R.drawable.banner_dian_focus);
			} else {
				imageViews[i]
						.setBackgroundResource(R.drawable.banner_dian_blur);
			}
			group.addView(imageViews[i]);
		}

		advPager.setAdapter(new AdvAdapter(advPics));
		advPager.setOnPageChangeListener(new GuidePageChangeListener());
		advPager.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					isContinue = false;
					break;
				case MotionEvent.ACTION_UP:
					isContinue = true;
					break;
				default:
					isContinue = true;
					break;
				}
				return false;
			}
		});
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (isContinue) {
						viewHandler.sendEmptyMessage(what.get());
						whatOption();
					}
				}
			}

		}).start();
	}

	private void whatOption() {
		what.incrementAndGet();
		if (what.get() > imageViews.length - 1) {
			what.getAndAdd(-4);
		}
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			
		}
	}

	private final Handler viewHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			advPager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		}

	};

	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			what.getAndSet(arg0);
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0]
						.setBackgroundResource(R.drawable.banner_dian_focus);
				if (arg0 != i) {
					imageViews[i]
							.setBackgroundResource(R.drawable.banner_dian_blur);
				}
			}

		}

	}

	private final class AdvAdapter extends PagerAdapter {
		private List<View> views = null;

		public AdvAdapter(List<View> views) {
			this.views = views;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(views.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {

		}

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(views.get(arg1), 0);
			return views.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {

		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {

		}

	}

}
