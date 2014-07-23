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
 * 类加载器工具类 用以从某一个jar文件中获取各种对象
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
	* 由一个jar文件中提取出所有的类名
	* @param file	jar文件的绝对路径
	* @return	所有的类名集合
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
	 * 使用加载器加载一个对应类名的类并获取实例
	 * @param className	要获取实例的类名(包名.类名)
	 * @return 该类的一个实例
	 * @throws Exception 加载失败,原因通常但并不仅局限于类名没有找到
	 */
	@SuppressWarnings("unchecked")
	public <T> T newInstance(String className) throws Exception{
		if(loader ==null){
			loader = getClassLoader();
		}
		try {
			return  (T)loader.loadClass(className).newInstance();
		} catch (Exception e) {
			throw new Exception("在指定的jar文件中加载类"+className+"失败");
		}
	}
	
	/**
	 * 使用加载器加载一个继承了某一个超类或实现了某一个接口的对象
	 * 也就是说 传入一个父类或接口 返回一个子类对象
	 * @param superClass 要加载的对象的超类或接口
	 * @return 实现了传入的超类或接口的子类对象
	 * @throws ClassNotFoundException 获取对象失败,原因通常但并不仅局限于子类没有找到
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
			throw new ClassNotFoundException("在指定的jar文件中没有找到子类实现");
		}catch (Exception e){
			throw new ClassNotFoundException("在指定的jar包中加载类失败");
		}
	}
	
	/**
	* 根据一个jar文件名，返回一个对应的ClassLoader
	* @param file   *.jar，绝对/相对路径
	* @return    返回一个ClassLoader
	*/
	private URLClassLoader getClassLoader() {
		try {
			return new URLClassLoader(new URL[]{new URL("file:"+jarName)});
		} catch (MalformedURLException e) {
			return null;
		}
	}
}