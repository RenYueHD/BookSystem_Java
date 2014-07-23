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
				System.out.println("请输入IP地址:");
				ip = input.next();
				System.out.println("请输入端口");
				port = input.nextInt();
				p.setProperty("ip", ip);
				p.setProperty("port",String.valueOf(port));
				FileOutputStream os = new FileOutputStream(file);
				p.storeToXML(os, null);
				os.close();
			}
			
			System.out.println("正在连接"+ip+":"+port);
			Socket socket = new Socket(ip,port);

			SocketHelper helper = new SocketHelper(socket);
			BookRequest request = new BookRequest();
			request.setUserName("admina");
			request.setPassWord("1234567");
			request.setRequestContent("古典");
			
			request.setRequestType(RequestTypeEnum.USER_LOGIN);
			System.out.println("正在发送请求");
			
				helper.writeObject(request);				
				System.out.println("发送请求成功,任意键关闭");
				if(helper.read()){
					BookResponse res = helper.getObject();
					if(res!=null){
						System.out.println(res.getResponseType());
					}
				}else{
					System.out.println(helper.getString());
				}

			helper.close();

			System.out.println("连接结束");
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}