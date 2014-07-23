package com.book.server.entity;

/**
 * �û�ʵ��
 * @author RenYue
 *
 */
public class User {
	private String userName;
	private String passWord;
	private String role;
	
	public User(){}
	
	/**
	 * ͨ���û���,����,Ȩ���������û�
	 * @param name �û���
	 * @param pwd ����
	 * @param role Ȩ��
	 */
	public User(String name,String pwd,String role){
		this.userName = name;
		this.passWord =pwd;
		this.role =role;
	}
	
	/**
	 * ��ȡ�û���
	 * @return �û���
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * �����û���
	 * @param userName �û���
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * ��ȡ����
	 * @return ����
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * ��������
	 * @param passWord ����
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * ��ȡȨ��
	 * @return Ȩ��
	 */
	public String getRole() {
		return role;
	}
	/**
	 * ����Ȩ��
	 * @param role Ȩ��
	 */
	public void setRole(String role) {
		this.role = role;
	}
}