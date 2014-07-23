package com.book.server.dao;

import com.book.server.dao.exception.ServerErrorException;
import com.book.server.util.ClassLoaderHelper;
import com.book.server.util.SettingHelper;

/**
 * 数据访问器工厂类,用以创建数据访问器
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
			System.err.println("缺少XML访问组件");
			throw new ServerErrorException("缺少XML访问组件");
		} 
	}
}