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
			// TODO �Զ����ɵķ������
			switch (v.getId()) {
			case R.id.submit:
				if (MyActivity.userManager.getCurrentUser() != null) {
					// ÿ���Զ���½��ʱ�����Ҫ�����µ�ǰλ�úͺ��ѵ����ϣ���Ϊ���ѵ�ͷ���ǳ�ɶ���Ǿ����䶯��
					mHandler.sendEmptyMessage(GO_SUBMIT);
				} else {
					mHandler.sendEmptyMessageDelayed(GO_LOGIN, 500);
					Toast.makeText(getActivity(), "���ȵ�¼", 0).show();;
				}
//				startActivity(new Intent(getActivity(),SubmitActivity.class));
				
				break;
			case R.id.out:
				MyUser.logOut(getActivity());
				Toast.makeText(getActivity(), "�������ǰ���󻺴�", 0).show();
				isLogined();
//				Intent a =new Intent(getActivity(),LoginActivity.class);
//				startActivity(a);
//				getActivity().finish();				
				break;
			case R.id.ISubmited:
				
				break;
			case R.id.myMessageLayout:
				if (title_my_message.getText().equals("���ȵ�¼")) {
					Intent toSubmit=new Intent(getActivity()
								,LoginActivity.class);
					startActivity(toSubmit);
				}else if(title_my_message.getText().equals("������Ϣ")){
					Toast.makeText(getActivity(), "������Ϣҳ", 0).show();
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
			title_my_message.setText("���ȵ�¼");
			out.setVisibility(View.GONE);
		}else {
			title_my_message.setText("������Ϣ");
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