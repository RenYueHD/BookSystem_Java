package com.book.server.dao.exception;

/**
 * ���ݷ����쳣
 * @author RenYue
 *
 */
public class ServerErrorException extends Exception {

	private static final long serialVersionUID = -8696963443091246182L;

	/**
	 * ͨ���쳣�����������ݷ����쳣����
	 * @param err �쳣����
	 */
	public ServerErrorException(String err){
		super(err);
	}
}
