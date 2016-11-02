package com.An.Adapter;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.An.My.MyGoods;
import com.An.Tools.ResultAsynTask;
import com.An.hire.R;

public class ResultAdapter extends BaseAdapter{
	private List<MyGoods> mList;
	private Context mContext;
	private LayoutInflater mInflater;
	private GridView mGridView;
	
	private HashMap<Integer, View> viewMap;
	
	public ResultAdapter(Context context,List<MyGoods> list,GridView mGridview) {
	// TODO �Զ����ɵĹ��캯�����
		mContext=context;
		mList=list;
		this.mGridView=mGridview;
		mInflater=LayoutInflater.from(context);
}
	@Override
	public int getCount() {
		// TODO �Զ����ɵķ������
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO �Զ����ɵķ������
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO �Զ����ɵķ������
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO �Զ����ɵķ������
		
		ViewHolder viewHolder;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView=mInflater.inflate(R.layout.item_result, null);
			viewHolder.resultImg=(ImageView)convertView.findViewById(R.id.resultImg);
			viewHolder.resultTitle=(TextView)convertView.findViewById(R.id.resultTitle);
			viewHolder.resultRentPrice=(TextView)convertView.findViewById(R.id.resultRentPrice);
			viewHolder.resultObjectID=(TextView)convertView.findViewById(R.id.resultObjectID);
			convertView.setTag(viewHolder);
		}else {
			viewHolder=(ViewHolder)convertView.getTag();
		}
		MyGoods myGoods=mList.get(position);
		//��������-->
		new ResultAsynTask(mContext, myGoods, viewHolder).execute();
//		viewHolder.resultTitle.setText(myGoods.getTitle());
//		viewHolder.resultRentPrice.setText(myGoods.getRentPrice());
//		Picasso.with(mContext).load(myGoods.getImgUrl()).placeholder(R.drawable.loading).into(viewHolder.resultImg);
		//<---��������
		return convertView;
	}
	public class ViewHolder{
		public ImageView resultImg;
		public TextView resultTitle;
		public TextView resultRentPrice;
		public TextView resultObjectID;
	}
}
