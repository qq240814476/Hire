package com.An.IM;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.im.bean.BmobChatUser;
import cn.bmob.im.bean.BmobMsg;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.RequestSMSCodeListener;

import com.An.Adapter.ChatMessageAdapter;
import com.An.IM.ChatMessage.Type;
import com.An.My.MyActivity;
import com.An.My.MyGoods;
import com.An.My.MyUser;
import com.An.hire.IndentActivity;
import com.An.hire.InfoActivity;
import com.An.hire.R;
import com.squareup.picasso.Picasso;

public class ChatActivity extends MyActivity{
	private ImageButton chat_back,send;
	private Button wantRent;
	private TextView title_in_chat,rentPrice_in_chat;
	private EditText inputEdit;
	private View item_in_chat_activity;
	private ImageView img_in_chat;
	private EditText mInputMsg;
	private ListView msgList;

	private List<ChatMessage> mDatas;			
	private MyGoods mGoods;//商品信息
	private ChatMessageAdapter mAdapter;

	private MyGoods myGoods;
	private MyUser myUser;

	private String objId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		Intent a=getIntent();
		objId=a.getStringExtra("objId");
		init();
		Do(INIT,null);
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
		mInputMsg=(EditText)findViewById(R.id.inputEdit);
		msgList=(ListView) findViewById(R.id.chatListView);

		chat_back.setOnClickListener(l);
		wantRent.setOnClickListener(l);
		send.setOnClickListener(l);
		item_in_chat_activity.setOnClickListener(l);
		
		initDatas();
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
				Intent toIndent=new Intent(ChatActivity.this,IndentActivity.class);
				toIndent.putExtra("objId", objId);
				startActivity(toIndent);
				finish();
				break;
			case R.id.send:	
				final String toMsg = mInputMsg.getText().toString();
				if (TextUtils.isEmpty(toMsg))
				{
					Toast.makeText(ChatActivity.this, "发送消息不能为空！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				Do(SEND_MSG, toMsg);
				if (msgList.getCount()==0) {
					Do(SEND_SMS, toMsg);
				}
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

	private void Do(final int toDoWhat,final String msg){
		BmobQuery<MyGoods> query=new BmobQuery<MyGoods>();
		query.getObject(this, objId, new GetListener<MyGoods>() {
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				Log.i("=========", arg0+"haha "+arg1);
				Toast.makeText(getApplicationContext(), "未知错误，请重试"+arg1, 0).show();
				finish();
			}

			@Override
			public void onSuccess(MyGoods arg0) {
				// TODO 自动生成的方法存根
				if (toDoWhat==INIT) {
					myGoods=arg0;
					Picasso.with(getApplicationContext())
					.load(myGoods.getImgUrl())
					.resize(360, 480)
					.centerCrop()
					.into(img_in_chat);
					title_in_chat.setText(myGoods.getTitle());
					rentPrice_in_chat.setText(myGoods.getRentPrice());
				}else if(toDoWhat==SEND_MSG){
					Log.i("=-=-=-=-=SEND_MSG","成功了");
					sendMsg(myGoods.getBelongID(),msg);
				}else if (toDoWhat==SEND_SMS) {
					Log.i("=-=-=-=-=SEND_SMS","成功了");
					sendSMS(myGoods.getBelongID(),msg);
					//需要短信服务时候取消注释
				}
			}
		});
	}

	public void sendSMS(String toUserId,final String msg){
		userManager.queryUserById(toUserId,new FindListener<BmobChatUser>() {
			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				ShowToast(arg0+"出错了"+arg1);
				Log.i("ERROR", arg1);
			}
			@Override
			public void onSuccess(List<BmobChatUser> arg0) {
				// TODO 自动生成的方法存根
				if (arg0 != null && arg0.size() > 0) {		
					BmobSMS.requestSMS(getApplicationContext(), arg0.get(0).getMobilePhoneNumber()	
							, "电话号码"+userManager.getCurrentUserName()+"想要租您的宝贝,给您发送如下消息："+msg,"0",
							new RequestSMSCodeListener() {
						@Override
						public void done(Integer arg0, BmobException arg1) {
							// TODO 自动生成的方法存根
							ShowLog("arg0="+arg0);
							ShowToast("arg0="+arg0+"arg1"+arg1);
						}
					});
				} else {
					Log.i("userName","onSuccess 查无此人");
					ShowLog("onSuccess 查无此人");
					ShowToast("onSuccess 查无此人");
				}
			}
		});
	}
	private void sendMsg(String toUserId,String msg){
		ChatMessage toMessage = new ChatMessage();
		toMessage.setMsg(msg);
		toMessage.setType(Type.OUTCOMING);
		mDatas.add(toMessage);
		mAdapter.notifyDataSetChanged();
		msgList.setSelection(mDatas.size()-1);
		mInputMsg.setText("");

		final BmobMsg fromMessage = BmobMsg.createTextSendMsg(
				getApplicationContext(),toUserId, msg);
		Log.i("manager", (manager==null)+"");
		Log.i("fromMessage", (fromMessage==null)+"");
		userManager.queryUserById(toUserId,new FindListener<BmobChatUser>() {

			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				ShowToast(arg0+"出错了"+arg1);
				Log.i("ERROR", arg1);
			}
			@Override
			public void onSuccess(List<BmobChatUser> arg0) {
				// TODO 自动生成的方法存根
				if (arg0 != null && arg0.size() > 0) {
//					ShowToast(arg0.get(0).getUsername());
					Log.i("userName", arg0.get(0).getUsername());
					manager.sendTextMessage(arg0.get(0), fromMessage);

				} else {
					Log.i("userName","onSuccess 查无此人");
					ShowLog("onSuccess 查无此人");
					ShowToast("onSuccess 查无此人");
				}
			}
		});
	}
	private void initDatas()
	{
		mDatas = new ArrayList<ChatMessage>();
//		mDatas.add(new ChatMessage());
		mAdapter = new ChatMessageAdapter(this, mDatas);
		msgList.setAdapter(mAdapter);
	}
	
	
	
	public void getToUserInfo(String toUserId){
		userManager.queryUserById(toUserId,new FindListener<BmobChatUser>() {

			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				ShowToast(arg0+"出错了"+arg1);
				Log.i("ERROR", arg1);
			}
			@Override
			public void onSuccess(List<BmobChatUser> arg0) {
				// TODO 自动生成的方法存根
				if (arg0 != null && arg0.size() > 0) {
					myUser = (MyUser) arg0.get(0);				
				} else {
					Log.i("userName","onSuccess 查无此人");
					ShowLog("onSuccess 查无此人");
					ShowToast("onSuccess 查无此人");
				}

			}
		});
	}
	
}
