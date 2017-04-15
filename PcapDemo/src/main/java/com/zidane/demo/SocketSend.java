package com.zidane.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketSend {
	
	private final String dstHost = "192.168.1.6";
	private final int dstPort = 8080;
	
	public void send1(){
		
		Socket socket = null;
		
		while(true){
			try {
				if (socket == null) {
					socket = new Socket(dstHost, dstPort);
					OutputStream out = socket.getOutputStream();
					String dataStr = CHexConver.hexStr2Str(DATA.TO_6_HEAD);
					byte[] dataByte = dataStr.getBytes();
					out.write(dataByte);	
				} else{
					continue;
				}
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}

	public static void main(String[] args) {
		
		
		new SocketSend().send1();

	}

}
