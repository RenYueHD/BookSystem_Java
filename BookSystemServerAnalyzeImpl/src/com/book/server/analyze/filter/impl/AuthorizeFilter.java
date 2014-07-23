package com.book.server.analyze.filter.impl;

import com.book.enums.ResponseTypeEnum;
import com.book.message.BookRequest;
import com.book.message.BookResponse;
import com.book.server.analyze.filter.IRequestAuthorizeFilter;
import com.book.server.dao.DAOFactoryBuilder;
import com.book.server.dao.exception.ServerErrorException;
import com.book.server.entity.User;

/**
 * 身份验证过滤器
 * @author Bin
 *
 */
public class AuthorizeFilter implements IRequestAuthorizeFilter{

	private BookResponse response;
	private String role;
	
	@Override
	public boolean filter(BookRequest request) {
		User user;
		try {
			user = DAOFactoryBuilder.newFactory().getUserDAO().getUserByName(request.getUserName());
			if(user != null && user.getPassWord().equals(request.getPassWord())){
				return true;
			}else{
				response = new BookResponse();
				response.setResponseType(ResponseTypeEnum.NO_AUTHORIZE);
				role = "user";
				return false;
			}
		} catch (ServerErrorException e) {
			response = new BookResponse();
			response.setResponseType(ResponseTypeEnum.SERVER_ERROR);
			response.setResponseContent(e);
			return false;
		}
	}

	@Override
	public BookResponse getResponse() {
		// TODO 自动生成的方法存根
		return response;
	}

	@Override
	public String getRequiredAuthorize() {
		// TODO 自动生成的方法存根
		return role;
	}

}