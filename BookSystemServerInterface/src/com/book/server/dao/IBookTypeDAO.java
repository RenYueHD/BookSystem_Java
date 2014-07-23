package com.book.server.dao;

import java.util.List;

import com.book.server.dao.exception.ServerErrorException;

/**
 * ͼ��������Ϣ���ݷ��ʽӿ�
 * @author RenYue
 *
 */
public interface IBookTypeDAO {
	List<String> getAllTypes() throws ServerErrorException;
	boolean addBookType(String typeName) throws ServerErrorException;
	boolean deleteBookType(String typeName) throws ServerErrorException;
}