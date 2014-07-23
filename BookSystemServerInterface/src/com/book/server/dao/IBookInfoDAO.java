package com.book.server.dao;

import java.util.List;

import com.book.entity.BookInfo;
import com.book.server.dao.exception.ServerErrorException;

/**
 * ͼ����Ϣ���ݷ��ʽӿ�
 * @author RenYue
 *
 */
public interface IBookInfoDAO {
	/**
	 * ͨ��ͼ�������Ϣ��ȡͼ��ȫ����Ϣ
	 * @param info ͼ�������Ϣ
	 * @return ͼ��ȫ����Ϣ
	 * @throws ServerErrorException �������쳣
	 */
	BookInfo getBookByInfo(BookInfo info) throws ServerErrorException;
	
	/**
	 * ���ͼ����Ϣ
	 * @param info Ҫ��ӵ�ͼ����Ϣ
	 * @return �Ƿ���ӳɹ�
	 * @throws ServerErrorException �������쳣
	 */
	boolean addBookInfo(BookInfo info) throws ServerErrorException;
	
	/**
	 * ͨ��ͼ�����ͻ�ȡͼ�������Ϣ
	 * @param typeName
	 * @return ��ȡ���Ļ�����Ϣ�б�
	 * @throws ServerErrorException �������쳣
	 */
	List<BookInfo> getBookInfosByType(String typeName) throws ServerErrorException;
}