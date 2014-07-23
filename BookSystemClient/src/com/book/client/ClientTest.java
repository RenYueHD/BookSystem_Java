package com.book.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Scanner;
import com.book.enums.RequestTypeEnum;
import com.book.message.BookRequest;
import com.book.message.BookResponse;
import com.book.util.SocketHelper;

public class ClientTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {

		Scanner input = new Scanner(System.in);
		
		try {
			File file =new File("setting.xml");
			InputStream is;
			String ip;
			int port;
			Properties p = new Properties();   
			if(file.exists()){
				is = new FileInputStream(file);
				p.loadFromXML(is);
				is.close();
				ip = p.getProperty("ip");
				port =Integer.parseInt(p.getProperty("port"));
			}else{
				System.out.println("������IP��ַ:");
				ip = input.next();
				System.out.println("������˿�");
				port = input.nextInt();
				p.setProperty("ip", ip);
				p.setProperty("port",String.valueOf(port));
				FileOutputStream os = new FileOutputStream(file);
				p.storeToXML(os, null);
				os.close();
			}
			
			System.out.println("��������"+ip+":"+port);
			Socket socket = new Socket(ip,port);

			SocketHelper helper = new SocketHelper(socket);
			BookRequest request = new BookRequest();
			request.setUserName("admina");
			request.setPassWord("1234567");
			request.setRequestContent("�ŵ�");
			
			request.setRequestType(RequestTypeEnum.USER_LOGIN);
			System.out.println("���ڷ�������");
			
				helper.writeObject(request);				
				System.out.println("��������ɹ�,������ر�");
				if(helper.read()){
					BookResponse res = helper.getObject();
					if(res!=null){
						System.out.println(res.getResponseType());
					}
				}else{
					System.out.println(helper.getString());
				}

			helper.close();

			System.out.println("���ӽ���");
		} catch (UnknownHostException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}