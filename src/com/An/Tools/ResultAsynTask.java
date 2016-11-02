package com.An.Tools;

import android.animation.IntArrayEvaluator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.An.Adapter.ResultAdapter.ViewHolder;
import com.An.My.MyGoods;
import com.An.hire.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class ResultAsynTask extends AsyncTask<Void, Void, RequestCreator>{
private Context mContext;
private MyGoods myGoods;
private ViewHolder viewHolder;
private RequestCreator mPicasso;//Picasso�ķ���ֵ����
	public ResultAsynTask(Context mContext,MyGoods myGoods,ViewHolder viewHolder) {
		// TODO �Զ����ɵĹ��캯�����
		this.mContext=mContext;
		this.myGoods=myGoods;
		this.viewHolder=viewHolder;
	}
	@Override
	protected RequestCreator doInBackground(Void... params) {
		// TODO �Զ����ɵķ������
		return Picasso.with(mContext)
				.load(myGoods.getImgUrl())
				.placeholder(R.drawable.isloading)
				.centerCrop()
				.resize(360,360);
	}
	
//��̨����ִ��ǰ�ص��ĺ���
	@Override
	protected void onPreExecute() {
		// TODO �Զ����ɵķ������
		super.onPreExecute();
		viewHolder.resultTitle.setText(myGoods.getTitle());
		Log.i("myGoods.getTitle()", myGoods.getTitle());
		viewHolder.resultRentPrice.setText(myGoods.getRentPrice());
		Log.i("myGoods.getRentPrice()", ""+myGoods.getRentPrice());
		viewHolder.resultObjectID.setText(myGoods.getMyGoodsID());
		Log.i("myGoods.getObjectId()",myGoods.getMyGoodsID());
	}
//��̨������Ϻ�ص��ĺ���
	@Override
		protected void onPostExecute(RequestCreator result) {
			// TODO �Զ����ɵķ������
			super.onPostExecute(result);
			result.into(viewHolder.resultImg);
		}
}
