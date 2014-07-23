package com.server.dao.impl.xml;

import java.rmi.ServerException;
import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class XMlHelper {

	public static Document create(String fileName) throws ServerException{
		return null;
	}
	
	public static Document load(String fileName) throws ServerException{
		return null;
	}
	
	public static void save(Document doc, String fileName) throws ServerException{
		
	}
	
	public static boolean containsElement(Document doc,String tagName,Attr... attrs) throws ServerException{
		return getElementByTagNameAndAttr(doc,tagName,attrs)==null;
	}
	
	public static Element getElementByTagNameAndAttr(Document doc,String tagName,Attr... attrs) throws ServerException{
		return null;
	}
	
	public static List<Element> getElementsByTagNameAndAttr(Document doc,String tagName,Attr... attrs) throws ServerException{
		return null;
	}
}