package com.book.server.analyze.filter;

import com.book.message.BookRequest;
import com.book.message.BookResponse;

/**
 * 请求解析自定义过滤器接口(用以在对请求解析之前对请求信息进行过滤)
 * @author RenYue
 *
 */
public interface IRequestFilter {
	/**
	 * 验证客户端请求是否能通过过滤器
	 * @param request 客户端请求
	 * @return 是否能通过
	 */
	boolean filter(BookRequest request);
	
	/**
	 * 如果不能通过 调用此方法可获取到过滤器处理结果
	 * @return 获取到的处理结果
	 */
	BookResponse getResponse();
}
