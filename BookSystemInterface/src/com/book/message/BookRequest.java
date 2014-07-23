package com.book.message;

import java.io.Serializable;

import com.book.enums.RequestTypeEnum;


/**
 * 客户端请求
 * @author RenYue
 *
 */
public class BookRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7249027133321421683L;
	private String userName;
	private String passWord;
	private RequestTypeEnum requestType;
	private Object requestContent;

	/**
	 * 获取请求相关的用户名
	 * @return 用户名
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * 设置请求相关的用户名
	 * @param userName 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 获取请求相关的密码
	 * @return 密码
	 */
	public String getPassWord() {
		return passWord;
	}
	
	/**
	 * 设置请求相关的密码
	 * @return 密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	/**
	 * 获取请求的类型
	 * @return 请求类型
	 */
	public RequestTypeEnum getRequestType() {
		return requestType;
	}
	
	/**
	 * 设置请求的类型
	 * @param requestType 请求类型
	 */
	public void setRequestType(RequestTypeEnum requestType) {
		this.requestType = requestType;
	}
	
	/**
	 * 获取请求内容
	 * @return 请求内容
	 */
	public Object getRequestContent() {
		return requestContent;
	}
	
	/**
	 * 设置请求内容
	 * @param requestContent 请求内容
	 */
	public void setRequestContent(Object requestContent) {
		this.requestContent = requestContent;
	}
}