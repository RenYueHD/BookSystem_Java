package com.book.server.analyze.filter;

/**
 * 请求权限过滤器	执行次序:1		(执行次序越小越先执行)
 * @author RenYue
 *
 */
public interface IRequestAuthorizeFilter extends IRequestFilter {
	/**
	 * 如果过滤器验证失败 可调用此方法获取此请求所需要的权限
	 * @return 请求所需权限字符串
	 */
	String getRequiredAuthorize();
}