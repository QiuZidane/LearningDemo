package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;


/**
 * 
 * 1.通过new Socket(server, servPort)创建Socket实例 <br>
 * 
 */
public class TCPEchoClient {

	public static void main(String[] margs) throws UnknownHostException, IOException, InterruptedException {

		String[] args = { "127.0.0.1", "Echo this hahahahaha", "9090" };

		if ((args.length < 2) || (args.length > 3)) {
			throw new IllegalArgumentException("IllegalArgumentException Parameter(s)");
		}

		String server = args[0]; // 服务器名字或ip地址

		byte[] sendData = args[1].getBytes("ISO-8859-1"); // 用默认码制将字符串转换为bytes类型
		byte[] receiveData = new byte[100];

		int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;

		Socket socket = null;
		
		
		
		try {
			
			socket = new Socket(server, servPort);
			TimeUnit.MILLISECONDS.sleep(2000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Connected to server...sending echo string");
		System.out.println("LocalSocketAddress=" + socket.getLocalSocketAddress()); // 本书例中SocketAddress方法返回的都是InetSocketAddress实例，而该实例中封装了一个InetAddress和一个端口号
		System.out.println("RemoteSocketAddress=" + socket.getRemoteSocketAddress());

		InputStream in = socket.getInputStream(); // 来自服务器的流数据
		OutputStream out = socket.getOutputStream(); // 发送给服务器的流数据

		out.write(sendData); // 发送字符串到服务器

		int totalBytesRcvd = 0; // 统计目前收到的字节数

		int bytesRcvd; // 最后一次读取的字节数

		while (totalBytesRcvd < sendData.length) {
			// 读取过程中，TCP连接被另一端关闭，read会返回-1
			if ((bytesRcvd = in.read(receiveData, totalBytesRcvd, sendData.length - totalBytesRcvd)) == -1) { 
				throw new SocketException("Connection closed prematurely");
			}
			totalBytesRcvd += bytesRcvd;
		} // 读取完毕

		System.out.println("Received:" + new String(receiveData, "ISO-8859-1"));

		socket.close();

	}

}
