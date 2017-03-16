package network.votedemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import network.DelimFramer;
import network.Framer;

public class VoteServerTCP {
	
	private static final int PORT = 9090;

	
	public static void main(String[] args) {

		try {
			
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			try {
				
				// 序列化处理实器
				VoteMsgCoder coder = new VoteMsgTextCoder();
				VoteService service = new VoteService();
				
				while(true){
					
					Socket clientSock = serverSocket.accept();
					System.out.println("正在处理客户端:"+clientSock.getRemoteSocketAddress()+"发来的消息");
					
					// 成帧和解析处理器
					Framer framer = new DelimFramer(clientSock.getInputStream());
					
					try {
						
						byte[] req;
						
						while((req=framer.nextMsg())!=null){
							System.out.println("接收到的消息字节数:"+req.length);
							VoteMsg responseMsg = service.handleRequest(coder.fromWire(req));
							framer.frameMsg(coder.toWire(responseMsg), clientSock.getOutputStream());
						}
						
					} catch (IOException e) {
						System.err.println("Error handling client: "+e.getMessage());
					} finally {
						System.out.println("Closing connection");
						clientSock.close();
					}
					
					
				}
				
				
				
			} finally {
				// TODO: handle finally clause
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
