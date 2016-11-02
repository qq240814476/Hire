package com.An.hire;

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
import com.An.IM.ChatActivity;
import com.An.IM.ChatMessage;
import com.An.IM.ChatMessage.Type;
import com.An.My.MyActivity;
import com.An.My.MyGoods;
import com.An.My.MyUser;
import com.squareup.picasso.Picasso;

public class IndentActivity extends MyActivity{
	private Button indent_ok;
	private ImageButton indent_cancel,indent_more;
	private TextView title_in_indent,rentPrice_in_indent;
	private View item_in_chat_activity;
	private ImageView img_in_indent;
	private ListView msgList;

	private List<ChatMessage> mDatas;			
	private MyGoods mGoods;//商品信息
	private ChatMessageAdapter mAdapter;

	private MyGoods myGoods;
	private MyUser myUser;

	private String objId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_indent);
		Intent a=getIntent();
		objId=a.getStringExtra("objId");
		init();
		Do(INIT,null);
	}
	
	public void init(){
		indent_cancel=(ImageButton) findViewById(R.id.indent_cancel);
		indent_more=(ImageButton) findViewById(R.id.indent_more);
		indent_ok=(Button) findViewById(R.id.indent_ok);
		title_in_indent=(TextView) findViewById(R.id.title_in_indent);
		rentPrice_in_indent=(TextView) findViewById(R.id.rentPrice_in_indent);
		img_in_indent=(ImageView) findViewById(R.id.img_in_indent);
		msgList=(ListView) findViewById(R.id.chatListView);

		indent_cancel.setOnClickListener(l);
		indent_more.setOnClickListener(l);
		indent_ok.setOnClickListener(l);
	}
	OnClickListener l=new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.indent_cancel:
				finish();
				break;

			case R.id.indent_ok:
				ShowToast("接下来跳转支付页面，进行支付环节");
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
				if (toDoWhat==INIT){
					myGoods=arg0;
					Picasso.with(getApplicationContext())
					.load(myGoods.getImgUrl())
					.resize(360, 480)
					.centerCrop()
					.into(img_in_indent);
					title_in_indent.setText(myGoods.getTitle());
					rentPrice_in_indent.setText(myGoods.getRentPrice());
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
//		mInputMsg.setText("");

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