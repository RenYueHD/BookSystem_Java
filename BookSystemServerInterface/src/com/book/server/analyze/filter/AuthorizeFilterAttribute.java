package com.book.server.analyze.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 解析器应用的权限过滤器的完全限定名
 * @author RenYue
 *
 */
@Target(value=ElementType.TYPE)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface AuthorizeFilterAttribute {
	/**
	 * 过滤器类名
	 * @return 过滤器类名
	 */
	String value();
}