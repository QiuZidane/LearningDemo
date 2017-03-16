package network.votedemo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author QZidane
 *
 * 投票服务<br>
 * 收到投票消息时，调用handleRequest进行处理<br>
 * 对于查询消息，返回候选人得票数<br>
 * 对于投票消息，增加候选人得票数
 *·
 */
public class VoteService {

	// 候选人和选票的映射
	private Map<Integer, Long> results = new HashMap<>();
	
	public VoteMsg handleRequest(VoteMsg msg) {
		
		if (msg.isResponse()) { // 如果是服务器发来的消息，返回消息即可，不处理
			return msg;			
		}
		msg.setResponse(true); // 制作一个返回消息
		
		int candidate = msg.getCandidateID();
		Long count = results.get(candidate);
		
		if (count==null) { // 不存在候选人时，设置为0；
			count = 0L;			
		}
		if (!msg.isInquiry()) {
			results.put(candidate, ++count);			
		}
		msg.setVoteCount(count);
		
		return msg;		
		
	}
	
	
	public static void main(String[] args) {

	}

}
