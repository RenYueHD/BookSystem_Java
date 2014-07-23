package com.book.server.analyze.impl;

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
class RequestAnalyzeBookInfo  implements IRequestAnalyze {
	private BookRequest request;
	public RequestAnalyzeBookInfo(BookRequest request){
		this.request= request;
	}
	@Override
	public BookResponse getResult() {
		BookResponse response = new BookResponse();
		
		try {
				BookInfo info =DAOFactoryBuilder.newFactory().getBookInfoDAO().getBookByInfo((BookInfo)request.getRequestContent());
				if(info!=null){
					response.setResponseType(ResponseTypeEnum.SUCCESS);
					response.setResponseContent(info);
				}else{
					response.setResponseType(ResponseTypeEnum.FAIL);
				}
		} catch (ServerErrorException e) {
			response.setResponseType(ResponseTypeEnum.SERVER_ERROR);
			response.setResponseContent(e);
		}

		return response;
	}
}