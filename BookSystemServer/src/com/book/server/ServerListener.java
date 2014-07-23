package com.book.server;

import java.util.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.InterruptibleChannel;

public class ServerListener extends Observable implements Runnable,InterruptibleChannel,Observer{

	/**
	 * ָʾ�������Ƿ��ѱ��ر�
	 */
	private boolean isClosed;
	private ServerSocket server = null;
	private int port;
	private InetAddress address;
	
	public ServerListener(int port){
		this.port = port;
	}
	
	public ServerListener(InetAddress address,int port){
		this.port = port;
		this.address = address;
	}
	
	public ServerListener(String hostName,int port) throws UnknownHostException{
		this.port = port;
		this.address = InetAddress.getByName(hostName);
	}
	
	@Override
	public void run(){
		super.setChanged();
		super.notifyObservers("������������");
		
		try {
			server = address==null?new ServerSocket(port):new ServerSocket(port,0, address);

			super.setChanged();
			super.notifyObservers("��������"+server.getInetAddress().getHostAddress()+":"+port);
			while(!isClosed){
				try{
					Socket socket = server.accept();
					ClientHandler handler = new ClientHandler(socket);
					handler.addObserver(this);
					Thread thread = new Thread(handler);
					thread.start();
				}catch(Exception e){
					if(!isClosed){
						super.setChanged();
						super.notifyObservers("�ͻ������Ӵ����̳߳�����δ֪����");
					}
				}
			}
		} catch (IOException e) {
			super.setChanged();
			super.notifyObservers("������������δ֪����");
		}finally{
			if(server !=null){
				try {
					server.close();
				} catch (IOException e) {
				}
			}
			isClosed = true;
			super.setChanged();
			super.notifyObservers("���������ر�");
		}
	}

	/**
	 * �жϷ������Ƿ��Ǵ�״̬
	 */
	@Override
	public boolean isOpen() {
		return isClosed;
	}

	/**
	 * �رշ�����
	 */
	@Override
	public void close() throws IOException {	
		if(server !=null){
			server.close();
		}
		isClosed=true;
	}

	@Override
	public void update(Observable o, Object arg) {
		super.setChanged();
		super.notifyObservers(arg);
	}
}