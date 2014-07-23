package com.book.server.analyze.impl;

import com.book.enums.RequestTypeEnum;
import com.book.message.BookRequest;
import com.book.server.analyze.IRequestAnalyze;
import com.book.server.analyze.IRequestAnalyzeFacotry;

public class RequestAnalyzeFactory  implements IRequestAnalyzeFacotry {
	
	public RequestAnalyzeFactory(){
		
	}
	
	public RequestAnalyzeFactory(BookRequest request){
		
	}
	
	@Override
	public IRequestAnalyze getRequestAnalyze(BookRequest request) {
		IRequestAnalyze response=null;
		if(request == null){
			response = new RequestAnalyzeBuilding();
		}else if(request.getRequestType() == RequestTypeEnum.USER_REG){
			response = new RequestAnalyzeReg(request);
		}else if(request.getRequestType() == RequestTypeEnum.USER_LOGIN){
			response = new RequestAnalyzeLogin(request);
		}else if(request.getRequestType() == RequestTypeEnum.BOOK_BASE_INFO_LIST){
			response = new RequestAnalyzeBookBaseList(request);
		}else if(request.getRequestType() == RequestTypeEnum.BOOK_INFO){
			response = new RequestAnalyzeBookInfo(request);
		}else if(request.getRequestType() == RequestTypeEnum.USER_LOGIN){
			response = new RequestAnalyzeLogin(request);
		}else if(request.getRequestType() == RequestTypeEnum.BOOK_UPLOAD){
			response = new RequestAnalyzeBookUpload(request);
		}else{
			response = new RequestAnalyzeBuilding();
		}
	
		return response;
	}	
}