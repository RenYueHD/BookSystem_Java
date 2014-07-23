package com.book.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ���������ð�����
 * @author RenYue
 *
 */
public class SettingHelper {
	/**
	 * ���������ļ�
	 * @return �����������ļ�
	 * @throws IOException �����ļ�����ʧ��,������IO����
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
	 * ��ȡ�����ļ�
	 * @return ��ȡ���������ļ�
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
				System.err.println("��ȡ�����ļ�ʱ����IO����");
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
				System.err.println("��ȡ�����ļ�ʱ����IO����");
				return null;
			}
		}
	}
	
	/**
	 * ��ȡ���������õļ���������ַ
	 * @return ���������ĵ�ַ,��δ�����򷵻�null
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
	 * �������������ַ�������ļ�
	 * @param address ���������õļ���������ַ
	 */
	public static void saveListenAddress(String address){
		Properties pro = loadPro();
		pro.setProperty("address", address);
		FileOutputStream fs=null;
		try {
			fs = new FileOutputStream("setting.xml");
			pro.storeToXML(fs, null);
		} catch (Exception e) {
			System.err.println("д�������ļ�ʱ����IO����");
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
	 * ��������˿��������ļ�
	 * @param port �����˿�
	 */
	public static void savePort(int port){
		Properties pro = loadPro();
		pro.setProperty("port", String.valueOf(port));
		FileOutputStream fs=null;
		try {
			fs = new FileOutputStream("setting.xml");
			pro.storeToXML(fs, null);
		} catch (Exception e) {
			System.err.println("д�������ļ�ʱ����IO����");
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
	 * ��ȡ���������õļ����˿�
	 * @return �����˿�,��δ�����򷵻�-1
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
	 * ���������������Ӧ�İ����������ļ�
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
			System.err.println("д�������ļ�ʱ����IO����");
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
	 * ��ȡ���������õ��������������
	 * @return ����������,�����δ���ý�����null
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
	 * �������ݷ������������ļ�
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
			System.err.println("д�������ļ�ʱ����IO����");
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
	 * ��ȡ���������õ����ݷ���������
	 * @return ���ݷ���������,�����δ���ù�������null
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
	 * �ж����������Ƿ�ȫ���������
	 * @return �Ƿ�ȫ���������
	 */
	public static boolean hasSetting(){
		if(loadAnalyzeJarPackage() !=null && loadDAOPackage()!=null && loadListenAddress() != null && loadPort() >=0){
			return true;
		}else {
			return false;
		}
	}
}
