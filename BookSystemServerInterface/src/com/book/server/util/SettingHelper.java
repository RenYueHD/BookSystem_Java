package com.book.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 服务器配置帮助类
 * @author RenYue
 *
 */
public class SettingHelper {
	/**
	 * 创建配置文件
	 * @return 创建的配置文件
	 * @throws IOException 配置文件创建失败,出现了IO错误
	 */
	private static Properties createPro() throws IOException{
		FileOutputStream os = null;
		try{
			os =new FileOutputStream("setting.xml");
			Properties pro = new Properties();
			pro.storeToXML(os, null);
			return pro;
		}finally{
			os.close();
		}
	}
	
	/**
	 * 读取配置文件
	 * @return 读取到的配置文件
	 */
	private static Properties loadPro(){
		File file =new File("setting.xml");
		InputStream is=null;
		
		if(file.exists()){
			try {
				is = new FileInputStream(file);
				Properties p = new Properties();   
				p.loadFromXML(is);
				return p;
			} catch (Exception e) {
				System.err.println("读取配置文件时出现IO错误");
				return null;
			}finally{
				if(is!=null){
					try {
						is.close();
					} catch (IOException e) {
					}
				}
			}
		}else{
			try {
				return createPro();
			} catch (IOException e) {
				System.err.println("读取配置文件时出现IO错误");
				return null;
			}
		}
	}
	
	/**
	 * 读取服务器设置的监听网卡地址
	 * @return 监听网卡的地址,从未设置则返回null
	 */
	public static String loadListenAddress(){
		Properties pro = loadPro();
		String ip = null;
		if(pro !=null){
			ip = pro.getProperty("address");
		}
		
		return ip;
	}
	
	/**
	 * 保存监听网卡地址至配置文件
	 * @param address 服务器设置的监听网卡地址
	 */
	public static void saveListenAddress(String address){
		Properties pro = loadPro();
		pro.setProperty("address", address);
		FileOutputStream fs=null;
		try {
			fs = new FileOutputStream("setting.xml");
			pro.storeToXML(fs, null);
		} catch (Exception e) {
			System.err.println("写入配置文件时出现IO错误");
		}finally{
			if(fs!=null){
				try {
					fs.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	/**
	 * 保存监听端口至配置文件
	 * @param port 监听端口
	 */
	public static void savePort(int port){
		Properties pro = loadPro();
		pro.setProperty("port", String.valueOf(port));
		FileOutputStream fs=null;
		try {
			fs = new FileOutputStream("setting.xml");
			pro.storeToXML(fs, null);
		} catch (Exception e) {
			System.err.println("写入配置文件时出现IO错误");
		}finally{
			if(fs!=null){
				try {
					fs.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	/**
	 * 读取服务器设置的监听端口
	 * @return 监听端口,从未设置则返回-1
	 */
	public static int loadPort(){
		Properties pro = loadPro();
		String ip = null;
		if(pro !=null){
			ip = pro.getProperty("ip");
		}
		if(ip !=null){
			try{
				return Integer.parseInt(ip);
			}catch(NumberFormatException e){
				return -1;
			}
		}else{
			return -1;
		}
	}
	
	/**
	 * 保存请求解析器对应的包名至配置文件
	 * @param name
	 */
	public static void saveAnalyzeJarPackage(String name){
		Properties pro = loadPro();
		pro.setProperty("analyze", name);
		FileOutputStream fs=null;
		try {
			fs = new FileOutputStream("setting.xml");
			pro.storeToXML(fs, null);
		} catch (Exception e) {
			System.err.println("写入配置文件时出现IO错误");
		}finally{
			if(fs!=null){
				try {
					fs.close();
				} catch (IOException e) {
				}
			}
		}
	}
		
	/**
	 * 读取服务器设置的请求解析器包名
	 * @return 解析器包名,如果从未设置将返回null
	 */
	public static String loadAnalyzeJarPackage(){
		Properties pro = loadPro();
		String cls = null;
		if(pro !=null){
			cls = pro.getProperty("analyze");
		}
		
		return cls;
	}

	/**
	 * 保存数据访问器至配置文件
	 * @param name
	 */
	public static void saveDAOPackage(String name){
		Properties pro = loadPro();
		pro.setProperty("dao", name);
		FileOutputStream fs=null;
		try {
			fs = new FileOutputStream("setting.xml");
			pro.storeToXML(fs, null);
		} catch (Exception e) {
			System.err.println("写入配置文件时出现IO错误");
		}finally{
			if(fs!=null){
				try {
					fs.close();
				} catch (IOException e) {
				}
			}
		}
	}
		
	/**
	 * 读取服务器设置的数据访问器包名
	 * @return 数据访问器包名,如果从未设置过将返回null
	 */
	public static String loadDAOPackage(){
		Properties pro = loadPro();
		String cls = null;
		if(pro !=null){
			cls = pro.getProperty("dao");
		}
		
		return cls;
	}
	
	/**
	 * 判断所有设置是否全部配置完成
	 * @return 是否全部配置完成
	 */
	public static boolean hasSetting(){
		if(loadAnalyzeJarPackage() !=null && loadDAOPackage()!=null && loadListenAddress() != null && loadPort() >=0){
			return true;
		}else {
			return false;
		}
	}
}
