package com.An.hire;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;

import com.An.My.MyActivity;
import com.An.My.MyGoods;
import com.An.My.MyUser;
import com.An.Other.RotatePic;
import com.bmob.BTPFileResponse;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;

//   发布界面   activity
public class SubmitActivity extends MyActivity{
	private ImageButton submit_addImg;
	private Button submit;
	private Uri mUri;
	private File img=null;
	private TextView submit_title,submit_description,realPrice,rentPrice;
	private ImageButton cancel,rotate;
	private Canvas canvas;
	private RotatePic pic;
	private Matrix matrix=new Matrix();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit);
		cancel=(ImageButton)findViewById(R.id.cancel);
		submit_addImg=(ImageButton)findViewById(R.id.submit_addImg);
		rotate=(ImageButton)findViewById(R.id.submit_rotate);
		rotate.setVisibility(View.GONE);
		submit=(Button)findViewById(R.id.mySubmit);
		submit_title=(TextView)findViewById(R.id.submit_title);
		submit_description=(TextView)findViewById(R.id.submit_description);
		realPrice=(TextView)findViewById(R.id.realPrice);
		rentPrice=(TextView)findViewById(R.id.rentPrice);
		
		submit.setOnClickListener(l);
		submit_addImg.setOnClickListener(l);
		cancel.setOnClickListener(l);
		rotate.setOnClickListener(l);
	}
	private void initView(){
		submit_title.setText("");
		submit.setText("确认发布");
		submit_description.setText("");
		realPrice.setText("");
		rentPrice.setText("");
		submit_addImg.setImageResource(R.drawable.jiahao);
	}
	
	OnClickListener l=new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.mySubmit:

				if (!TextUtils.isEmpty(submit_title.getText())&&
						!TextUtils.isEmpty(submit_description.getText())&&
							!TextUtils.isEmpty(realPrice.getText())&&
								!TextUtils.isEmpty(rentPrice.getText())&&
									(img!=null)) {

					
							BTPFileResponse response =BmobProFile.getInstance(SubmitActivity.this)
									.upload(img.getAbsolutePath(),new UploadListener() {

								@Override
								public void onError(int arg0, String arg1) {
									// TODO 自动生成的方法存根
									Toast.makeText(SubmitActivity.this, arg0+"失败了"+arg1, 0).show();
									submit.setClickable(true);

								}
								@Override
								public void onSuccess(String arg0, String arg1, BmobFile arg2) {
									// TODO 自动生成的方法存根
									saveItem(arg2.getUrl());
									submit.setClickable(true);
									submit.setBackgroundResource(R.drawable.my_button);


								}
								@Override
								public void onProgress(int arg0) {
									// TODO 自动生成的方法存根
									submit.setClickable(false);
									submit.setText("正在发布，请稍等……");
									submit.setBackgroundResource(R.drawable.my_button_pressed);
								}
							});

				}else {
					Toast.makeText(SubmitActivity.this, "请先补全信息", 0).show();
				}
				break;
			
			case R.id.submit_addImg:
	/*							Intent p=new Intent();
								p.setType("image/*");
								p.setAction(Intent.ACTION_GET_CONTENT);
								startActivityForResult(p, 2);*/
				Intent cam=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				String filePath=Environment.getExternalStorageDirectory().toString()+"/rentDemoImg";
				File path1=new File(filePath);
				if(!path1.exists()){
					path1.mkdirs();
				}
				img=new File(path1,System.currentTimeMillis()+".jpg");	
				mUri=Uri.fromFile(img);
				cam.putExtra(MediaStore.EXTRA_OUTPUT,mUri);
				startActivityForResult(cam, 1);
				break;
			case R.id.cancel:
				finish();
				break;
			case R.id.submit_rotate:
				ShowToast("旋转");
				matrix.postRotate(90);
				Bitmap bitmap = getScaleBitmap(img.getPath(), 0, 0);  
				saveScalePhoto(bitmap,img.getPath());  
				submit_addImg.setImageBitmap(bitmap); 
				matrix.postRotate(-90);
				break;

			default:
				break;
			}

		}
	};
	public void onActivityResult(int requestCode,int resultCode,Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==1) {
			if(resultCode==-1){
				Bitmap bitmap = getScaleBitmap(img.getPath(), 0, 0);  
				saveScalePhoto(bitmap,img.getPath());  
				submit_addImg.setImageBitmap(bitmap); 
			}
			else if(resultCode==0){
				Toast.makeText(SubmitActivity.this, "放弃操作", 1).show();
			}
		}
/*				else if (requestCode==2) {
					if(resultCode==-1){
						Uri uri=data.getData();
						Toast.makeText(SubmitActivity.this, "uri="+uri, 1).show();
						System.out.println("uri="+uri);
					}
					else if(resultCode==0){
						Toast.makeText(SubmitActivity.this, "放弃操作", 1).show();
					}
				}*/
	}
	private Bitmap getScaleBitmap(String url, double width, double height){
		//   拍照  获取照片
		BitmapFactory.Options options = new BitmapFactory.Options();  
        options.inJustDecodeBounds = true; // 设置了此属性一定要记得将值设置为false  
        Bitmap bitmap = BitmapFactory.decodeFile(url);  
        // 防止OOM发生  
        options.inJustDecodeBounds = false;
		int mWidth=bitmap.getWidth();
		int mHeight=bitmap.getHeight();
		ShowToast(mWidth+","+mHeight);
	    
		float scaleWidth=0.7f;
		float scaleHeight=0.7f;
		rotate.setVisibility(View.VISIBLE);
		
//		if(mWidth <= mHeight) {  
////            scaleWidth = (float) (width/mWidth);  
////            scaleHeight = (float) (height/mHeight); 
//            ShowToast("竖屏图片，w&h-->"+scaleWidth+"&"+scaleHeight);
//		} else {  
//            //matrix.postRotate(90); /* 翻转90度 */  
////            scaleWidth = (float) (height/mWidth);  
////            scaleHeight = (float) (width/mHeight);  
//            ShowToast("横屏图片，w&h-->"+scaleWidth+"&"+scaleHeight);
//        }  
		//matrix.postScale(1,1);
		Bitmap reBitmap=Bitmap.createBitmap(bitmap, 0, 0, mWidth, mHeight,matrix,true);
		bitmap.recycle();
		return reBitmap;
	}
	private void saveItem(String imgUrl){
		/**/
		MyUser myUser=BmobUser.getCurrentUser(SubmitActivity.this,MyUser.class);
		
		Toast.makeText(SubmitActivity.this, myUser.getObjectId(), 0).show();
		
		final MyGoods myGoods=new MyGoods();
		myGoods.setBelongID(myUser.getObjectId());
		myGoods.setTitle(submit_title.getText().toString());
		myGoods.setDescription(submit_description.getText().toString());
		myGoods.setRealPrice(realPrice.getText().toString());
		myGoods.setRentPrice(rentPrice.getText().toString());
		myGoods.setImgUrl(imgUrl);
		myGoods.save(SubmitActivity.this, new SaveListener() {

			@Override
			public void onSuccess() {
				// TODO 自动生成的方法存根
				Toast.makeText(SubmitActivity.this, myGoods.getObjectId(), 0).show();
				Toast.makeText(SubmitActivity.this, "提交了", 0).show();
				finish();
			}
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO 自动生成的方法存根
				Toast.makeText(SubmitActivity.this, "提交失败"+arg1, 0).show();

			}
		});
	}
	private void saveScalePhoto(Bitmap bitmap,String path) {  
		FileOutputStream fos = null;  
		try {  
			fos = new FileOutputStream(path);  
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} finally {  
			try {  
				fos.flush();  
				fos.close();  
			} catch (IOException e) {  
				e.printStackTrace();  
			}  
		}  
	}  

}
