package network.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class TCPEchoServerPool {

	private static final int PORT = 9090;
	private static final int threadPoolSize = 10;

	public static void main(String[] args) {

		try {
			final ServerSocket serverSocket = new ServerSocket(PORT);
			final Logger logger = Logger.getLogger(TCPEchoServerThread.class.getName());

			for (int i = 0; i < threadPoolSize; i++) {

				Thread thread = new Thread() {
					public void run() {

						while (true) {
							
							try {
								Socket clientSocket = serverSocket.accept();
								EchoProtocol.handleEchoClient(clientSocket, logger);
								
							} catch (Exception e) {
								// TODO: handle exception
							}
							
						}

					};
				};
				
				thread.start();
				logger.info("开始处理队列:"+thread.getName());

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
