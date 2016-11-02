package com.An.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import cn.bmob.im.bean.BmobRecent;
import cn.bmob.im.db.BmobDB;
import cn.bmob.im.db.DBConfig;

import com.An.Adapter.MsgAdapter;
import com.An.hire.R;

public class Message extends Fragment{
	
	private ImageButton locations,type;
	MsgAdapter msgAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_message, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initList();

	}
	private void initList(){
		final ListView msg_list=(ListView)getView().findViewById(R.id.msg_list);
		msg_list.setOnItemClickListener(itemClickListener);
		msg_list.setOnScrollListener(onScrollListener);
		msg_list.setOnItemLongClickListener(onItemLongClick);
		DBConfig mDbConfig=new DBConfig();
		mDbConfig.setContext(getActivity());
		msgAdapter = new MsgAdapter(getActivity(), R.layout.item_msg, BmobDB.create(getActivity()).queryRecents());
		msg_list.setAdapter(msgAdapter);
		msgAdapter.notifyDataSetChanged();
	}
	OnItemClickListener itemClickListener=new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO 自动生成的方法存根
			BmobRecent recent = msgAdapter.getItem(position);
			//重置未读消息
//			BmobDB.create(getActivity()).resetUnread(recent.getTargetid());
			//组装聊天对象
//			MyUser user = new MyUser();
//			user.setUsername(recent.getUserName());
//			user.setObjectId(recent.getTargetid());
			Toast.makeText(getActivity(), "该用户NAME值："+recent.getUserName(), 0).show();
//			Toast.makeText(getActivity(), "该用户NAME值："+user.getUsername(), 0).show();
//			Toast.makeText(getActivity(), user.getObjectId(), 0).show();
//			Intent intent = new Intent(getActivity(), ChatActivity.class);
//			intent.putExtra("user", user);
//			intent.putExtra("flag", "message");
//			startActivity(intent);
		}
	};
	OnScrollListener onScrollListener=new OnScrollListener() {
		
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// TODO 自动生成的方法存根
			
		}
		
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// TODO 自动生成的方法存根
			
		}
	};
	OnClickListener l=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			ImageButton btn=(ImageButton)v;
			switch (btn.getId()) {


			default:
				break;
			}
		}
	};
	OnItemLongClickListener onItemLongClick=new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO 自动生成的方法存根
			BmobRecent recent = msgAdapter.getItem(position);
			Toast.makeText(getActivity(), "即将删除该会话", 0).show();
			try {
				Thread.sleep(1500);
				deleteRecent(recent);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			return true;
		}
	};


	/** 删除会话
	  * deleteRecent
	  * @param @param recent 
	  * @return void
	  * @throws
	  */
	private void deleteRecent(BmobRecent recent){
		msgAdapter.remove(recent);
		BmobDB.create(getActivity()).deleteRecent(recent.getTargetid());
		BmobDB.create(getActivity()).deleteMessages(recent.getTargetid());
	}
	
	
	
	
}