package com.An.My;

import cn.bmob.v3.BmobObject;

public class MyGoods extends BmobObject{
	private String BelongID;
	private String MyGoodsID;//������ʱ�洢ObjectId
	private String ImgUrl;//ͼƬ��ַ
	private String Title;
	private String Description;
	private String FavouritedID;
	private String RentPrice;
	private String RealPrice;
	private String RentToUserID;
	private String HowLongToRent;
	private String RecordUrl;//¼����ַ
	private boolean isRented;

	public MyGoods(){
		super();
	}
	/**
	 * 
	 * @param ImgUrl
	 * @param Title
	 * @param RentPrice
	 * @param BelongID//��������������β���BelongID ����ʵ�δ�����ObjectId-->
	 * 					��ϸ��RentDemo->Home.java->105�����ң�initList()�����У�
	 * ������췽����SearchAndResultActivity��initList()�����б����á�//���Ǵ��Ĳ����������ġ�
	 */
	public MyGoods(String ImgUrl,String Title,String RentPrice,String ObjectId){
		super();
		this.ImgUrl=ImgUrl;
		this.Title=Title;
		this.RentPrice=RentPrice;
		this.MyGoodsID=ObjectId;
	}

	/**
	 * @return myGoodsID
	 */
	public String getMyGoodsID() {
		return MyGoodsID;
	}
	/**
	 * @param myGoodsID Ҫ���õ� myGoodsID
	 */
	public void setMyGoodsID(String myGoodsID) {
		MyGoodsID = myGoodsID;
	}
	/**
	 * @return belongID
	 */
	public String getBelongID() {
		return BelongID;
	}
	/**
	 * @param belongID Ҫ���õ� belongID
	 */
	public void setBelongID(String belongID) {
		BelongID = belongID;
	}
	/**
	 * @return imgUrl
	 */
	public String getImgUrl() {
		return ImgUrl;
	}
	/**
	 * @param imgUrl Ҫ���õ� imgUrl
	 */
	public void setImgUrl(String imgUrl) {
		ImgUrl = imgUrl;
	}
	/**
	 * @return title
	 */
	public String getTitle() {
		return Title;
	}
	/**
	 * @param title Ҫ���õ� title
	 */
	public void setTitle(String title) {
		Title = title;
	}
	/**
	 * @return description
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * @param description Ҫ���õ� description
	 */
	public void setDescription(String description) {
		Description = description;
	}
	/**
	 * @return favouritedID
	 */
	public String getFavouritedID() {
		return FavouritedID;
	}
	/**
	 * @param favouritedID Ҫ���õ� favouritedID
	 */
	public void setFavouritedID(String favouritedID) {
		FavouritedID = favouritedID;
	}
	/**
	 * @return rentPrice
	 */
	public String getRentPrice() {
		return RentPrice;
	}
	/**
	 * @param rentPrice Ҫ���õ� rentPrice
	 */
	public void setRentPrice(String rentPrice) {
		RentPrice = rentPrice;
	}
	/**
	 * @return realPrice
	 */
	public String getRealPrice() {
		return RealPrice;
	}
	/**
	 * @param realPrice Ҫ���õ� realPrice
	 */
	public void setRealPrice(String realPrice) {
		RealPrice = realPrice;
	}
	/**
	 * @return rentToUserID
	 */
	public String getRentToUserID() {
		return RentToUserID;
	}
	/**
	 * @param rentToUserID Ҫ���õ� rentToUserID
	 */
	public void setRentToUserID(String rentToUserID) {
		RentToUserID = rentToUserID;
	}
	/**
	 * @return howLongToRent
	 */
	public String getHowLongToRent() {
		return HowLongToRent;
	}
	/**
	 * @param howLongToRent Ҫ���õ� howLongToRent
	 */
	public void setHowLongToRent(String howLongToRent) {
		HowLongToRent = howLongToRent;
	}
	/**
	 * @return recordUrl
	 */
	public String getRecordUrl() {
		return RecordUrl;
	}
	/**
	 * @param recordUrl Ҫ���õ� recordUrl
	 */
	public void setRecordUrl(String recordUrl) {
		RecordUrl = recordUrl;
	}
	/**
	 * @return isRented
	 */
	public boolean isRented() {
		return isRented;
	}
	/**
	 * @param isRented Ҫ���õ� isRented
	 */
	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}
}
