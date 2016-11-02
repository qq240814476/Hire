package com.An.My;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;
import cn.bmob.im.BmobChatManager;
import cn.bmob.im.BmobUserManager;
import cn.bmob.im.bean.BmobRecent;

public class MyActivity extends Activity{
	public static BmobUserManager userManager;
	public BmobChatManager manager;
	public static BmobRecent mBmobRecent;
	
	public final int LOGIN_FINISH=1;
	public static final int GO_HOME = 99;
	public static final int GO_CHAT = 100;
	public static final int GO_LOGIN = 200;
	
	public static final int INIT= 0;
	public static final int SEND_MSG = 1;
	public static final int SEND_SMS = 2;
	protected int mScreenWidth;
	protected int mScreenHeight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		userManager = BmobUserManager.getInstance(this);
		manager = BmobChatManager.getInstance(this);
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;
	}

	Toast mToast;

	public void ShowToast(final String text) {
		if (!TextUtils.isEmpty(text)) {
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (mToast == null) {
						mToast = Toast.makeText(getApplicationContext(), text,
								Toast.LENGTH_LONG);
					} else {
						mToast.setText(text);
					}
					mToast.show();
				}
			});
			
		}
	}

	public void ShowToast(final int resId) {
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (mToast == null) {
					mToast = Toast.makeText(MyActivity.this.getApplicationContext(), resId,
							Toast.LENGTH_LONG);
				} else {
					mToast.setText(resId);
				}
				mToast.show();
			}
		});
	}

	/** ¥ÚLog
	  * ShowLog
	  * @return void
	  * @throws
	  */
	public void ShowLog(String msg){
		Log.i("life",msg);
	}

	
}
