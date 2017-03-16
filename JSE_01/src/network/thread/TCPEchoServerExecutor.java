package network.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TCPEchoServerExecutor {

	private static final int PORT = 9090;
	

	public static void main(String[] args) {
		
		
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			serverSocket.setSoTimeout((int)TimeUnit.DAYS.toSeconds(1)); //  设置最长阻塞
			Logger logger = Logger.getLogger(TCPEchoServerExecutor.class.getName());
			ExecutorService pool = Executors.newCachedThreadPool();
			
			while(true){
				
				Socket clientSocket = serverSocket.accept();
				// clientSocket.getInputStream().available(); // 返回流有多少数据可读
				pool.execute(new EchoProtocol(clientSocket, logger)); // 这样就行了！！ 
				
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		

	}

}
