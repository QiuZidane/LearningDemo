package network.votedemo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import network.DelimFramer;
import network.Framer;
import network.LengthFramer;

/**
 * 
 * 投票的客户端 <br>
 * 通过TCP连接到投票服务端 <br>
 * 投票后发送一个查询，接受查询和投票结果 <br>
 *
 * @author QZidane
 */
public class VoteClientTCP {

	public static final int CANDIDATEID = 888;
	public static final String SERVER = "127.0.0.1";
	public static final int PORT = 9090;

	public static void main(String[] args) {

		try {
			Socket socket = new Socket(SERVER, PORT);
			try {

				OutputStream out = socket.getOutputStream();

				VoteMsgTextCoder coder = new VoteMsgTextCoder();

				Framer framer = new DelimFramer(socket.getInputStream());

				// 创建一个查询实例
				VoteMsg msg = new VoteMsg(false, true, CANDIDATEID, 0);

				// 序列化请求信息
				byte[] encodedMsg = coder.toWire(msg);
				System.out.println("Sending Inquiry (" + encodedMsg.length + " bytes):");
				System.out.println(msg);
				framer.frameMsg(encodedMsg, out); // 输出到服务端

				// 发送一个投票请求
				msg.setInquiry(false);
				encodedMsg = coder.toWire(msg);
				System.out.println("Sending Vote (" + encodedMsg.length + " bytes):");
				framer.frameMsg(encodedMsg, out); // 输出到服务端

				// 接收查询回复
				encodedMsg = framer.nextMsg();
				msg = coder.fromWire(encodedMsg); // 反序列化
				System.out.println("Receive Inquiry Response (" + encodedMsg.length + " bytes):");
				System.out.println(msg);

				// 接收投票回复
				msg = coder.fromWire(framer.nextMsg());
				System.out.println("Receive Vote Response (" + encodedMsg.length + " bytes):");
				System.out.println(msg);
			} finally {
				socket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
