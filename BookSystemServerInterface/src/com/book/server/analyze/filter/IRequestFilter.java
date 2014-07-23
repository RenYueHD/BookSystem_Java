package com.book.server.analyze.filter;

import com.book.message.BookRequest;
import com.book.message.BookResponse;

/**
 * ��������Զ���������ӿ�(�����ڶ��������֮ǰ��������Ϣ���й���)
 * @author RenYue
 *
 */
public interface IRequestFilter {
	/**
	 * ��֤�ͻ��������Ƿ���ͨ��������
	 * @param request �ͻ�������
	 * @return �Ƿ���ͨ��
	 */
	boolean filter(BookRequest request);
	
	/**
	 * �������ͨ�� ���ô˷����ɻ�ȡ��������������
	 * @return ��ȡ���Ĵ�����
	 */
	BookResponse getResponse();
}
