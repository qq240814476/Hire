package com.An.hire;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import cn.bmob.im.BmobChat;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

import com.An.My.MyActivity;
import com.An.Tools.Constant;



/**
 * ����ҳ
 * 
 * @ClassName: SplashActivity
 * @Description: TODO
 * @author smile
 * @date 2014-6-4 ����9:45:43
 */
public class SplashActivity extends MyActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		//�����õ���ģʽ����Ϊtrue��ʱ�򣬻���logcat��BmobChat�����һЩ��־���������ͷ����Ƿ��������У��������˷��ش���Ҳ��һ����ӡ���������㿪���ߵ���
		BmobChat.DEBUG_MODE = true;
		Bmob.initialize(this,Constant.BMOB_APP_ID);
		BmobChat.getInstance(this).init(Constant.BMOB_APP_ID);
		BmobInstallation.getCurrentInstallation(this).save();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mHandler.sendEmptyMessageDelayed(GO_HOME,2000);
		/*if (userManager.getCurrentUser() != null) {
			// ÿ���Զ���½��ʱ�����Ҫ�����µ�ǰλ�úͺ��ѵ����ϣ���Ϊ���ѵ�ͷ���ǳ�ɶ���Ǿ����䶯��
			mHandler.sendEmptyMessageDelayed(GO_HOME, 2000);
		} else {
			mHandler.sendEmptyMessageDelayed(GO_LOGIN, 2000);
		}*/
	}
	
/*	��ʼ���ж��Ƿ��ж��󻺴�
 * @SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
				startActivity(new Intent(SplashActivity.this,MainActivity.class));
				finish();
				break;
			case GO_LOGIN:
				startActivity(new Intent(SplashActivity.this,LoginActivity.class));
				finish();
				break;
			}
		}
	};
*/
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
				startActivity(new Intent(SplashActivity.this,MainActivity.class));
				finish();
				break;
			}
		}
	};
}
