package com.book.util;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Socket帮助类
 * @author RenYue
 *
 */
public class SocketHelper {
	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	private InputStreamReader isr;
	private OutputStreamWriter osw;
	private BufferedReader br;
	private BufferedWriter bw;
	
	private Object obj;	//记录上次方法读取到的对象(如果有的话)
	private String str;	//记录上次方法读取到的字符串(如果有的话)
	private boolean isLastString;	//记录上次read方法读取到的是否是字符串
	
	/**
	 * 通过一个Socket连接创建出Socket帮助类
	 * @param socket Socket连接
	 * @throws IOException 创建该帮助类时出现IO错误
	 */
	public SocketHelper(Socket socket) throws IOException{
		this.socket = socket;
		this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		this.objectInputStream = new ObjectInputStream(socket.getInputStream());
		osw = new OutputStreamWriter(objectOutputStream);
		bw = new BufferedWriter(osw);
		isr = new InputStreamReader(objectInputStream);
		br = new BufferedReader(isr);
	}
	
	/**
	 * 从流中读取对象或整行字符串(读取完成后请使用getObject或getString方法获取读取到的对象和字符串)
	 * @return 如果读取到的是对象,则返回true,如果是字符串行,则返回false
	 * @throws SocketException 读取数据错误,这通常是由于客户端已手动断开连接
	 * @throws IOException 读取数据错误,这通常是由于客户端连接由于未知原因断开
	 */
	public boolean read() throws SocketException,IOException {
		if(isLastString){
			try{
				str = br.readLine();
				if(str == null){
					obj = objectInputStream.readObject();
					isLastString = false;
					return true;
				}else{
					return false;
				}
			}catch(ClassNotFoundException e){
				obj = null;
				return true;
			}
		}else{
			try{
				obj = objectInputStream.readObject();
				isLastString = false;
				return true;
			}catch(ClassNotFoundException e){
				obj = null;
				return true;
			}catch(OptionalDataException e){
				str = br.readLine();
				isLastString = true;
				return false;
			}
		}
	}
	
	/**
	 * 获取上一次read方法读取的字符串(如果读取到的是字符串)
	 * @return 读取到的字符串
	 */
	public String getString(){
		String s = str;
		str =null;
		return s;
	}
	
	/**
	 * 获取上一次read方法读取的对象(如果读取到的是对象)
	 * @return 读取到的对象
	 */
	public <T> T getObject(){
		@SuppressWarnings("unchecked")
		T t = (T)obj;
		obj = null;
		return t;
	}
	
	/**
	 * 写入一个Object对象(此对象必须实现Serializable接口)
	 * @param obj 要写入的对象
	 * @throws IOException 写入错误,,通常是因为客户端因为未知原因断开了连接
	 */
	public void writeObject(Object obj) throws IOException{
		objectOutputStream.writeObject(obj);
		objectOutputStream.flush();
	}
	
	/**
	 * 以字节序列形式写入一个字符串并换行
	 * @param str 要写入的字符串
	 * @throws IOException 写入错误,通常是因为客户端因为未知原因断开了连接
	 */
	public void writeLine(String str) throws IOException{
		//objectOutputStream.writeBytes(str+"\n");
		//objectOutputStream.flush();
		bw.write(str);
		bw.newLine();
		bw.flush();
	}
	
	/**
	 * 读取一个Object对象(已过时,请使用read方法)
	 * @return 读取到的对象
	 * @throws ClassNotFoundException 读取到的对象转换错误
	 * @throws EOFException 读取对象错误,通常是因为客户端已经手动关闭了连接
	 * @throws IOException 读取对象错误,通常是因为客户端因为未知原因断开了连接
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public <T> T readObject() throws ClassNotFoundException, EOFException,IOException{
		return (T)objectInputStream.readObject();
	}
	
	/**
	 * 读取一行字符串
	 * @return 读取到的字符串
	 * @throws IOException 读取错误
	 */
	@Deprecated
	public String readLine() throws IOException{
		return br.readLine();
	}
	
	/**
	 * 关闭socket连接
	 * @throws IOException 关闭Socket时出现IO错误
	 */
	public void close() throws IOException{
		
		br.close();
		isr.close();
		bw.close();
		osw.close();
		objectInputStream.close();
		objectOutputStream.close();
		socket.close();
	}
}