package com.book.server.dao;

import java.util.List;
import com.book.server.dao.exception.ServerErrorException;
import com.book.server.entity.User;

/**
 * 用户基本信息数据访问接口
 * @author RenYue
 *
 */
public interface IUserDAO {
	User getUserByName(String name) throws ServerErrorException;
	List<User> getAllUsers() throws ServerErrorException;
	boolean addUser(User user) throws ServerErrorException;
	boolean deleteUserByName(String name) throws ServerErrorException;
}