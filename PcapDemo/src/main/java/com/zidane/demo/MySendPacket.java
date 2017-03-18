package com.zidane.demo;

import java.io.EOFException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;
import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;

@SuppressWarnings("javadoc")
public class MySendPacket {

	private static final String COUNT_KEY = MySendPacket.class.getName() + ".count";
	private static final int COUNT = Integer.getInteger(COUNT_KEY, 50);

	private static final String READ_TIMEOUT_KEY = MySendPacket.class.getName()
			+ ".readTimeout";
	private static final int READ_TIMEOUT = Integer.getInteger(READ_TIMEOUT_KEY, 10); // [ms]

	private static final String SNAPLEN_KEY = MySendPacket.class.getName() + ".snaplen";
	private static final int SNAPLEN = Integer.getInteger(SNAPLEN_KEY, 65536); // [bytes]

	private MySendPacket() {}

	public static void main(String[] args)
			throws PcapNativeException, NotOpenException, UnknownHostException {
		String strSrcIpAddress = "192.168.1.7"; // 源地址
		// String strDstIpAddress = "60.205.8.179"; // CSDN帖子发布
		String strDstIpAddress = "116.251.204.42"; // 阮一峰博客
		String filter = "src host " + strSrcIpAddress + " and dst host " + strDstIpAddress;

		System.out.println(COUNT_KEY + ": " + COUNT);
		System.out.println(READ_TIMEOUT_KEY + ": " + READ_TIMEOUT);
		System.out.println(SNAPLEN_KEY + ": " + SNAPLEN);
		System.out.println("\n");

		PcapNetworkInterface nif;

		InetAddress addr = InetAddress.getByName(strSrcIpAddress);
		nif = Pcaps.getDevByAddress(addr);

		System.out.println(nif.getName() + "(" + nif.getDescription() + ")");

		PcapHandle handle = nif.openLive(SNAPLEN, PromiscuousMode.PROMISCUOUS, READ_TIMEOUT);

		handle.setFilter(filter, BpfCompileMode.OPTIMIZE);

		int num = 0;
		while (true) {
			try {
				Packet packet = handle.getNextPacketEx();
				System.out.println(handle.getTimestamp());
				System.out.println(packet);
				num++;
				if (num >= COUNT) {
					break;
				}
			} catch (TimeoutException e) {} catch (EOFException e) {
				e.printStackTrace();
			}
		}

		handle.close();
	}

}
