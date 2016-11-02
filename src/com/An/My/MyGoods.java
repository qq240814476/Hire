package com.An.My;

import cn.bmob.v3.BmobObject;

public class MyGoods extends BmobObject{
	private String BelongID;
	private String MyGoodsID;//用来临时存储ObjectId
	private String ImgUrl;//图片地址
	private String Title;
	private String Description;
	private String FavouritedID;
	private String RentPrice;
	private String RealPrice;
	private String RentToUserID;
	private String HowLongToRent;
	private String RecordUrl;//录音地址
	private boolean isRented;

	public MyGoods(){
		super();
	}
	/**
	 * 
	 * @param ImgUrl
	 * @param Title
	 * @param RentPrice
	 * @param BelongID//这个参数最早是形参是BelongID 但是实参传的是ObjectId-->
	 * 					详细见RentDemo->Home.java->105行左右（initList()方法中）
	 * 这个构造方法在SearchAndResultActivity的initList()方法中被调用。//但是传的参数是正常的。
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
	 * @param myGoodsID 要设置的 myGoodsID
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
	 * @param belongID 要设置的 belongID
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
	 * @param imgUrl 要设置的 imgUrl
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
	 * @param title 要设置的 title
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
	 * @param description 要设置的 description
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
	 * @param favouritedID 要设置的 favouritedID
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
	 * @param rentPrice 要设置的 rentPrice
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
	 * @param realPrice 要设置的 realPrice
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
	 * @param rentToUserID 要设置的 rentToUserID
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
	 * @param howLongToRent 要设置的 howLongToRent
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
	 * @param recordUrl 要设置的 recordUrl
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
	 * @param isRented 要设置的 isRented
	 */
	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}
}
