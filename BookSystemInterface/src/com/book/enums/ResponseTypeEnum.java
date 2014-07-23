package com.book.enums;

/**
 * 相应类型枚举
 * @author RenYue
 *
 */
public enum ResponseTypeEnum {
	/**
	 * 操作成功
	 */
	SUCCESS,
	
	/**
	 * 操作失败
	 */
	FAIL,
	
	/**
	 * 权限不足
	 */
	 NO_AUTHORIZE,

	/**
	* 未知请求
	*/
	UNKNOW,
	
	/**
	 * 服务器异常
	 */
	SERVER_ERROR,
	
	/**
	 * 功能正在开发中
	 */
	BUILDING
	
}