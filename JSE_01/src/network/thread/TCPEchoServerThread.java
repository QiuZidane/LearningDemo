package network.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class TCPEchoServerThread {
	
	private static final int serverPort = 9090;

	public static void main(String[] args) {

		try {
			ServerSocket serverSocket = new ServerSocket(serverPort);
			Logger logger = Logger.getLogger(TCPEchoServerThread.class.getName());
			
			while(true){
				
				Socket clientSocket = serverSocket.accept();
				Thread thread = new Thread(new EchoProtocol(clientSocket, logger));
				thread.start();
				logger.info("Created and started Thread"+thread.getName());
								
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
