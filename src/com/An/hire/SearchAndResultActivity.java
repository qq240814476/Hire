package com.An.hire;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.An.Adapter.ResultAdapter;
import com.An.My.MyActivity;
import com.An.My.MyGoods;

public class SearchAndResultActivity extends MyActivity{
	private ImageButton back,clearText;
	private TextView rightText;
	private EditText textToSearch;
	private GridView resultGrid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_and_result);
		back=(ImageButton)findViewById(R.id.back);
		clearText=(ImageButton)findViewById(R.id.clearText);
		rightText=(TextView)findViewById(R.id.rightText);
		textToSearch=(EditText)findViewById(R.id.textToSearch);
//		initList(1);				
		back.setOnClickListener(l);
		clearText.setOnClickListener(l);
		rightText.setOnClickListener(l);
	}
	OnClickListener l=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.back:
				startActivity(new Intent(SearchAndResultActivity.this
						,ToSearchActivity.class));
				finish();
				break;
			case R.id.rightText:
				if(rightText.getText().toString().equals("搜索")
						&&!TextUtils.isEmpty(textToSearch.getText())){
//				rightText.setText("筛选");
				//当textToSearch获取到焦点时应该改成“搜索”，现在有点bug
				queryList(1);
				
				}else if (TextUtils.isEmpty(textToSearch.getText())) {
					Toast.makeText(getApplicationContext(), "并没有输入任何东西", 0).show();
				}
				else if(rightText.getText().toString().equals("筛选")){
				Toast.makeText(getApplicationContext(), "并不知道应该筛选什么内容", 0).show();
					
				}
				
				break;
			case R.id.clearText:
				textToSearch.setText("");
				rightText.setText("搜索");
				
				break;

			default:
				break;
			}
		}
	};
	private void initList(int Code){
//		Toast.makeText(getApplicationContext(), "initList了，什么鬼？", 0).show();
		Log.i("=====", "1");
		final GridView resultList=(GridView)findViewById(R.id.resultList);
		Log.i("=====", "2");

//		resultList.setOnItemClickListener(it);
//		resultList.setOnScrollListener(s);
		BmobQuery<MyGoods> mBmobQuery=new BmobQuery<MyGoods>();
		mBmobQuery.findObjects(getApplicationContext(), new FindListener<MyGoods>() {
			@Override
			public void onSuccess(List<MyGoods> arg0) {
				// TODO 自动生成的方法存根
				List<MyGoods> itemBeanList=new ArrayList<MyGoods>();
				Toast.makeText(getApplicationContext(), "刷新了"+arg0.size()+"条数据", 0).show();
				for (MyGoods myGoods:arg0) {
					itemBeanList.add(new MyGoods(myGoods.getImgUrl(), 
							myGoods.getTitle(), myGoods.getRentPrice(),myGoods.getBelongID()));
				}

				ResultAdapter myAdapter=new ResultAdapter(getApplicationContext(), itemBeanList,resultList);
				resultList.setAdapter(myAdapter);
				myAdapter.notifyDataSetChanged();
			}
			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根

				
				Log.i("错误", "调用了onError"+arg1);
				Toast.makeText(getApplicationContext(), "请检查网络连接后重试:"+arg1, 1).show();
			}
		});
		if (Code==2) {
			resultList.setStackFromBottom(true);
		}
		Log.i("=====", "9");

	}
	private void queryList(int Code){
		Log.i("=====", "1");
		final GridView resultList=(GridView)findViewById(R.id.resultList);
		Log.i("=====", "2");

		resultList.setOnItemClickListener(it);
//		resultList.setOnScrollListener(s);
		BmobQuery<MyGoods> mBmobQuery=new BmobQuery<MyGoods>();
		mBmobQuery.addWhereContains("Title", textToSearch.getText().toString());
		mBmobQuery.findObjects(getApplicationContext(), new FindListener<MyGoods>() {
			@Override
			public void onSuccess(List<MyGoods> arg0) {
				// TODO 自动生成的方法存根
				if (arg0.size()==0) {
					Toast.makeText(getApplicationContext(), "对不起，没有找到相关内容", 0).show();
				}
				List<MyGoods> itemBeanList=new ArrayList<MyGoods>();
				Toast.makeText(getApplicationContext(), "刷新了"+arg0.size()+"条数据", 0).show();
				for (MyGoods myGoods:arg0) {
					itemBeanList.add(new MyGoods(myGoods.getImgUrl(), 
							myGoods.getTitle(), myGoods.getRentPrice(),myGoods.getObjectId()));
				}

				ResultAdapter myAdapter=new ResultAdapter(getApplicationContext(), itemBeanList,resultList);
				resultList.setAdapter(myAdapter);
				myAdapter.notifyDataSetChanged();
			}
			@Override
			public void onError(int arg0, String arg1) {
				// TODO 自动生成的方法存根

				
				Log.i("错误", "调用了onError"+arg1);
				Toast.makeText(getApplicationContext(), "请检查网络连接后重试:"+arg1, 1).show();
			}
		});
		if (Code==2) {
			resultList.setStackFromBottom(true);
		}

	}
	OnItemClickListener it=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO 自动生成的方法存根
			TextView objId=(TextView)view.findViewById(R.id.resultObjectID);
			Toast.makeText(getApplicationContext(), "objId=="+objId.getText().toString(), 1).show();
			Intent toItemInfo=new Intent(getApplicationContext(),InfoActivity.class);
			toItemInfo.putExtra("objId", objId.getText().toString());
			startActivity(toItemInfo);
		}
	};

}
