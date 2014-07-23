package com.book.server.dao;

import com.book.server.dao.exception.ServerErrorException;
import com.book.server.util.ClassLoaderHelper;
import com.book.server.util.SettingHelper;

/**
 * ���ݷ�����������,���Դ������ݷ�����
 * @author RenYue
 *
 */
public class DAOFactoryBuilder {
	private DAOFactoryBuilder(){}
	
	public static IDAOFactory newFactory() throws ServerErrorException{
		try {
			ClassLoaderHelper helper = new ClassLoaderHelper(SettingHelper.loadDAOPackage());
			return helper.newInstance(IDAOFactory.class);
		} catch (ClassNotFoundException e) {
			System.err.println("ȱ��XML�������");
			throw new ServerErrorException("ȱ��XML�������");
		} 
	}
}