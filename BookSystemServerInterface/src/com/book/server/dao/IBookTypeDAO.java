package com.book.server.dao;

import java.util.List;

import com.book.server.dao.exception.ServerErrorException;

/**
 * 图书类型信息数据访问接口
 * @author RenYue
 *
 */
public interface IBookTypeDAO {
	List<String> getAllTypes() throws ServerErrorException;
	boolean addBookType(String typeName) throws ServerErrorException;
	boolean deleteBookType(String typeName) throws ServerErrorException;
}