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
	 * 指示服务器是否已被关闭
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
		super.notifyObservers("服务器已启动");
		
		try {
			server = address==null?new ServerSocket(port):new ServerSocket(port,0, address);

			super.setChanged();
			super.notifyObservers("正在侦听"+server.getInetAddress().getHostAddress()+":"+port);
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
						super.notifyObservers("客户端连接处理线程出现了未知错误");
					}
				}
			}
		} catch (IOException e) {
			super.setChanged();
			super.notifyObservers("服务器遇到了未知错误");
		}finally{
			if(server !=null){
				try {
					server.close();
				} catch (IOException e) {
				}
			}
			isClosed = true;
			super.setChanged();
			super.notifyObservers("服务器被关闭");
		}
	}

	/**
	 * 判断服务器是否是打开状态
	 */
	@Override
	public boolean isOpen() {
		return isClosed;
	}

	/**
	 * 关闭服务器
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