package com.book.message;

import java.io.Serializable;
import com.book.enums.ResponseTypeEnum;

/**
 * 服务器响应
 * @author RenYue
 *
 */
public class BookResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -293750222853358529L;
	private ResponseTypeEnum responseType;
	private Object responseContent;
	
	/**
	 * 获取响应类型
	 * @return 响应类型
	 */
	public ResponseTypeEnum getResponseType() {
		return responseType;
	}
	
	/**
	 * 设置响应类型
	 * @param responseType 响应类型
	 */
	public void setResponseType(ResponseTypeEnum responseType) {
		this.responseType = responseType;
	}
	
	/**
	 * 获取响应主体
	 * @return 响应主体
	 */
	public Object getResponseContent() {
		return responseContent;
	}

	/**
	 * 设置响应主体
	 * @param responseContent 响应主体
	 */
	public void setResponseContent(Object responseContent) {
		this.responseContent = responseContent;
	}
}