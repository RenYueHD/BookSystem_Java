package com.book.server.analyze.impl;

import com.book.enums.ResponseTypeEnum;
import com.book.message.BookResponse;
import com.book.server.analyze.IRequestAnalyze;
import com.book.server.analyze.filter.AuthorizeFilterAttribute;
import com.book.server.analyze.filter.IngressFilterAttribute;

/**
 * 功能完善中响应
 * @author Bin
 *
 */
@AuthorizeFilterAttribute(value="com.book.server.analyze.filter.impl.AuthorizeFilter")
@IngressFilterAttribute(value="com.book.server.analyze.filter.impl.RequestAvailableFilter")
class RequestAnalyzeBuilding  implements IRequestAnalyze {

	@Override
	public BookResponse getResult() {
		BookResponse response = new BookResponse();
		response.setResponseType(ResponseTypeEnum.BUILDING);
		return response;
	}
}