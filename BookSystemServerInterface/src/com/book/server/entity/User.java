package com.book.server.entity;

/**
 * 用户实体
 * @author RenYue
 *
 */
public class User {
	private String userName;
	private String passWord;
	private String role;
	
	public User(){}
	
	/**
	 * 通过用户名,密码,权限来构造用户
	 * @param name 用户名
	 * @param pwd 密码
	 * @param role 权限
	 */
	public User(String name,String pwd,String role){
		this.userName = name;
		this.passWord =pwd;
		this.role =role;
	}
	
	/**
	 * 获取用户名
	 * @return 用户名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置用户名
	 * @param userName 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取密码
	 * @return 密码
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * 设置密码
	 * @param passWord 密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * 获取权限
	 * @return 权限
	 */
	public String getRole() {
		return role;
	}
	/**
	 * 设置权限
	 * @param role 权限
	 */
	public void setRole(String role) {
		this.role = role;
	}
}