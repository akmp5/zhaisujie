package com.ag.zhaisujie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

import com.ag.zhaisujie.App;
import com.ag.zhaisujie.R;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.LocationData;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;

/**
 * 
 *    MainActivity.java
 *     <p>
 *     ѡ�����ص���ҳ��
 *     Copyright: Copyright(c) 2013 
 *     @author Gavin_Feng
 *     </p>
 * 
 */
public class MainActivity extends Activity {
	private String key="B380D1FC76F5489BEA2C6B9B1C69E2744D1FB471";
	private BMapManager mBMapMan = null;
	private MapView mMapView = null;
	private MapController mapController = null;
	private MyLocationOverlay myLocationOverlay;
	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();
	private  MKSearch mkSerach;
	private  TextView locatFrom;
	private  App app;
	private Button loginBtn;
	private Button orderBtn;
	private Button settingBtn;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init(key, null);
		// ע�⣺��������setContentViewǰ��ʼ��BMapManager���󣬷���ᱨ��
		setContentView(R.layout.main);
		locatFrom=(TextView)findViewById(R.id.from_location);
		mMapView = (MapView) findViewById(R.id.bmapsView);
		initMap();//��ʼ����ͼ
		app=App.getInstance(this);
		loginBtn=(Button) findViewById(R.id.title_btn_login);
		loginBtn.setOnClickListener(listener);
		orderBtn=(Button) findViewById(R.id.title_btn_order);
		settingBtn=(Button) findViewById(R.id.title_btn_setting);
		
		//����̧ͷ
		if(app.getUser()==null){
			loginBtn.setVisibility(View.VISIBLE);
			orderBtn.setVisibility(View.GONE);
			settingBtn.setVisibility(View.GONE);
		}else{
			loginBtn.setVisibility(View.GONE);
			orderBtn.setVisibility(View.VISIBLE);
			settingBtn.setVisibility(View.VISIBLE);
		}
	}

	private void initMap(){
		
		mMapView.setBuiltInZoomControls(true);
		// �����������õ����ſؼ�
		mapController = mMapView.getController();
		// �õ�mMapView�Ŀ���Ȩ,�����������ƺ�����ƽ�ƺ�����
		GeoPoint point = new GeoPoint((int) (39.915 * 1E6),
				(int) (116.404 * 1E6));
		// �ø����ľ�γ�ȹ���һ��GeoPoint����λ��΢�� (�� * 1E6)
		mapController.setCenter(point);// ���õ�ͼ���ĵ�
		mapController.setZoom(12);// ���õ�ͼzoom����
		
		// ���Ӷ�λͼ��
		myLocationOverlay = new MyLocationOverlay(mMapView);
		// �����ų���Ӧ������
		myLocationOverlay.enableCompass();
		mMapView.getOverlays().add(myLocationOverlay);
		
		mLocationClient = new LocationClient(getApplicationContext()); // ����LocationClient��
		mLocationClient.registerLocationListener(myListener); // ע���������
		
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");//���صĶ�λ���������ַ��Ϣ
		option.setCoorType("bd09ll");//���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
		option.setScanSpan(5000);//���÷���λ����ļ��ʱ��Ϊ5000ms
		option.disableCache(true);//��ֹ���û��涨λ
		//option.setPoiNumber(5);	//��෵��POI����	
		//option.setPoiDistance(1000); //poi��ѯ����		
		//option.setPoiExtraInfo(true); //�Ƿ���ҪPOI�ĵ绰�͵�ַ����ϸ��Ϣ		
		mLocationClient.setLocOption(option);
		//��ʼ
		mLocationClient.start();
		mLocationClient.requestLocation();
		
		mMapView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					// ������������ ����ʾ�����Ϣ����һ�����Ҳ�֪����ô�����ˡ�
					// ��һ����Ҫ��Ч���ǣ�����Ļ���ĵ�ͼ�����棬�и�ð�ݿ���ʾ�����Ϣ
					// ��ȡmapview����������
					GeoPoint gp = mMapView.getMapCenter();
					search(gp);//��ʾ
					return true;
				}
				return false;
			}
		});

		//������ʼ��
		mkSerach = new MKSearch();
		mkSerach.init(mBMapMan, new MKSearchListener() {

			@Override
			public void onGetWalkingRouteResult(MKWalkingRouteResult arg0,
					int arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetTransitRouteResult(MKTransitRouteResult arg0,
					int arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetPoiDetailSearchResult(int arg0, int arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetDrivingRouteResult(MKDrivingRouteResult arg0,
					int arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetAddrResult(MKAddrInfo info, int arg1) {
				locatFrom.setText(info.strAddr);
			}
		});
		
		
	}
	
	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;
			LocationData locData = new LocationData();
			//�ֶ���λ��Դ��Ϊ�찲�ţ���ʵ��Ӧ���У���ʹ�ðٶȶ�λSDK��ȡλ����Ϣ��Ҫ��SDK����ʾһ��λ�ã���Ҫ
			//ʹ�ðٶȾ�γ�����꣨bd09ll��
			locData.latitude  = location.getLatitude();
			locData.longitude = location.getLongitude();
			locData.accuracy = location.getRadius();
			locData.direction = location.getDerect();
			locData.speed     = location.getSpeed();
			locData.satellitesNum     = location.getSatelliteNumber();
			myLocationOverlay.setData(locData);
			mMapView.refresh();
			GeoPoint gp= new GeoPoint((int)(locData.latitude*1e6),
					(int)(locData.longitude* 1e6));
			mMapView.getController().animateTo(gp);
			search(gp);//��ʾ
			mLocationClient.stop();
		
		}
		public void onReceivePoi(BDLocation poiLocation) {
			return;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		mMapView.destroy();
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		if(mLocationClient!=null){
			mLocationClient.stop();
			mLocationClient=null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		if(mLocationClient!=null){
			mLocationClient.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		if(mLocationClient!=null){
			mLocationClient.start();
		}
		super.onResume();
	}
	
	private void search(GeoPoint gp){
		mkSerach.reverseGeocode(gp);
		locatFrom.setText("��ȡλ����...");
	}
	
	OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			Button btn = (Button) v;
			switch (btn.getId()) {
			case R.id.title_btn_login:
				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
				MainActivity.this.startActivity(intent);
				//MainActivity.this.finish();
				break;
			case R.id.title_btn_order:
				break;
			case R.id.title_btn_setting:
				break;
			}
		}
	};
	
}