package com.book.message;

import java.io.Serializable;
import com.book.enums.ResponseTypeEnum;

/**
 * ��������Ӧ
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
	 * ��ȡ��Ӧ����
	 * @return ��Ӧ����
	 */
	public ResponseTypeEnum getResponseType() {
		return responseType;
	}
	
	/**
	 * ������Ӧ����
	 * @param responseType ��Ӧ����
	 */
	public void setResponseType(ResponseTypeEnum responseType) {
		this.responseType = responseType;
	}
	
	/**
	 * ��ȡ��Ӧ����
	 * @return ��Ӧ����
	 */
	public Object getResponseContent() {
		return responseContent;
	}

	/**
	 * ������Ӧ����
	 * @param responseContent ��Ӧ����
	 */
	public void setResponseContent(Object responseContent) {
		this.responseContent = responseContent;
	}
}