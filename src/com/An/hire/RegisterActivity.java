package com.An.hire;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;

import com.An.My.MyActivity;
import com.An.My.MyUser;

public class RegisterActivity extends MyActivity implements OnClickListener{

	private Button register;
	private TextView phoneNum,password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_register);
		initView();
	}

	private boolean initView(){
		register=(Button)findViewById(R.id.register);
		phoneNum=(TextView)findViewById(R.id.phoneNum);
		password=(TextView)findViewById(R.id.password);
		register.setOnClickListener(l);
		return true;
	}
		OnClickListener l=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Button btn=(Button)v;
				switch (btn.getId()) {
				
				case R.id.register:
					if(!"".equals(phoneNum.getText().toString())){
						if (!"".equals(password.getText().toString())) {
							MyUser user=new MyUser();
							user.setUsername(phoneNum.getText().toString());
							user.setMobilePhoneNumber(phoneNum.getText().toString());
							user.setPassword(password.getText().toString());
							user.setName("Hirer--"+phoneNum.getText().toString());
							user.setNew(true);
							user.signUp(getApplicationContext(), new SaveListener() {
								
								@Override
								public void onSuccess() {
									// TODO 自动生成的方法存根
									Toast.makeText(getApplicationContext(), "注册成功，已为您自动登陆本账号。", 1).show();
									setResult(LOGIN_FINISH);
									finish();
								}
								
								@Override
								public void onFailure(int arg0, String arg1) {
									// TODO 自动生成的方法存根
									switch (arg0) {
									case 301:
										Toast.makeText(getApplicationContext(), "请输入正确的手机号码", 1).show();
										
										break;
									case 202:
										Toast.makeText(getApplicationContext(), "号码："+phoneNum.getText().toString()+"已被注册，您可尝试通过\"忘记密码\"找回", 1).show();
										
										break;

									default:
										Toast.makeText(getApplicationContext(), arg0+"-注册失败："+arg1, 1).show();
										break;
									}
								}
							});
						}else {
							Toast.makeText(getApplicationContext(), "请先输入您的密码", 1).show();
						}
					}else {
						Toast.makeText(getApplicationContext(), "请先输入您的手机号", 1).show();
					}
					break;

				default:
					break;
				}
			}
		};
@Override
public void onClick(View v) {
	// TODO 自动生成的方法存根
	
}



}
