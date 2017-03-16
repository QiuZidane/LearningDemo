package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @see TCP服务器 工作步骤： 1.创建ServerSocket实例并制定本地端口，用于监听指定端口收到的连接 <br>
 *      2.重复执行：<br>
 *      a.调用ServerSocket的accept()方法获取下一个客户端连接，基于该连接创建Socket实例.<br>
 *      b.使用上一步中的Socket实例的InputStream和OutputStream与客户端通信. <br>
 *      c.通信完成后，使用Socket.close()关闭该客户端的套接字连接. <br>
 *      相关方法： 1.clientSocket.getRemoteSocketAddress() 获取客户端socket的ip地址(含端口)
 */
public class TCPEchoServer {

	private static final int BUFSIZE = 32; // 接收缓存器大小

	/**
	 * 正常accept,全量返回字符信息给client
	 * 
	 * @param serverSocket
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void acceptHandle(ServerSocket serverSocket) throws IOException, InterruptedException {

		int recvMsgSize; // 接收信息的大小
		byte[] receiveBuf = new byte[BUFSIZE]; // 接收缓存器
		byte[] helloByte = "Hello World again!".getBytes("ISO-8859-1");

		// accept()方法会阻塞等待，直到有新的连接请求到来
		// 如果服务端未调用accept()，连接就已经到达，那么连接将排入队列中，这时一调用accept()就会立刻响应了
		Socket clientSocket = serverSocket.accept(); // 获取客户端连接

		SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();

		System.out.println("正在处理客户端地址:" + clientAddress);

		InputStream in = clientSocket.getInputStream();
		OutputStream out = clientSocket.getOutputStream();

		// out.write(helloByte);

		while ((recvMsgSize = in.read(receiveBuf)) != -1) {
			// 读取输入并立刻输出，所以offset都是0
			out.write(receiveBuf, 0, recvMsgSize); //
			// 将receiveBuf的第0位开始长度recvMsgSize的字节输出
			System.out.println("向客户端传输数据:" + new String(receiveBuf, "ISO-8859-1"));
		}

		clientSocket.close();
	}

	/**
	 * 每次读写一个字节
	 * 
	 * @param serverSocket
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void acceptHandle_1bytePertime(ServerSocket serverSocket)
			throws IOException, InterruptedException {

		int recvMsgSize; // 接收信息的大小
		byte[] receiveBuf = new byte[1]; // 接收缓存器

		int wait = 0;
		while (wait++ < 10) {
			Thread.sleep(1000);
			System.out.println("wait " + wait + " seconds");
		}
		// accept()方法会阻塞等待，直到有新的连接请求到来
		// 如果服务端未调用accept()，连接就已经到达，那么连接将排入队列中，这时一调用accept()就会立刻响应了
		Socket clientSocket = serverSocket.accept(); // 获取客户端连接

		SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();

		System.out.println("正在处理客户端地址:" + clientAddress);

		InputStream in = clientSocket.getInputStream();
		OutputStream out = clientSocket.getOutputStream();

		while ((recvMsgSize = in.read(receiveBuf)) != -1) {
			Thread.sleep(1000);
			// 读取输入并立刻输出，所以offset都是0
			out.write(receiveBuf, 0, recvMsgSize); //
			// 将receiveBuf的第0位开始长度recvMsgSize的字节输出
			System.out.println("向客户端传输数据:" + new String(receiveBuf, "ISO-8859-1"));
		}

		clientSocket.close();
	}

	public static void main(String[] margs)
			throws IllegalAccessException, IOException, InterruptedException {

		TCPEchoServer server = new TCPEchoServer();

		int servPort = Integer.parseInt("9090");

		ServerSocket serverSocket = new ServerSocket(servPort);

		System.out.println("InetAddress=" + serverSocket.getInetAddress());
		System.out.println("LocalSocketAddress=" + serverSocket.getLocalSocketAddress());
		System.out.println("LocalPort=" + serverSocket.getLocalPort());

		while (true) { // 长期运行，接收和发送的连接

			server.acceptHandle(serverSocket);
			// server.acceptHandle_1bytePertime(serverSocket);

		}
	}

}
