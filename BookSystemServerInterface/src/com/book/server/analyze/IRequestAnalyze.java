package com.book.server.analyze;

import com.book.message.BookResponse;

/**
 * 请求解析器接口
 * @author RenYue
 *
 */
public interface IRequestAnalyze {
	BookResponse getResult();
}