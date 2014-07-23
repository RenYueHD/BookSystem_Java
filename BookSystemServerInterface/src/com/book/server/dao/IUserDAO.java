package com.book.server.dao;

import java.util.List;
import com.book.server.dao.exception.ServerErrorException;
import com.book.server.entity.User;

/**
 * �û�������Ϣ���ݷ��ʽӿ�
 * @author RenYue
 *
 */
public interface IUserDAO {
	User getUserByName(String name) throws ServerErrorException;
	List<User> getAllUsers() throws ServerErrorException;
	boolean addUser(User user) throws ServerErrorException;
	boolean deleteUserByName(String name) throws ServerErrorException;
}