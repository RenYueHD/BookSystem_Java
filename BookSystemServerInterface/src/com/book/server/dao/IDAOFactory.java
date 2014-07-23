package com.book.server.dao;

/**
 * ���ݷ����������ӿ�,���Թ������ݷ�����
 * @author RenYue
 *
 */
public interface IDAOFactory {
	IUserDAO getUserDAO();
	IBookInfoDAO getBookInfoDAO();
	IBookTypeDAO getBookTypeDAO();
}