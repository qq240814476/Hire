package com.An.My;

import cn.bmob.im.bean.BmobChatUser;

public class MyUser extends BmobChatUser{
	private String name;
	private String TelNumber;
	private String logoUrl;
	private String myFavouriteID;
	private String mySubmitID;//我发布的商品的id
	private String myRentInID;//我租的商品的id
	private String myRentOutID;//我租出去的商品的id
	private String myBank;//我的资产
	private boolean isNew=true;//默认为true 是新注册用户
	private boolean sex=true;//默认为true 为男性
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return telNumber
	 */
	public String getTelNumber() {
		return TelNumber;
	}
	/**
	 * @param telNumber 要设置的 telNumber
	 */
	public void setTelNumber(String telNumber) {
		TelNumber = telNumber;
	}
	/**
	 * @return logoUrl
	 */
	public String getLogoUrl() {
		return logoUrl;
	}
	/**
	 * @param logoUrl 要设置的 logoUrl
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	/**
	 * @return myFavouriteID
	 */
	public String getMyFavouriteID() {
		return myFavouriteID;
	}
	/**
	 * @param myFavouriteID 要设置的 myFavouriteID
	 */
	public void setMyFavouriteID(String myFavouriteID) {
		this.myFavouriteID = myFavouriteID;
	}
	/**
	 * @return mySubmitID
	 */
	public String getMySubmitID() {
		return mySubmitID;
	}
	/**
	 * @param mySubmitID 要设置的 mySubmitID
	 */
	public void setMySubmitID(String mySubmitID) {
		this.mySubmitID = mySubmitID;
	}
	/**
	 * @return myRentInID
	 */
	public String getMyRentInID() {
		return myRentInID;
	}
	/**
	 * @param myRentInID 要设置的 myRentInID
	 */
	public void setMyRentInID(String myRentInID) {
		this.myRentInID = myRentInID;
	}
	/**
	 * @return myRentOutID
	 */
	public String getMyRentOutID() {
		return myRentOutID;
	}
	/**
	 * @param myRentOutID 要设置的 myRentOutID
	 */
	public void setMyRentOutID(String myRentOutID) {
		this.myRentOutID = myRentOutID;
	}
	/**
	 * @return isNew
	 */
	public boolean isNew() {
		return isNew;
	}
	/**
	 * @param isNew 要设置的 isNew
	 */
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	/**
	 * @return sex
	 */
	public boolean isSex() {
		return sex;
	}
	/**
	 * @param sex 要设置的 sex
	 */
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	/**
	 * @return myBank
	 */
	public String getMyBank() {
		return myBank;
	}
	/**
	 * @param myBank 要设置的 myBank
	 */
	public void setMyBank(String myBank) {
		this.myBank = myBank;
	}

}
