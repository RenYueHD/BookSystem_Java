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
 * �ͻ��������̴߳�����
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
		super.notifyObservers("�ͻ���"+address.getAddress().getHostAddress()+":"+address.getPort()+"�Ѿ�����");
		
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
			super.notifyObservers("�ͻ���"+address.getAddress().getHostAddress()+":"+address.getPort()+"�Ѿ��Ͽ�");
		}
	}
}