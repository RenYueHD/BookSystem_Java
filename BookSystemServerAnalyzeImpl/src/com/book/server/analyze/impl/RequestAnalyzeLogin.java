package com.book.server.analyze.impl;

import com.book.enums.ResponseTypeEnum;
import com.book.message.BookRequest;
import com.book.message.BookResponse;
import com.book.server.analyze.IRequestAnalyze;
import com.book.server.analyze.filter.IngressFilterAttribute;
import com.book.server.dao.DAOFactoryBuilder;
import com.book.server.dao.exception.ServerErrorException;
import com.book.server.entity.User;

/**
 * 登录请求解析实现类
 * @author RenYue
 *
 */
@IngressFilterAttribute(value="com.book.server.analyze.filter.impl.RequestAvailableFilter")
class RequestAnalyzeLogin  implements IRequestAnalyze{
	private BookRequest request;
	public RequestAnalyzeLogin(BookRequest request){
		this.request= request;
	}
	@Override
	public BookResponse getResult() {
		BookResponse response = new BookResponse();
		
		try {
			User user =DAOFactoryBuilder.newFactory().getUserDAO().getUserByName(request.getUserName());
			if(user != null && user.getPassWord().equals(request.getPassWord())){
				response.setResponseType(ResponseTypeEnum.SUCCESS);
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