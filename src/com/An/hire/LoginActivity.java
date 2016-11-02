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
		// TODO �Զ����ɵķ������
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
				// TODO �Զ����ɵķ������
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
									// TODO �Զ����ɵķ������
									BmobQuery<MyUser> query=new BmobQuery<MyUser>();
									query.getObject(getApplicationContext(),user.getObjectId(),new GetListener<MyUser>() {

										@Override
										public void onSuccess(MyUser arg0) {
											// TODO �Զ����ɵķ������
/*											if(arg0.getIsNew()){
												Toast.makeText(getApplicationContext(), "��½�ɹ����벹��������Ϣ", Toast.LENGTH_SHORT).show();
												Intent a=new Intent(LoginActivity.this,InfoActivity.class);
												a.putExtra("MyUser", user);
												startActivity(a);
											}
											else {
												Toast.makeText(getApplicationContext(), "��½�ɹ�", Toast.LENGTH_SHORT).show();
												Intent a=new Intent(LoginActivity.this,UserMain.class);
												startActivity(a);
											}*/
											Toast.makeText(getApplicationContext(), "��½�ɹ�", Toast.LENGTH_SHORT).show();
											/*Intent a=new Intent(LoginActivity.this,MainActivity.class);
											startActivity(a);*/
											finish();
										}

										@Override
										public void onFailure(int arg0,
												String arg1) {
											// TODO �Զ����ɵķ������
											
											Toast.makeText(getApplicationContext(), "�ӷ�������ȡ�û���Ϣʧ�ܣ�"+arg1, 0).show();
											
										}
										
									});
								}
								
								@Override
								public void onFailure(int arg0, String arg1) {
									// TODO �Զ����ɵķ������
									Toast.makeText(getApplicationContext(), "�û�������������", 1).show();
									
								}
							});
						}else{
							Toast.makeText(getApplicationContext(), "������������", 1).show();
							
						}
						}else {
							Toast.makeText(getApplicationContext(), "����������������", 1).show();
						}
					}else {
						Toast.makeText(getApplicationContext(), "�������������ֻ���", 1).show();
					}
					break;
				case R.id.toRegister:
					Intent a =new Intent(LoginActivity.this,RegisterActivity.class);
					startActivityForResult(a,LOGIN_FINISH);
					break;
				case R.id.forget:
					Toast.makeText(LoginActivity.this, "���ˣ��������Լ���", 1).show();
					break;
				default:
					break;
				}
			}
		};
@Override
public void onClick(View v) {
	// TODO �Զ����ɵķ������
	
}
/*//��ʼ�����жϻ�������Ƿ����
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
	// TODO �Զ����ɵķ������
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
	// TODO �Զ����ɵķ������
	super.onDestroy();
	Log.i("onDestroy", "Login��������");
}
}
