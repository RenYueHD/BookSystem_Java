package com.book.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Observable;

import com.book.message.BookRequest;
import com.book.message.BookResponse;
import com.book.server.analyze.RequestAnalyze;
import com.book.util.SocketHelper;

/**
 * 客户端连接线程处理类
 * @author RenYue
 *
 */
public class ClientHandler extends Observable implements Runnable {
	
	private SocketHelper helper;
	private Socket socket;
	
	public ClientHandler(Socket socket) throws IOException {
		this.socket = socket;
		helper = new SocketHelper(socket);
	}
	
	@Override
	public void run() {
		super.setChanged();
		InetSocketAddress address = (InetSocketAddress)socket.getRemoteSocketAddress();
		super.notifyObservers("客户端"+address.getAddress().getHostAddress()+":"+address.getPort()+"已经接入");
		
		try {
			while(true){
					boolean isObj = helper.read();
					if(isObj){
						BookRequest request = helper.getObject();
						BookResponse response = RequestAnalyze.getResult(request);
						helper.writeObject(response);
					}
			}
		}catch (IOException e) {
			super.setChanged();
			super.notifyObservers("客户端"+address.getAddress().getHostAddress()+":"+address.getPort()+"已经断开");
		}
	}
}