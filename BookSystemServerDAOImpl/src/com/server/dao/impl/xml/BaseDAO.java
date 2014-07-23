package com.server.dao.impl.xml;

import java.io.File;
import java.rmi.ServerException;

public abstract class BaseDAO {
	String data_root;
	String book_root;
	String user_file;
	String book_info_file;
	String book_type_file;
	
	public BaseDAO() throws ServerException{
		data_root = "data\\";
		book_root = "data\\books\\";
		user_file="data\\users.xml";
		book_info_file="data\\books.xml";
		book_type_file="data\\booktypes.xml";
		
		File bk_root=new File(book_root);
		if(!bk_root.exists()){
			bk_root.mkdirs();
		}
		
		File user = new File(user_file);
		if(!user.exists()){
			XMlHelper.save(	XMlHelper.create(user_file),user_file);
		}
		
		File book = new File(book_info_file);
		if(!book.exists()){
			XMlHelper.save(	XMlHelper.create(book_info_file),book_info_file);
		}
		
		File bk_type = new File(book_type_file);
		if(!bk_type.exists()){
			XMlHelper.save(	XMlHelper.create(book_type_file),book_type_file);
		}
	}
}