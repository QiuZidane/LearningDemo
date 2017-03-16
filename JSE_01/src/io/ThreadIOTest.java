package io;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.Callable;

/**
 * 管道流，主要用于线程间通信<br>
 * PipedInputStream<br>
 * PipedOutputStream<br>
 * 
 * @author EvaZis
 *
 */
public class ThreadIOTest {

	public static void main(String[] args) {

		Send send = new Send("haha\n");
		Receive receive = new Receive();

		try { // 管道连接

			send.getOut().connect(receive.getInput());

		} catch (Exception e) {
			e.printStackTrace();
		}

		new Thread(send).start();

		new Thread(receive).start();

	}

}

/**
 * 消息发送类
 * 
 * @author EvaZis
 *
 */
class Send implements Runnable {

	private PipedOutputStream out = null;
	private String message;

	public Send(String _message) {
		out = new PipedOutputStream();
		message = _message;
	}

	public PipedOutputStream getOut() {
		return this.out;
	}

	public void run() {

		try {

			try {
				out.write(message.getBytes());
			} finally {
				out.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

/**
 * 
 * 消息接收类
 *
 */
class Receive implements Runnable {

	private PipedInputStream input = null;

	public Receive() {
		input = new PipedInputStream();
	}

	public PipedInputStream getInput() {
		return input;
	}

	@Override
	public void run() {

		byte[] b = new byte[1000];
		int len = 0;
		try {
			try {
				len = input.read(b);
			} finally {
				input.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("接收到得内容为:\n" + new String(b));

	}

}
