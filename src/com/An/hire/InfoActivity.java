package com.An.hire;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

import com.An.IM.ChatActivity;
import com.An.My.MyActivity;
import com.An.My.MyGoods;
import com.squareup.picasso.Picasso;

public class InfoActivity extends MyActivity{
	
	private String objId;
	private ImageView info_img;
	private ImageButton info_back;
	private TextView info_title,info_description,wantRent;
	private MyGoods myGoods;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		Intent a=getIntent();//from  SearchAndResultActivity
		objId=a.getStringExtra("objId");
		info_img=(ImageView)findViewById(R.id.info_img);
		info_title=(TextView)findViewById(R.id.info_title);
		info_description=(TextView)findViewById(R.id.info_description);
		info_back=(ImageButton)findViewById(R.id.info_back);
		wantRent=(TextView)findViewById(R.id.wantRent);
		info_back.setOnClickListener(l);
		wantRent.setOnClickListener(l);
		initView();

	}
	
	private void initView(){
		BmobQuery<MyGoods> query=new BmobQuery<MyGoods>();
		query.getObject(this, objId, new GetListener<MyGoods>() {
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				Toast.makeText(getApplicationContext(), "未知错误，请重试"+arg1, 0).show();
				finish();
			}
			
			@Override
			public void onSuccess(MyGoods arg0) {
				// TODO 自动生成的方法存根
				myGoods=arg0;
				Picasso.with(getApplicationContext())
					.load(myGoods.getImgUrl())
					.resize(360, 480)
					.centerCrop()
					.into(info_img);
				info_title.setText(myGoods.getTitle());
				info_description.setText(myGoods.getDescription());
				
			}
		});
	}
	OnClickListener l=new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.info_back:
				finish();
				break;
			case R.id.wantRent:
//				Toast.makeText(getApplicationContext(),myGoods.getBelongID(), 0).show();
				if (userManager.getCurrentUser() != null) {
					// 每次自动登陆的时候就需要更新下当前位置和好友的资料，因为好友的头像，昵称啥的是经常变动的
					mHandler.sendEmptyMessage(GO_CHAT);
				} else {
					mHandler.sendEmptyMessageDelayed(GO_LOGIN, 500);
				}
/*				Intent a=new Intent(getApplicationContext(),ChatActivity.class);
				a.putExtra("objId", objId);
				startActivity(a);
				finish();*/
				break;
			default:
				break;
			}
		}
	};
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_CHAT:
				Intent a=new Intent(getApplicationContext(),ChatActivity.class);
				a.putExtra("objId", objId);
				startActivity(a);
				finish();
				break;
			case GO_LOGIN:
				startActivity(new Intent(InfoActivity.this,LoginActivity.class));
				break;
			}
		}
	};
}
