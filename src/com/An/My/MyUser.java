package com.An.My;

import cn.bmob.im.bean.BmobChatUser;

public class MyUser extends BmobChatUser{
	private String name;
	private String TelNumber;
	private String logoUrl;
	private String myFavouriteID;
	private String mySubmitID;//�ҷ�������Ʒ��id
	private String myRentInID;//�������Ʒ��id
	private String myRentOutID;//�����ȥ����Ʒ��id
	private String myBank;//�ҵ��ʲ�
	private boolean isNew=true;//Ĭ��Ϊtrue ����ע���û�
	private boolean sex=true;//Ĭ��Ϊtrue Ϊ����
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name Ҫ���õ� name
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
	 * @param telNumber Ҫ���õ� telNumber
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
	 * @param logoUrl Ҫ���õ� logoUrl
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
	 * @param myFavouriteID Ҫ���õ� myFavouriteID
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
	 * @param mySubmitID Ҫ���õ� mySubmitID
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
	 * @param myRentInID Ҫ���õ� myRentInID
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
	 * @param myRentOutID Ҫ���õ� myRentOutID
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
	 * @param isNew Ҫ���õ� isNew
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
	 * @param sex Ҫ���õ� sex
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
	 * @param myBank Ҫ���õ� myBank
	 */
	public void setMyBank(String myBank) {
		this.myBank = myBank;
	}

}
