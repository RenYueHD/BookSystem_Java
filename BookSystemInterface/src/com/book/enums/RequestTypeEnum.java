package com.book.enums;

/**
 * 请求类型枚举
 * @author RenYue
 *
 */
public enum RequestTypeEnum {
	
	/**
	 * 用户注册
	 */
	USER_REG,
	
	/**
	 * 用户登录
	 */
	USER_LOGIN,
	
	/**
	 * 图书完全信息(包含信息和主体)
	 */
	BOOK_INFO,
	
	/**
	 * 图书基本信息
	 */
	BOOK_BASE_INFO_LIST,
	
	/**
	 * 图书下载(包含信息和主体)
	 */
	BOOK_DOWNLOAD,
	
	/**
	 * 图书类型列表
	 */
	BOOK_TYPE_LIST,
	
	/**
	 * 图书列表
	 */
	BOOK_LIST,
	
	/**
	 * 图书上传
	 */
	BOOK_UPLOAD,
	
}