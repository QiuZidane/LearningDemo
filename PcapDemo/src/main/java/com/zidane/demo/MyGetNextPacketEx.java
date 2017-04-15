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
import org.pcap4j.packet.IpV4Rfc791Tos;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.UdpPacket;
import org.pcap4j.packet.UnknownPacket;
import org.pcap4j.packet.namednumber.EtherType;
import org.pcap4j.packet.namednumber.IpNumber;
import org.pcap4j.packet.namednumber.IpVersion;
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

	private static EthernetPacket tcpPckBuilder()
			throws IllegalRawDataException, UnknownHostException {

		InetAddress dstAddr = Inet4Address.getByName("116.251.204.42");
		InetAddress srcAddr = Inet4Address.getByName("192.168.1.7");
		String dst_mac = "a0:63:91:de:6a:c0";
		String src_mac = "30:e3:7a:0a:55:78";

		EthernetPacket etherPacket = EthernetPacket.newPacket(packet.getRawData(), 0,
				packet.getRawData().length);
		IpV4Packet ipv4Packet = IpV4Packet.newPacket(etherPacket.getPayload().getRawData(), 0,
				etherPacket.getPayload().getRawData().length);
		TcpPacket tcpPacket = TcpPacket.newPacket(ipv4Packet.getPayload().getRawData(), 0,
				ipv4Packet.getPayload().getRawData().length);

		System.err.println("******");
		System.err.println("src port = " + tcpPacket.getHeader().getSrcPort());
		System.err.println("syn = " + tcpPacket.getHeader().getSyn());
		System.err.println("ack = " + tcpPacket.getHeader().getAck());
		System.err.println("******");
		System.err.println("");

		if (tcpPacket.getPayload() == null) { // 不是所有tcp包都带有数据的
			return null;
		} else {
			System.err.println("TCP PAYLOAD >>> ");
			System.err.println(new String(tcpPacket.getPayload().getRawData()));

			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}


		byte[] synData = "00 00 00 00 00 00 00 00".getBytes();

		TcpPacket.Builder tcpBuilder = new TcpPacket.Builder()
				.srcPort(new TcpPort((short) 64073, "src"))
				.dstPort(new TcpPort((short) 80, "dst"))
				.payloadBuilder(new UnknownPacket.Builder().rawData(tcpPacket.getPayload().getRawData()));

		IpV4Packet.Builder ipv4Builder = ipv4Packet.getBuilder()
				.dstAddr((Inet4Address) dstAddr)
				.srcAddr((Inet4Address) srcAddr)
				.payloadBuilder(tcpBuilder);

		EthernetPacket sendPacket = etherPacket.getBuilder()
				.dstAddr(MacAddress.getByName(dst_mac))
				.srcAddr(MacAddress.getByName(src_mac))
				.payloadBuilder(ipv4Builder).build();

		return sendPacket;

	}

//	private static EthernetPacket tcpPckBuilder_syn()
//			throws IllegalRawDataException, UnknownHostException {
//
//		InetAddress dstAddr = Inet4Address.getByName("116.251.204.42");
//		InetAddress srcAddr = Inet4Address.getByName("192.168.1.7");
//		String dst_mac = "a0:63:91:de:6a:c0";
//		String src_mac = "30:e3:7a:0a:55:78";
//
//		byte[] synData = "00 00 00 00 00 00 00 00".getBytes();
//
//		TcpPacket.Builder tcpBuilder = new TcpPacket.Builder()
//				.srcPort(new TcpPort((short) 64073, "src"))
//				.dstPort(new TcpPort((short) 80, "dst"))
//				.payloadBuilder(
//						new UnknownPacket.Builder().rawData(synData));
//		// new
//		// UnknownPacket.Builder().rawData(tcpPacket.getPayload().getRawData()));
//
//		IpV4Packet.Builder ipV4Builder = new IpV4Packet.Builder();
//		ipV4Builder
//				.version(IpVersion.IPV4)
//				.tos(IpV4Rfc791Tos.newInstance((byte) 0))
//				.ttl((byte) 100)
//				.protocol(IpNumber.TCP)
//				.dstAddr((Inet4Address) dstAddr)
//				.srcAddr((Inet4Address) srcAddr)
//				.payloadBuilder(tcpBuilder)
//				.correctChecksumAtBuild(true)
//				.correctLengthAtBuild(true);
//
//		return sendPacket;
//
//	}

	public static void main(String[] args) throws PcapNativeException, NotOpenException,
			UnknownHostException, IllegalRawDataException {
		String strSrcIpAddress = "192.168.1.7"; // 源地址
		// String strDstIpAddress = "60.205.8.179"; // CSDN帖子发布
		String strDstIpAddress = "116.251.204.42"; // 阮一峰博客
		// String filter = "src host " + strSrcIpAddress + " and dst host " +
		// strDstIpAddress;
		String filter = "src host " + strDstIpAddress + " or dst host " + strDstIpAddress;

		System.out.println(COUNT_KEY + ": " + COUNT);
		System.out.println(READ_TIMEOUT_KEY + ": " + READ_TIMEOUT);
		System.out.println(SNAPLEN_KEY + ": " + SNAPLEN);
		System.out.println("\n");

		PcapNetworkInterface nif;

		InetAddress addr = InetAddress.getByName(strSrcIpAddress);
		nif = Pcaps.getDevByAddress(addr);

		System.out.println(nif.getName() + "(" + nif.getDescription() + ")");

		PcapHandle handle = nif.openLive(SNAPLEN, PromiscuousMode.PROMISCUOUS, READ_TIMEOUT);

		handle.setFilter("", BpfCompileMode.OPTIMIZE);

		long start = System.currentTimeMillis();

		while (true) {
			try {
				packet = handle.getNextPacketEx();

				
				boolean con1 = packet.toString().contains("Source address: /192.168.1.7");
				boolean con2 = packet.toString().contains("Destination address: /192.168.1.6");
				
				if (con1 && con2) {
					System.out.println(handle.getTimestamp());
					System.out.println(packet);
				}
				
				

				long end = System.currentTimeMillis();
				if (end - start > 300 * 1000) {
					break;
				}

			} catch (TimeoutException e) {} catch (EOFException e) {
				e.printStackTrace();
				break;
			}
		}

//		EthernetPacket ePacket = tcpPckBuilder();
//		try {
//			handle.sendPacket(ePacket);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		handle.close();
	}

}
