package network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 实现了基于定界符的成帧方法
 * @author QZidane
 *
 */
public class DelimFramer implements Framer {

	private InputStream _in; // 数据源
	private static final byte DELIMITER = "\n".getBytes()[0]; // 定界符

	public DelimFramer(InputStream in) {
		_in = in;
	}

	/**
	 * 添加成帧信息并将指定消息输出
	 */
	@Override
	public void frameMsg(byte[] message, OutputStream out) throws IOException {
		
		// ensure that the message does not contain the delimiter
		for (byte b : message) {
			if (b == DELIMITER) {
				throw new IOException(">>Message contains delimiter!");
			}
		}
		
		out.write(message);
		out.write(DELIMITER);
		out.flush();
		

	}

	/**
	 * 如果在遇到定界符之前就已经到了流的终点，则分两种情况：<br>
	 * 一是从帧的构造开始或从遇到前一个定界符以来，缓存区已经接收了一些字节，这时程序将抛出一个异常；<br>
	 * 否则nextMsg()方法将返回null以表示全部消息已接收完
	 */
	@Override
	public byte[] nextMsg() throws IOException {

		ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
		
		int nextByte;
		
		// fetch bytes until find delimiter;
		while( (nextByte=_in.read())!=DELIMITER ){
			if (nextByte==-1) { // end of stream?
				
				if (messageBuffer.size()==0) {
					// if no byte read
					return null;
				} else {
					// if bytes followed by end of stream:framing error
					throw new EOFException(">>>Non-empty message without delimiter!");
				}
			}
			messageBuffer.write(nextByte); // 输出到内存，传给调用方
		}
		
		
		return messageBuffer.toByteArray();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
