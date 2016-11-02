package com.An.Fragment;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.im.BmobChatManager;
import cn.bmob.im.BmobUserManager;

import com.An.IM.ChatActivity;
import com.An.My.MyActivity;
import com.An.My.MyUser;
import com.An.hire.InfoActivity;
import com.An.hire.LoginActivity;
import com.An.hire.R;
import com.An.hire.SubmitActivity;

public class Me extends Fragment{
	private ImageButton submit;
	private Button out;
	private View mView,ISubmited,myMessageLayout;
	private TextView title_my_message;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView=inflater.inflate(R.layout.fragment_me, container, false);
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		submit=(ImageButton)mView.findViewById(R.id.submit);
		myMessageLayout=mView.findViewById(R.id.myMessageLayout);
		title_my_message=(TextView) mView.findViewById(R.id.title_my_message);

		out=(Button)mView.findViewById(R.id.out);
		ISubmited=mView.findViewById(R.id.ISubmited);
		submit.setOnClickListener(l);
		out.setOnClickListener(l);
		ISubmited.setOnClickListener(l);
		myMessageLayout.setOnClickListener(l);

	}
	OnClickListener l=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.submit:
				if (MyActivity.userManager.getCurrentUser() != null) {
					// 每次自动登陆的时候就需要更新下当前位置和好友的资料，因为好友的头像，昵称啥的是经常变动的
					mHandler.sendEmptyMessage(GO_SUBMIT);
				} else {
					mHandler.sendEmptyMessageDelayed(GO_LOGIN, 500);
					Toast.makeText(getActivity(), "请先登录", 0).show();;
				}
//				startActivity(new Intent(getActivity(),SubmitActivity.class));
				
				break;
			case R.id.out:
				MyUser.logOut(getActivity());
				Toast.makeText(getActivity(), "已清除当前对象缓存", 0).show();
				isLogined();
//				Intent a =new Intent(getActivity(),LoginActivity.class);
//				startActivity(a);
//				getActivity().finish();				
				break;
			case R.id.ISubmited:
				
				break;
			case R.id.myMessageLayout:
				if (title_my_message.getText().equals("请先登录")) {
					Intent toSubmit=new Intent(getActivity()
								,LoginActivity.class);
					startActivity(toSubmit);
				}else if(title_my_message.getText().equals("个人信息")){
					Toast.makeText(getActivity(), "个人信息页", 0).show();
				}
				break;

			default:
				break;
			}
			
		}
	};
	private void isLogined(){
		BmobUserManager userManager = BmobUserManager.getInstance(getActivity());
		if (userManager.getCurrentUser() == null) {
			title_my_message.setText("请先登录");
			out.setVisibility(View.GONE);
		}else {
			title_my_message.setText("个人信息");
			out.setVisibility(View.VISIBLE);
		}
	}
	
	private final int GO_LOGIN=1;
	private final int GO_SUBMIT=2;
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_SUBMIT:
				Intent a=new Intent(getActivity(),SubmitActivity.class);
				startActivity(a);
//				getActivity().finish();
				break;
			case GO_LOGIN:
				startActivity(new Intent(getActivity(),LoginActivity.class));
				break;
			}
		}
	};
	public void onResume() {
		super.onResume();
		isLogined();
	};
}