package com.book.server.util;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import com.book.server.analyze.IRequestAnalyzeFacotry;

/**
 * ������������� ���Դ�ĳһ��jar�ļ��л�ȡ���ֶ���
 * @author RenYue
 *
 */
public class ClassLoaderHelper {
	public ClassLoaderHelper(String jarFileName){
		this.jarName = jarFileName;
	}
	
	private String jarName;
	private ClassLoader loader;
	
	/**
	* ��һ��jar�ļ�����ȡ�����е�����
	* @param file	jar�ļ��ľ���·��
	* @return	���е���������
	*/
	@SuppressWarnings("resource")
	private List<String> getClasses() {
		JarFile jar = null;
		List<String> set = new ArrayList<String>();
		try {
	    jar = new JarFile(jarName);
		} catch (IOException e) {
			return null;
		}
		Enumeration<JarEntry> entries = jar.entries();
	 
		String name;
		while(entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();
			if(entry.getName().endsWith(".class")) {
				name = entry.getName();
				name = name.substring(0, name.length() - 6);
				name = name.replaceAll("/", ".");
				set.add(name);
			}
		}
		return set;
	}
	
	/**
	 * ʹ�ü���������һ����Ӧ�������ಢ��ȡʵ��
	 * @param className	Ҫ��ȡʵ��������(����.����)
	 * @return �����һ��ʵ��
	 * @throws Exception ����ʧ��,ԭ��ͨ��������������������û���ҵ�
	 */
	@SuppressWarnings("unchecked")
	public <T> T newInstance(String className) throws Exception{
		if(loader ==null){
			loader = getClassLoader();
		}
		try {
			return  (T)loader.loadClass(className).newInstance();
		} catch (Exception e) {
			throw new Exception("��ָ����jar�ļ��м�����"+className+"ʧ��");
		}
	}
	
	/**
	 * ʹ�ü���������һ���̳���ĳһ�������ʵ����ĳһ���ӿڵĶ���
	 * Ҳ����˵ ����һ�������ӿ� ����һ���������
	 * @param superClass Ҫ���صĶ���ĳ����ӿ�
	 * @return ʵ���˴���ĳ����ӿڵ��������
	 * @throws ClassNotFoundException ��ȡ����ʧ��,ԭ��ͨ��������������������û���ҵ�
	 */
	@SuppressWarnings("unchecked")
	public <T> T newInstance(Class<T> superClass) throws ClassNotFoundException{
		if(loader ==null){
			loader = getClassLoader();
		}
		try {
			List<String> classes = getClasses();
			
			for (String cname : classes) {
				Class<?> cls = loader.loadClass(cname);
				if(IRequestAnalyzeFacotry.class.isAssignableFrom(cls)){
					return (T)cls.newInstance();	
				}
			}
			throw new ClassNotFoundException("��ָ����jar�ļ���û���ҵ�����ʵ��");
		}catch (Exception e){
			throw new ClassNotFoundException("��ָ����jar���м�����ʧ��");
		}
	}
	
	/**
	* ����һ��jar�ļ���������һ����Ӧ��ClassLoader
	* @param file   *.jar������/���·��
	* @return    ����һ��ClassLoader
	*/
	private URLClassLoader getClassLoader() {
		try {
			return new URLClassLoader(new URL[]{new URL("file:"+jarName)});
		} catch (MalformedURLException e) {
			return null;
		}
	}
}