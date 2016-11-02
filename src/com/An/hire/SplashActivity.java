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
 * 引导页
 * 
 * @ClassName: SplashActivity
 * @Description: TODO
 * @author smile
 * @date 2014-6-4 上午9:45:43
 */
public class SplashActivity extends MyActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		//可设置调试模式，当为true的时候，会在logcat的BmobChat下输出一些日志，包括推送服务是否正常运行，如果服务端返回错误，也会一并打印出来。方便开发者调试
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
			// 每次自动登陆的时候就需要更新下当前位置和好友的资料，因为好友的头像，昵称啥的是经常变动的
			mHandler.sendEmptyMessageDelayed(GO_HOME, 2000);
		} else {
			mHandler.sendEmptyMessageDelayed(GO_LOGIN, 2000);
		}*/
	}
	
/*	初始化判断是否有对象缓存
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
