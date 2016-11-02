package com.An.Tools;

/**
 * @author kingofglory email: kingofglory@yeah.net blog: http:www.google.com
 * @date 2014-2-21 TODO
 */ 

public interface Constant {

	String BMOB_APP_ID = "11f77968dce8efecc9929abc67429cdc";
	String TABLE_AI = "Mood";
	String TABLE_COMMENT = "Comment";

	String NETWORK_TYPE_WIFI = "wifi";
	String NETWORK_TYPE_MOBILE = "mobile";
	String NETWORK_TYPE_ERROR = "error";

	int CONTENT_TYPE = 4;

	public static final int PUBLISH_COMMENT = 1;
	public static final int NUMBERS_PER_PAGE = 15;// 每次请求返回评论条数
	public static final int GO_SETTINGS = 4;

	public static final String SEX_MALE = "male";
	public static final String SEX_FEMALE = "female";
}
