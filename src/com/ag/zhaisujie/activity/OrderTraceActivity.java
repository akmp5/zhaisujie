package com.ag.zhaisujie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ag.zhaisujie.R;

/**
 * ����׷��
 * 
 * @author max.Luo
 * @email max_null@sina.com
 * 
 */
@SuppressWarnings("all")
public class OrderTraceActivity extends Activity {
	private Button backBtn;

	private String commitTime = "";// �ύ�ɹ�ʱ��

	private String companyName = "";// ������˾����
	private String companyPhone = "";// ������˾�绰
	private String matchTime = "";// ƥ��ʱ��

	private ImageView waiterImg;// ������Աͷ��
	private String waiterName = "";// ������Ա����
	private String waiterPhone = "";// ������Ա�绰
	private String serviceTime = "";// ����ʱ��

	private String serviceDoneTime = "";// �������ʱ��

	private Button orderCancelBtn;
	private Button contactHomeCompanyBtn;
	private Button contactWaiterBtn;
	private Button confirmBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_trace);

		Bundle bundle = this.getIntent().getExtras();

		TextView title = (TextView) findViewById(R.id.title);
		title.setText(R.string.order_trace);
		backBtn = (Button) findViewById(R.id.title_btn_back);
		backBtn.setVisibility(View.VISIBLE);
		backBtn.setOnClickListener(listener);

		orderCancelBtn = (Button) findViewById(R.id.order_cancel);
		orderCancelBtn.setOnClickListener(listener);

		contactHomeCompanyBtn = (Button) findViewById(R.id.contact_home_company);
		contactHomeCompanyBtn.setOnClickListener(listener);

		contactWaiterBtn = (Button) findViewById(R.id.contact_waiter);
		contactWaiterBtn.setOnClickListener(listener);

		confirmBtn = (Button) findViewById(R.id.confirm_done);
		confirmBtn.setOnClickListener(listener);

	}

	OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			Button btn = (Button) v;
			switch (btn.getId()) {
			case R.id.title_btn_back:
				OrderTraceActivity.this.finish();
				break;
			case R.id.order_cancel:
				break;
			case R.id.contact_home_company:
				break;
			case R.id.contact_waiter:
				break;
			case R.id.confirm_done:
				Intent intent = new Intent(OrderTraceActivity.this, ServiceActivity.class);
				startActivity(intent);
				break;
			}
		}
	};

}
