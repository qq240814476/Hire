package com.An.Adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.An.IM.ChatMessage;
import com.An.IM.ChatMessage.Type;
import com.An.baseAdapter.BaseListAdapter;
import com.An.hire.R;

public class ChatMessageAdapter extends BaseListAdapter<ChatMessage>{
	private LayoutInflater mInflater;
	private List<ChatMessage> mDatas;

	public ChatMessageAdapter(Context context, List<ChatMessage> mDatas)
	{
		super(context, mDatas);
		mInflater = LayoutInflater.from(context);
		this.mDatas = mDatas;
	}

	@Override
	public int getCount()
	{
		return mDatas.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public int getItemViewType(int position)
	{
		ChatMessage chatMessage = mDatas.get(position);
		if (chatMessage.getType() ==Type.INCOMING)
		{
			return 0;
		}
		return 1;
	}

	@Override
	public int getViewTypeCount()
	{
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ChatMessage chatMessage = mDatas.get(position);
		ViewHolder viewHolder = null;
		if (convertView == null)
		{
			// 通过ItemType设置不同的布局
			if (getItemViewType(position) == 0)
			{
				convertView = mInflater.inflate(R.layout.item_chat_his, parent,
						false);
				viewHolder = new ViewHolder();
				viewHolder.mMsg = (TextView) convertView
						.findViewById(R.id.hisText);
			} else
			{
				convertView = mInflater.inflate(R.layout.item_chat_my, parent,
						false);
				viewHolder = new ViewHolder();
				viewHolder.mMsg = (TextView) convertView
						.findViewById(R.id.myText);
			}
			convertView.setTag(viewHolder);
		} else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// 设置数据
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		viewHolder.mDate.setText(df.format(chatMessage.getDate()));
		viewHolder.mMsg.setText(chatMessage.getMsg());
		return convertView;
	}

	private final class ViewHolder
	{
//		TextView mDate;
		TextView mMsg;
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		// TODO 自动生成的方法存根
		return null;
	}

}