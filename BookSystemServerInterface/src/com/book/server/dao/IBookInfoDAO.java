package com.book.server.dao;

import java.util.List;

import com.book.entity.BookInfo;
import com.book.server.dao.exception.ServerErrorException;

/**
 * 图书信息数据访问接口
 * @author RenYue
 *
 */
public interface IBookInfoDAO {
	/**
	 * 通过图书基本信息获取图书全部信息
	 * @param info 图书基本信息
	 * @return 图书全部信息
	 * @throws ServerErrorException 服务器异常
	 */
	BookInfo getBookByInfo(BookInfo info) throws ServerErrorException;
	
	/**
	 * 添加图书信息
	 * @param info 要添加的图书信息
	 * @return 是否添加成功
	 * @throws ServerErrorException 服务器异常
	 */
	boolean addBookInfo(BookInfo info) throws ServerErrorException;
	
	/**
	 * 通过图书类型获取图书基本信息
	 * @param typeName
	 * @return 获取到的基本信息列表
	 * @throws ServerErrorException 服务器异常
	 */
	List<BookInfo> getBookInfosByType(String typeName) throws ServerErrorException;
}