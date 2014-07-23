package com.book.server.analyze.impl;

import java.util.List;
import com.book.entity.BookInfo;
import com.book.enums.ResponseTypeEnum;
import com.book.message.BookRequest;
import com.book.message.BookResponse;
import com.book.server.analyze.IRequestAnalyze;
import com.book.server.analyze.filter.AuthorizeFilterAttribute;
import com.book.server.analyze.filter.IngressFilterAttribute;
import com.book.server.dao.DAOFactoryBuilder;
import com.book.server.dao.exception.ServerErrorException;

@AuthorizeFilterAttribute(value="com.book.server.analyze.filter.impl.AuthorizeFilter")
@IngressFilterAttribute(value="com.book.server.analyze.filter.impl.RequestAvailableFilter")
class RequestAnalyzeBookBaseList implements IRequestAnalyze {
	private BookRequest request;
	public RequestAnalyzeBookBaseList(BookRequest request){
		this.request= request;
	}

	public BookResponse getResult() {
		BookResponse response = new BookResponse();
		
		try {
			if(request.getRequestContent() instanceof String && (!request.getRequestContent().toString().isEmpty())){
				String type = request.getRequestContent().toString();
				List<BookInfo> info =DAOFactoryBuilder.newFactory().getBookInfoDAO().getBookInfosByType(type);
				response.setResponseType(ResponseTypeEnum.SUCCESS);
				response.setResponseContent(info);
			}else{
				response.setResponseType(ResponseTypeEnum.UNKNOW);
			}
		} catch (ServerErrorException e) {
			response.setResponseType(ResponseTypeEnum.SERVER_ERROR);
			response.setResponseContent(e);
		}

		return response;
	}
}