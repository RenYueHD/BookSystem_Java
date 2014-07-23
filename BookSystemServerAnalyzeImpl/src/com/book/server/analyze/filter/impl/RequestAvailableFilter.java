package com.book.server.analyze.filter.impl;

import com.book.entity.BookInfo;
import com.book.enums.RequestTypeEnum;
import com.book.enums.ResponseTypeEnum;
import com.book.message.BookRequest;
import com.book.message.BookResponse;
import com.book.server.analyze.filter.IRequestIngressFilter;

/**
 * 请求有效性验证过滤器
 * @author Bin
 *
 */
public class RequestAvailableFilter implements IRequestIngressFilter {

	private BookResponse response;
	
	@Override
	public boolean filter(BookRequest request) {
		if(request ==null || request.getUserName() ==null || request.getUserName().isEmpty() || request.getPassWord() == null || request.getPassWord().isEmpty()){
			response = new BookResponse();
			response.setResponseType(ResponseTypeEnum.UNKNOW);
			return false;
		}else if(request.getRequestType()== RequestTypeEnum.BOOK_INFO || request.getRequestType()== RequestTypeEnum.BOOK_BASE_INFO_LIST	|| request.getRequestType()== RequestTypeEnum.BOOK_DOWNLOAD || request.getRequestType()== RequestTypeEnum.BOOK_UPLOAD ){
			if(request.getRequestContent() instanceof BookInfo){
				BookInfo info = (BookInfo)request.getRequestContent();
				if(info.getName()== null || info.getName().isEmpty() || info.getAuthor() == null || info.getAuthor().isEmpty() || info.getType() == null || info.getType().isEmpty() || request.getRequestType() == RequestTypeEnum.BOOK_UPLOAD && !(info.getContent() instanceof String)){
					response = new BookResponse();
					response.setResponseType(ResponseTypeEnum.UNKNOW);
					return false;
				}else{
					return true;
				}
			}else{
				response = new BookResponse();
				response.setResponseType(ResponseTypeEnum.UNKNOW);
				return false;
			}
		}else{
			return true;
		}
	}

	@Override
	public BookResponse getResponse() {
		// TODO 自动生成的方法存根
		return response;
	}

}
