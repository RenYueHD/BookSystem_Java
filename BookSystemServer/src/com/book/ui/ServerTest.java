package com.book.ui;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Observable;
import java.util.Observer;
import com.book.server.ServerListener;
import com.book.server.util.SettingHelper;

public class ServerTest implements Observer {

	/**
	 * @param args
	 * @throws InvalidPropertiesFormatException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InvalidPropertiesFormatException, IOException {
		if(!SettingHelper.hasSetting()){
			SettingHelper.savePort(41366);
			SettingHelper.saveListenAddress("0.0.0.0");
			SettingHelper.saveAnalyzeJarPackage("analyze.jar");
			SettingHelper.saveDAOPackage("dao.jar");
		}
		SettingHelper.saveAnalyzeJarPackage("cls.jar");
		String address = SettingHelper.loadListenAddress();
		int port = SettingHelper.loadPort();
		ServerListener listener = new ServerListener("0.0.0.0",41366);
		listener.addObserver(new ServerTest());
		Thread thread = new Thread(listener);
		thread.start();
		
		System.out.println("按任意键退出");
		System.in.read();

		try {
			listener.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		String msg = (String)arg;
		System.out.println(msg);
	}
}