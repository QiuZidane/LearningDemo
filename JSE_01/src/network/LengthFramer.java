package network;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 实现了基于长度的成帧方法适用于长度小于65535 (216 ? 1)字节的消息。<br>
 * 发送者首先给出指定消息的长度，并将长度信息以big-endian顺序存入两个字节的整数中，<br>
 * 再将这两个字节放在完整的消息内容前，连同消息一起写入输出流。<br>
 * 在接收端，我们使用DataInputStream以读取整型的长度信息；<br>
 * readFully() 方法将阻塞等待，直到给定的数组完全填满。<br>
 * 值得注意的是，使用这种成帧方法，发送者不需要检查要成帧的消息内容，而只需要检查消息的长度是否超出了限制。
 * 
 * @author QZidane
 *
 */
public class LengthFramer implements Framer {

	public static final int MAXMESSAGELENGTH = 65535;
	public static final int BYTEMASK = 0xff;
	public static final int SHORTMASK = 0xffff;
	public static final int BYTESHIFT = 8;

	private DataInputStream in; // 包装i/o数据

	public LengthFramer(InputStream in) throws IOException {
		this.in = new DataInputStream(in);
	}

	/**
	 * 添加成帧信息并将指定消息输出
	 */
	@Override
	public void frameMsg(byte[] message, OutputStream out) throws IOException {

		if (message.length > MAXMESSAGELENGTH) {
			throw new IOException("message too long");
		}

		// 写前缀
		out.write((message.length >> BYTESHIFT) & BYTEMASK);
		out.write(message.length & BYTEMASK);

		// 写信息
		out.write(message);
		out.flush();
	}

	/**
	 * 扫描制定的流，获取下一条消息
	 */
	@Override
	public byte[] nextMsg() throws IOException {

		int length;
		try {
			length = in.readUnsignedShort();

		} catch (EOFException e) {
			return null;
		}

		byte[] msg = new byte[length];
		in.readFully(msg);
		return msg;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
