package com.An.hire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.An.My.MyActivity;
import com.An.hire.R.layout;

public class ToSearchActivity extends MyActivity{
	private ListView typeList;
	private ImageButton searchCancel,clearText;
	private TextView touchToSearch,turnBack;
	private EditText textToSearch;
	private View hideView,showView;
	private SimpleAdapter typeAdapter;
	private List<Map<String,Object>> dataList;
	private int	[]pic={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,
			R.drawable.e,R.drawable.g,R.drawable.f};
	private String []type ={"手机","电脑","数码3C","家电","虚拟现实","生活用品","其他"};
	private String []example={"iPhone6  小米4 Samsung","DELL Apple Lenovo",
				"Skullcandy 铁三角  Beats","冰箱  电饭煲  微波炉","英雄联盟   Dota CF","沐浴露  杜蕾斯  2333","OTHERS"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_search);
		dataList=new ArrayList<Map<String,Object>>();
		typeList=(ListView)findViewById(R.id.type_list);
		searchCancel=(ImageButton)findViewById(R.id.searchCancel);
		touchToSearch=(TextView)findViewById(R.id.touchToSearch);
		typeAdapter=new SimpleAdapter(this,
				getData(),R.layout.item_type,new String[]{"pic","type","example"},
					new int[]{R.id.type_img,R.id.type,R.id.example});
		typeList.setAdapter(typeAdapter);
		typeList.setOnItemClickListener(listener);
		searchCancel.setOnClickListener(l);
		touchToSearch.setOnClickListener(l);
	}
	OnItemClickListener listener=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO 自动生成的方法存根
			Toast.makeText(getApplicationContext(), "别点了，没有用，没写-->"+position, 0).show();
		}
	};
	OnClickListener l=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.searchCancel:
				finish();
				break;
			case R.id.touchToSearch:
/*				typeList.setVisibility(View.GONE);
				hideView.setVisibility(View.GONE);
				showView.setVisibility(View.VISIBLE);
				textToSearch.requestFocus();*/
				startActivity(new Intent(ToSearchActivity.this
						,SearchAndResultActivity.class));
				finish();
				break;



			default:
				break;
			}
		}
	};
	private List<Map<String,Object>> getData(){
		for (int i = 0; i < type.length; i++) {
			Map<String,Object>map=new HashMap<String, Object>();
			map.put("pic", pic[i]);
			map.put("type", type[i]);
			map.put("example", example[i]);
			dataList.add(map);
			
		}
		return dataList;
	}
}
