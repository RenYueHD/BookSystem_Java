package com.book.server.dao.exception;

/**
 * 数据访问异常
 * @author RenYue
 *
 */
public class ServerErrorException extends Exception {

	private static final long serialVersionUID = -8696963443091246182L;

	/**
	 * 通过异常描述构建数据访问异常对象
	 * @param err 异常描述
	 */
	public ServerErrorException(String err){
		super(err);
	}
}
