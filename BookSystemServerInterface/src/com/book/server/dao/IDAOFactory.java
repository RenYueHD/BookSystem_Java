package com.book.server.dao;

/**
 * 数据访问器工厂接口,用以构建数据访问器
 * @author RenYue
 *
 */
public interface IDAOFactory {
	IUserDAO getUserDAO();
	IBookInfoDAO getBookInfoDAO();
	IBookTypeDAO getBookTypeDAO();
}