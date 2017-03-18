package com.zidane.demo;

import java.io.EOFException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.pcap4j.core.BpfProgram.BpfCompileMode;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.PcapNetworkInterface.PromiscuousMode;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IllegalRawDataException;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.UdpPacket;
import org.pcap4j.packet.UnknownPacket;
import org.pcap4j.packet.namednumber.TcpPort;
import org.pcap4j.util.MacAddress;

@SuppressWarnings("javadoc")
public class MyGetNextPacketEx {

	private static final String COUNT_KEY = MyGetNextPacketEx.class.getName() + ".count";
	private static final int COUNT = Integer.getInteger(COUNT_KEY, 50);

	private static final String READ_TIMEOUT_KEY = MyGetNextPacketEx.class.getName()
			+ ".readTimeout";
	private static final int READ_TIMEOUT = Integer.getInteger(READ_TIMEOUT_KEY, 10); // [ms]

	private static final String SNAPLEN_KEY = MyGetNextPacketEx.class.getName() + ".snaplen";
	private static final int SNAPLEN = Integer.getInteger(SNAPLEN_KEY, 65536); // [bytes]

	private static Packet packet;
	private static Packet sendPck;

	private MyGetNextPacketEx() {}

	private static EthernetPacket tcpPckBuilder() throws IllegalRawDataException, UnknownHostException {
		
		InetAddress dstAddr = Inet4Address.getByName("116.251.204.42");
		InetAddress srcAddr = Inet4Address.getByName("192.168.1.7");
		String dst_mac ="a0:63:91:de:6a:c0";
		String src_mac="30:e3:7a:0a:55:78";
		
		EthernetPacket etherPacket = EthernetPacket.newPacket(packet.getRawData(), 0,
				packet.getRawData().length);
		IpV4Packet ipv4Packet = IpV4Packet.newPacket(etherPacket.getPayload().getRawData(), 0,
				etherPacket.getPayload().getRawData().length);
		TcpPacket tcpPacket = TcpPacket.newPacket(ipv4Packet.getPayload().getRawData(), 0, ipv4Packet.getPayload().getRawData().length);
		
		if (tcpPacket.getPayload() == null) { // 不是所有tcp包都带有数据的	
			return null;
		} else {
			System.err.println("TCP PAYLOAD >>> ");
//			System.err.println(tcpPacket.getPayload());
			System.err.println(new String(tcpPacket.getPayload().getRawData()));
			
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
//		String httpRequest = "47 45 54 20 2f 62 6c 6f 67 2f 20 48 54 54 50 2f 31 2e 31 0d 0a 48 6f 73 74 3a 20 77 77 77 2e 72 75 61 6e 79 69 66 65 6e 67 2e 63 6f 6d 0d 0a 43 6f 6e 6e 65 63 74 69 6f 6e 3a 20 6b 65 65 70 2d 61 6c 69 76 65 0d 0a 43 61 63 68 65 2d 43 6f 6e 74 72 6f 6c 3a 20 6d 61 78 2d 61 67 65 3d 30 0d 0a 55 70 67 72 61 64 65 2d 49 6e 73 65 63 75 72 65 2d 52 65 71 75 65 73 74 73 3a 20 31 0d 0a 55 73 65 72 2d 41 67 65 6e 74 3a 20 4d 6f 7a 69 6c 6c 61 2f 35 2e 30 20 28 57 69 6e 64 6f 77 73 20 4e 54 20 31 30 2e 30 3b 20 57 69 6e 36 34 3b 20 78 36 34 29 20 41 70 70 6c 65 57 65 62 4b 69 74 2f 35 33 37 2e 33 36 20 28 4b 48 54 4d 4c 2c 20 6c 69 6b 65 20 47 65 63 6b 6f 29 20 43 68 72 6f 6d 65 2f 35 36 2e 30 2e 32 39 32 34 2e 38 37 20 53 61 66 61 72 69 2f 35 33 37 2e 33 36 0d 0a 41 63 63 65 70 74 3a 20 74 65 78 74 2f 68 74 6d 6c 2c 61 70 70 6c 69 63 61 74 69 6f 6e 2f 78 68 74 6d 6c 2b 78 6d 6c 2c 61 70 70 6c 69 63 61 74 69 6f 6e 2f 78 6d 6c 3b 71 3d 30 2e 39 2c 69 6d 61 67 65 2f 77 65 62 70 2c 2a 2f 2a 3b 71 3d 30 2e 38 0d 0a 41 63 63 65 70 74 2d 45 6e 63 6f 64 69 6e 67 3a 20 67 7a 69 70 2c 20 64 65 66 6c 61 74 65 2c 20 73 64 63 68 0d 0a 41 63 63 65 70 74 2d 4c 61 6e 67 75 61 67 65 3a 20 7a 68 2d 43 4e 2c 7a 68 3b 71 3d 30 2e 38 2c 65 6e 3b 71 3d 30 2e 36 0d 0a 43 6f 6f 6b 69 65 3a 20 5f 67 61 3d 47 41 31 2e 32 2e 39 31 32 38 38 31 31 33 31 2e 31 34 38 39 37 36 33 36 32 33 0d 0a 49 66 2d 4e 6f 6e 65 2d 4d 61 74 63 68 3a 20 22 33 38 31 32 2d 35 34 62 30 31 62 31 32 62 32 61 63 30 2d 67 7a 69 70 22 0d 0a 49 66 2d 4d 6f 64 69 66 69 65 64 2d 53 69 6e 63 65 3a 20 53 61 74 2c 20 31 38 20 4d 61 72 20 32 30 31 37 20 31 33 3a 35 38 3a 34 33 20 47 4d 54 0d 0a 0d 0a";
//		if (tcpPacket.getPayload().toString().contains(httpRequest)) {
//			System.err.println(">>>>HTTP请求<<<<");
//			System.err.println(new String(tcpPacket.getPayload().getRawData()));
//			
//			try {
//				TimeUnit.SECONDS.sleep(10);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//		} else {
//			return null;
//		}
		
		
		TcpPacket.Builder tcpBuilder = new TcpPacket.Builder()
				.srcPort(new TcpPort((short)64073,"src"))
				.dstPort(new TcpPort((short)80,"dst"))
				.payloadBuilder(new UnknownPacket.Builder().rawData(tcpPacket.getPayload().getRawData()));
		
		IpV4Packet.Builder ipv4Builder = ipv4Packet.getBuilder()
				.dstAddr((Inet4Address)dstAddr)
				.srcAddr((Inet4Address)srcAddr)
				.payloadBuilder(tcpBuilder);
		
		EthernetPacket sendPacket = etherPacket.getBuilder()
				.dstAddr(MacAddress.getByName(dst_mac))
				.srcAddr(MacAddress.getByName(src_mac))
				.payloadBuilder(ipv4Builder).build();
		
		return sendPacket;
		
	}

	public static void main(String[] args)
			throws PcapNativeException, NotOpenException, UnknownHostException, IllegalRawDataException {
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
		long start = System.currentTimeMillis();

		while (true) {
			try {
				packet = handle.getNextPacketEx();
				// System.out.println(handle.getTimestamp());
				// System.out.println(packet);
				long end = System.currentTimeMillis();
				if (end - start > 300 * 1000) {
					break;
				}
				
				EthernetPacket ePacket = tcpPckBuilder();
				if (ePacket != null) {
					System.err.println("发送>>>>");
//					System.err.println(ePacket.toString());
					handle.sendPacket(ePacket);
				}
				
				

			} catch (TimeoutException e) {} catch (EOFException e) {
				e.printStackTrace();
				break;
			}
		}

		handle.close();
	}

}
