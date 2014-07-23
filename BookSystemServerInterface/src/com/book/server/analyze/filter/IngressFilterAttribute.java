package com.book.server.analyze.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ������Ӧ�õ���վ����������ȫ�޶���
 * @author RenYue
 *
 */
@Target(value=ElementType.TYPE)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface IngressFilterAttribute {
	/**
	 * ����������
	 * @return ����������
	 */
	String value();
}