package com.book.server.analyze.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ��������Ӧ�õ��Զ������������ȫ�޶����б�
 * @author RenYue
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomFilterAttribute  {
	
	/**
	 * ��������������
	 * @return ����������
	 */
	String[] values() default {};
}