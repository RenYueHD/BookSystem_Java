package com.book.server.analyze;

import com.book.message.BookRequest;

/**
 * ��������������ӿ� ����������������ʵ������ӿ�
 * @author RenYue
 *
 */
public interface IRequestAnalyzeFacotry {
	IRequestAnalyze getRequestAnalyze(BookRequest request);
}