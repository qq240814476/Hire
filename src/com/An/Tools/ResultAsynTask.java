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
private RequestCreator mPicasso;//Picasso的返回值类型
	public ResultAsynTask(Context mContext,MyGoods myGoods,ViewHolder viewHolder) {
		// TODO 自动生成的构造函数存根
		this.mContext=mContext;
		this.myGoods=myGoods;
		this.viewHolder=viewHolder;
	}
	@Override
	protected RequestCreator doInBackground(Void... params) {
		// TODO 自动生成的方法存根
		return Picasso.with(mContext)
				.load(myGoods.getImgUrl())
				.placeholder(R.drawable.isloading)
				.centerCrop()
				.resize(360,360);
	}
	
//后台操作执行前回调的函数
	@Override
	protected void onPreExecute() {
		// TODO 自动生成的方法存根
		super.onPreExecute();
		viewHolder.resultTitle.setText(myGoods.getTitle());
		Log.i("myGoods.getTitle()", myGoods.getTitle());
		viewHolder.resultRentPrice.setText(myGoods.getRentPrice());
		Log.i("myGoods.getRentPrice()", ""+myGoods.getRentPrice());
		viewHolder.resultObjectID.setText(myGoods.getMyGoodsID());
		Log.i("myGoods.getObjectId()",myGoods.getMyGoodsID());
	}
//后台操作完毕后回调的函数
	@Override
		protected void onPostExecute(RequestCreator result) {
			// TODO 自动生成的方法存根
			super.onPostExecute(result);
			result.into(viewHolder.resultImg);
		}
}
