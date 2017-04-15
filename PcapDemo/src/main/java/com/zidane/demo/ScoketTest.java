package com.zidane.demo;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ScoketTest {

	public static void main(String[] args) {

		try {
			Socket socket=new Socket("116.251.204.42",80);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
				
		
	}

}
