package com.book.server.analyze.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 解析器所应用的自定义过滤器的完全限定名列表
 * @author RenYue
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomFilterAttribute  {
	
	/**
	 * 过滤器类名数组
	 * @return 过滤器类名
	 */
	String[] values() default {};
}