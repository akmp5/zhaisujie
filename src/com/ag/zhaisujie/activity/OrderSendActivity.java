package com.ag.zhaisujie.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ag.zhaisujie.R;
import com.ag.zhaisujie.utils.CustomGifView;
import com.ag.zhaisujie.utils.ThreadPoolUtils;
import com.ant.liao.GifView;
import com.ant.liao.GifView.GifImageType;

/**
 * OrderSendActivity.java
 * 
 * @author max.Luo
 * @email max_null@sina.com 2013-5-25
 */
public class OrderSendActivity extends BaseActivity {

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		System.out.println("onResume");
		super.onResume();
		
	}
	
	

	@Override
	public void onBackPressed() {
		System.out.println("onBackPressed");
		super.onBackPressed();
	}

	@Override
	protected void onPause() {
		System.out.println("onPause");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		System.out.println("onRestart");
		super.onRestart();
	}

	@Override
	protected void onStart() {
		System.out.println("onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {
		System.out.println("onStop");
		super.onStop();
	}



	private Button backBtn;
	private CustomGifView progressImg;
	private TextView resultInfo;
	private TextView warnInfo;

	private Handler handler = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_send);
		backBtn = (Button) findViewById(R.id.title_btn_back);
		backBtn.setVisibility(View.VISIBLE);
		backBtn.setOnClickListener(listener);

		// 创建属于主线程的handler
		if (handler == null) {
			handler = new Handler() {

				@Override
				public void handleMessage(Message msg) {
					Intent intent = new Intent(OrderSendActivity.this,
							OrderTraceActivity.class);
					startActivity(intent);
				}

			};
		}

		Bundle bundle = this.getIntent().getExtras();

		TextView title = (TextView) findViewById(R.id.title);
		title.setText(R.string.order_send);

		progressImg = (CustomGifView) findViewById(R.id.order_send_process);
		progressImg.setResource(R.drawable.loading);
		resultInfo = (TextView) findViewById(R.id.order_result_info);
		warnInfo = (TextView) findViewById(R.id.order_warn_info);
		ThreadPoolUtils.execute(run);
	}

	Runnable run = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(3000);
				Message msg = new Message();
				Bundle bundle = new Bundle();
				msg.setData(bundle);
				handler.sendMessage(msg);

				// 进行支付or获取跟踪信息
				/*
				 * ImageView iv = new ImageView(OrderSendActivity.this);
				 * iv.setImageBitmap(BitmapFactory.decodeStream(null));
				 */
				Thread.sleep(3000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	};

	OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			Button btn = (Button) v;
			switch (btn.getId()) {
			case R.id.title_btn_back:
				OrderSendActivity.this.finish();
				break;
			}
		}
	};
}
