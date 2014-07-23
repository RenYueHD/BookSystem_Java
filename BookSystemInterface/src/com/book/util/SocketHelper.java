package com.book.util;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Socket������
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
	
	private Object obj;	//��¼�ϴη�����ȡ���Ķ���(����еĻ�)
	private String str;	//��¼�ϴη�����ȡ�����ַ���(����еĻ�)
	private boolean isLastString;	//��¼�ϴ�read������ȡ�����Ƿ����ַ���
	
	/**
	 * ͨ��һ��Socket���Ӵ�����Socket������
	 * @param socket Socket����
	 * @throws IOException �����ð�����ʱ����IO����
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
	 * �����ж�ȡ����������ַ���(��ȡ��ɺ���ʹ��getObject��getString������ȡ��ȡ���Ķ�����ַ���)
	 * @return �����ȡ�����Ƕ���,�򷵻�true,������ַ�����,�򷵻�false
	 * @throws SocketException ��ȡ���ݴ���,��ͨ�������ڿͻ������ֶ��Ͽ�����
	 * @throws IOException ��ȡ���ݴ���,��ͨ�������ڿͻ�����������δ֪ԭ��Ͽ�
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
	 * ��ȡ��һ��read������ȡ���ַ���(�����ȡ�������ַ���)
	 * @return ��ȡ�����ַ���
	 */
	public String getString(){
		String s = str;
		str =null;
		return s;
	}
	
	/**
	 * ��ȡ��һ��read������ȡ�Ķ���(�����ȡ�����Ƕ���)
	 * @return ��ȡ���Ķ���
	 */
	public <T> T getObject(){
		@SuppressWarnings("unchecked")
		T t = (T)obj;
		obj = null;
		return t;
	}
	
	/**
	 * д��һ��Object����(�˶������ʵ��Serializable�ӿ�)
	 * @param obj Ҫд��Ķ���
	 * @throws IOException д�����,,ͨ������Ϊ�ͻ�����Ϊδ֪ԭ��Ͽ�������
	 */
	public void writeObject(Object obj) throws IOException{
		objectOutputStream.writeObject(obj);
		objectOutputStream.flush();
	}
	
	/**
	 * ���ֽ�������ʽд��һ���ַ���������
	 * @param str Ҫд����ַ���
	 * @throws IOException д�����,ͨ������Ϊ�ͻ�����Ϊδ֪ԭ��Ͽ�������
	 */
	public void writeLine(String str) throws IOException{
		//objectOutputStream.writeBytes(str+"\n");
		//objectOutputStream.flush();
		bw.write(str);
		bw.newLine();
		bw.flush();
	}
	
	/**
	 * ��ȡһ��Object����(�ѹ�ʱ,��ʹ��read����)
	 * @return ��ȡ���Ķ���
	 * @throws ClassNotFoundException ��ȡ���Ķ���ת������
	 * @throws EOFException ��ȡ�������,ͨ������Ϊ�ͻ����Ѿ��ֶ��ر�������
	 * @throws IOException ��ȡ�������,ͨ������Ϊ�ͻ�����Ϊδ֪ԭ��Ͽ�������
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public <T> T readObject() throws ClassNotFoundException, EOFException,IOException{
		return (T)objectInputStream.readObject();
	}
	
	/**
	 * ��ȡһ���ַ���
	 * @return ��ȡ�����ַ���
	 * @throws IOException ��ȡ����
	 */
	@Deprecated
	public String readLine() throws IOException{
		return br.readLine();
	}
	
	/**
	 * �ر�socket����
	 * @throws IOException �ر�Socketʱ����IO����
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