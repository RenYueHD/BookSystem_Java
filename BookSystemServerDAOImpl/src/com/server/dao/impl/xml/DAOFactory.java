package com.server.dao.impl.xml;

import java.rmi.ServerException;

import com.book.server.dao.IBookInfoDAO;
import com.book.server.dao.IBookTypeDAO;
import com.book.server.dao.IDAOFactory;
import com.book.server.dao.IUserDAO;

public class DAOFactory extends BaseDAO implements IDAOFactory {

	public DAOFactory() throws ServerException {
		super();
	}

	@Override
	public IUserDAO getUserDAO() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public IBookInfoDAO getBookInfoDAO() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public IBookTypeDAO getBookTypeDAO() {
		// TODO 自动生成的方法存根
		return null;
	}

}
