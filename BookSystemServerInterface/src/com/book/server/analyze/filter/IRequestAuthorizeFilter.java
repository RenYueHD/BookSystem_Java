package com.book.server.analyze.filter;

/**
 * ����Ȩ�޹�����	ִ�д���:1		(ִ�д���ԽСԽ��ִ��)
 * @author RenYue
 *
 */
public interface IRequestAuthorizeFilter extends IRequestFilter {
	/**
	 * �����������֤ʧ�� �ɵ��ô˷�����ȡ����������Ҫ��Ȩ��
	 * @return ��������Ȩ���ַ���
	 */
	String getRequiredAuthorize();
}