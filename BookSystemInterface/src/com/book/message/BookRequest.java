package com.book.message;

import java.io.Serializable;

import com.book.enums.RequestTypeEnum;


/**
 * �ͻ�������
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
	 * ��ȡ������ص��û���
	 * @return �û���
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * ����������ص��û���
	 * @param userName �û���
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * ��ȡ������ص�����
	 * @return ����
	 */
	public String getPassWord() {
		return passWord;
	}
	
	/**
	 * ����������ص�����
	 * @return ����
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	/**
	 * ��ȡ���������
	 * @return ��������
	 */
	public RequestTypeEnum getRequestType() {
		return requestType;
	}
	
	/**
	 * �������������
	 * @param requestType ��������
	 */
	public void setRequestType(RequestTypeEnum requestType) {
		this.requestType = requestType;
	}
	
	/**
	 * ��ȡ��������
	 * @return ��������
	 */
	public Object getRequestContent() {
		return requestContent;
	}
	
	/**
	 * ������������
	 * @param requestContent ��������
	 */
	public void setRequestContent(Object requestContent) {
		this.requestContent = requestContent;
	}
}