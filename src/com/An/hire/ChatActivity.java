package com.An.hire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

import com.An.My.MyGoods;
import com.An.My.MyUser;
import com.An.base.MyActivity;
import com.squareup.picasso.Picasso;

public class ChatActivity extends MyActivity{
	private ImageButton chat_back,send;
	private Button wantRent;
	private TextView title_in_chat,rentPrice_in_chat;
	private EditText inputEdit;
	private View item_in_chat_activity;
	private ImageView img_in_chat;
	
	private MyGoods myGoods;
	private MyUser toUser;
	private String toUserId;
	
	private String objId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acticvity_chat);
		Intent a=getIntent();
		objId=a.getStringExtra("objId");
		ShowToast("objId="+objId);
		init();
		initView();
		
	}
	public void init(){
		chat_back=(ImageButton) findViewById(R.id.chat_back);
		send=(ImageButton) findViewById(R.id.send);
		wantRent=(Button) findViewById(R.id.wantRent);
		title_in_chat=(TextView) findViewById(R.id.title_in_chat);
		rentPrice_in_chat=(TextView) findViewById(R.id.rentPrice_in_chat);
		inputEdit=(EditText) findViewById(R.id.inputEdit);
		item_in_chat_activity=findViewById(R.id.item_in_chat_activity);
		img_in_chat=(ImageView) findViewById(R.id.img_in_chat);
		chat_back.setOnClickListener(l);
		wantRent.setOnClickListener(l);
		send.setOnClickListener(l);
		item_in_chat_activity.setOnClickListener(l);
	}
	OnClickListener l=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.chat_back:
				finish();
				break;
			case R.id.wantRent:
				ShowToast("下订单");
				break;
			case R.id.send:
				ShowToast("发送了");
				break;
			case R.id.item_in_chat_activity:
				Intent a=new Intent(getApplicationContext(),InfoActivity.class);
				a.putExtra("objId", objId);
				startActivity(a);
				finish();
				break;

			default:
				break;
			}
		}
	};
	
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
					.into(img_in_chat);
				title_in_chat.setText(myGoods.getTitle());
				rentPrice_in_chat.setText(myGoods.getRentPrice());
				toUserId=myGoods.getBelongID();
				ShowToast(toUserId);
			}
		});
	}
}
