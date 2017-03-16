package network.votedemo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * Wire Format "VOTEPROTO" <"v"|"i"> [<RESPFLAG>] <CANDIDATE> [<VOTECOUNT>]
 * 
 *
 */
public class VoteMsgTextCoder implements VoteMsgCoder {

	public static final String MAGIC = "Voting";
	public static final String VOTESTR = "v";
	public static final String INQSTR = "i";
	public static final String RESPONSESTR = "R";
	public static final String CHARSETNAME = "US-ASCII";
	public static final String DELIMSTR = " ";
	public static final int MAX_WIRE_LENGTH = 2000;

	/**
	 * 序列化方法:输出消息给报文
	 */
	@Override
	public byte[] toWire(VoteMsg msg) throws IOException {

		String msgString = MAGIC + DELIMSTR + (msg.isInquiry() ? INQSTR : VOTESTR) + DELIMSTR
				+ (msg.isResponse() ? RESPONSESTR + DELIMSTR : " ")
				+ Integer.toString(msg.getCandidateID()) + DELIMSTR
				+ Long.toString(msg.getVoteCount());
		
		byte[] data = msgString.getBytes(CHARSETNAME);

		return data;
	}

	@Override
	public VoteMsg fromWire(byte[] input) throws IOException {
		
		ByteArrayInputStream msgStream = new ByteArrayInputStream(input);
		InputStreamReader iStreamReader = new InputStreamReader(msgStream, CHARSETNAME);
		Scanner s = new Scanner(iStreamReader);
		boolean isInquiry;
		boolean isResponse;
		int candidateID;
		long voteCount;
		String token;
		
		/**
		 *   Wire Format "VOTEPROTO" <"v"|"i"> [<RESPFLAG>] <CANDIDATE> [<VOTECOUNT>] <br>
		 *  根据格式分析:
		 */
		try {
			// 因为用空格作为定界符，所以用next(), next()遇到空格会返回之前读到的有效字符
			token = s.next();
			
			if (!token.equals(MAGIC)) {
				throw new IOException("Bad magic string: "+token);
			}
			
			token = s.next();
			if (token.equals(VOTESTR)) {
				isInquiry = false;
			} else if (!token.equals(INQSTR)){
				throw new IOException("Bad vote/inq indicator: "+token);
			} else {
				isInquiry = true;
			}
			
			token = s.next();
			if (token.equals(RESPONSESTR)) {
				isResponse = true;
				token = s.next();
			} else {
				isResponse = false;
			}
			
			// 目前token是CANDIDATE
			candidateID = Integer.parseInt(token);
			if (isResponse) {
				token = s.next();
				voteCount = Long.parseLong(token);
			} else {
				voteCount = 0;
			}
			
		} catch (Exception e) {
			throw new IOException("Parse error");
		
		}
		
		return new VoteMsg(isResponse, isInquiry, candidateID, voteCount);
	}

	public static void main(String[] args) {

	}

}
