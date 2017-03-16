package network.votedemo;

import java.io.IOException;

/**
 * 提供了对投票消息进行序列化和反序列化的方法
 *
 */
public interface VoteMsgCoder {
	
	/**
	 * 序列化方法:输出消息给报文
	 * 
	 * @param msg
	 * @return
	 * @throws IOException
	 */
	byte[] toWire(VoteMsg msg) throws IOException; 
	
	/**
	 * 反序列化方法:从电报获取消息
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	VoteMsg fromWire(byte[] input) throws IOException;

}
