package com.An.hire;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.GetListener;
import cn.bmob.v3.listener.SaveListener;

import com.An.My.MyActivity;
import com.An.My.MyUser;
import com.An.Tools.NetworkUtil;

public class LoginActivity extends MyActivity implements OnClickListener{

	private Button login;
	private TextView toregister,forget,username,password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);

//		initLogin();
		setContentView(R.layout.user_login);
		login=(Button)findViewById(R.id.login);
		toregister=(TextView)findViewById(R.id.toRegister);
		forget=(TextView)findViewById(R.id.forget);
		username=(TextView)findViewById(R.id.userName);
		password=(TextView)findViewById(R.id.password);
		login.setOnClickListener(l);
		forget.setOnClickListener(l);
		toregister.setOnClickListener(l);
	}

		OnClickListener l=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				switch (v.getId()) {
				case R.id.login:
					if(!"".equals(username.getText().toString())){
						if (!"".equals(password.getText().toString())) {
							if (NetworkUtil.isAvailable(getApplicationContext())) {
							final MyUser user=new MyUser();
							user.setUsername(username.getText().toString());
							user.setPassword(password.getText().toString());
							user.login(getApplicationContext(),new SaveListener() {
								@Override
								public void onSuccess() {
									// TODO 自动生成的方法存根
									BmobQuery<MyUser> query=new BmobQuery<MyUser>();
									query.getObject(getApplicationContext(),user.getObjectId(),new GetListener<MyUser>() {

										@Override
										public void onSuccess(MyUser arg0) {
											// TODO 自动生成的方法存根
/*											if(arg0.getIsNew()){
												Toast.makeText(getApplicationContext(), "登陆成功，请补充您的信息", Toast.LENGTH_SHORT).show();
												Intent a=new Intent(LoginActivity.this,InfoActivity.class);
												a.putExtra("MyUser", user);
												startActivity(a);
											}
											else {
												Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
												Intent a=new Intent(LoginActivity.this,UserMain.class);
												startActivity(a);
											}*/
											Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
											/*Intent a=new Intent(LoginActivity.this,MainActivity.class);
											startActivity(a);*/
											finish();
										}

										@Override
										public void onFailure(int arg0,
												String arg1) {
											// TODO 自动生成的方法存根
											
											Toast.makeText(getApplicationContext(), "从服务器获取用户信息失败："+arg1, 0).show();
											
										}
										
									});
								}
								
								@Override
								public void onFailure(int arg0, String arg1) {
									// TODO 自动生成的方法存根
									Toast.makeText(getApplicationContext(), "用户名或密码有误", 1).show();
									
								}
							});
						}else{
							Toast.makeText(getApplicationContext(), "请检查网络连接", 1).show();
							
						}
						}else {
							Toast.makeText(getApplicationContext(), "请先输入您的密码", 1).show();
						}
					}else {
						Toast.makeText(getApplicationContext(), "请先输入您的手机号", 1).show();
					}
					break;
				case R.id.toRegister:
					Intent a =new Intent(LoginActivity.this,RegisterActivity.class);
					startActivityForResult(a,LOGIN_FINISH);
					break;
				case R.id.forget:
					Toast.makeText(LoginActivity.this, "忘了？哈哈，自己想", 1).show();
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
/*//初始化，判断缓存对象是否存在
private void initLogin(){
	BmobUser mUser=BmobUser.getCurrentUser(this);
	if(mUser!=null){
		Intent a=new Intent(LoginActivity.this,MainActivity.class);
		startActivity(a);
		finish();
	}
}*/
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO 自动生成的方法存根
	super.onActivityResult(requestCode, resultCode, data);
	switch (requestCode) {
	case LOGIN_FINISH:
		if (resultCode==LOGIN_FINISH) {
			
			finish();
		}
		break;

	default:
		break;
	}
}
@Override
protected void onDestroy() {
	// TODO 自动生成的方法存根
	super.onDestroy();
	Log.i("onDestroy", "Login被销毁了");
}
}
