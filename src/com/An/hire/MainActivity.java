package com.An.hire;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.An.Fragment.Home;
import com.An.Fragment.Interest;
import com.An.Fragment.Me;
import com.An.Fragment.Message;
import com.An.My.MyFragmentActivity;

public class MainActivity extends MyFragmentActivity {

	private FragmentTabHost mTabHost;
	private LayoutInflater mLayoutInflater;
	private Class mFragmentArray[] = { Home.class, Interest.class,
			 Message.class, Me.class };
	private int mImageArray[] = { R.drawable.tab_home_mybtn,
			R.drawable.tab_interest_mybtn, 
			R.drawable.tab_message_mybtn,
			R.drawable.tab_me_mybtn };
	private String mTextArray[] = { "首页", "兴趣", "消息", "我的" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		mLayoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		// 找到TabHost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		mTabHost.getTabWidget().setDividerDrawable(null);//删除掉FragmentTabHost的分割线
		// 得到fragment的个数
		int count = mFragmentArray.length;
		for (int i = 0; i < count; i++) {
			// 给每个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
					.setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, mFragmentArray[i], null);
			// 设置Tab按钮的背景
			mTabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(R.drawable.selector_tab_background);
		}
	}

	private View getTabItemView(int index) {
		View view = mLayoutInflater.inflate(R.layout.tab_item_view, null);
		ImageView imageView=(ImageView)view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageArray[index]);
		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTextArray[index]);

		return view;

	}
	

}