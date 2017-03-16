package network.thread;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


// 服务端协议类
public class EchoProtocol implements Runnable {

	private static final int BUFSIZE = 32;
	private Socket clientSocket; // 连接客户端的Socket
	private Logger logger; // 服务器日志记录器

	public EchoProtocol(Socket clientSocket, Logger logger) {
		this.clientSocket = clientSocket;
		this.logger = logger;
	}

	// 对每个客户端的处理
	public static void handleEchoClient(Socket clientSock, Logger logger) {

		try {
			
			TimeUnit.SECONDS.sleep(3);

			InputStream in = clientSock.getInputStream();
			OutputStream out = clientSock.getOutputStream();

			int recvMsgSize; // 收到消息的大小
			int totalBytesEchoed = 0; // 从服务端收到字节数大小
			byte[] echoBuffer = new byte[BUFSIZE]; // 接收缓存数组

			// receive until client closes connection, indicated by -1
			while ((recvMsgSize = in.read(echoBuffer)) != -1) {
				out.write(echoBuffer, 0, recvMsgSize);
				totalBytesEchoed += recvMsgSize;
			}

			logger.info("Client " + clientSock.getRemoteSocketAddress() + ", echoed " + totalBytesEchoed + "bytes");
			
			

		} catch (Exception e) {
			logger.log(Level.WARNING, "Exception in echo protocol", e);
		} finally {
			try {
				clientSock.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	

	}
	

	@Override
	public void run() {
		
		handleEchoClient(clientSocket, logger);

	}

}
