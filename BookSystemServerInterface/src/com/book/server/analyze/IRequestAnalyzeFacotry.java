package com.book.server.analyze;

import com.book.message.BookRequest;

/**
 * 请求解析器工厂接口 请求解析器组件必须实现这个接口
 * @author RenYue
 *
 */
public interface IRequestAnalyzeFacotry {
	IRequestAnalyze getRequestAnalyze(BookRequest request);
}