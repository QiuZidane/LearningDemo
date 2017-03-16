package network.votedemo;

import innerClass.Outer.inner;

/**
 *
 * 该类用于表示客户端和服务端的消息 <br>
 * 通过域来区分是投票还是查询
 *
 */
public class VoteMsg {

	private boolean isInquiry; // true-查询 false-投票
	private boolean isResponse; // true-response from server
	private int candidateID; // 候选人ID in [0,1000]
	private long voteCount; // nonZero, only in response
	public static final int MAX_CANDIDATE_ID = 1000;

	public VoteMsg(boolean isResponse, boolean isInquiry, int candidateID, long voteCount)
			throws IllegalArgumentException {
		// 入参检查
		if (voteCount != 0 && !isResponse) {
			throw new IllegalArgumentException(">>Request vote count must be zero!");
		}

		if (candidateID < 0 || candidateID > MAX_CANDIDATE_ID) {
			throw new IllegalArgumentException(">>Bad Candidate ID:" + candidateID);

		}

		if (voteCount < 0) {
			throw new IllegalArgumentException(">>Total count must be >= zero");

		}

		this.candidateID = candidateID;
		this.isInquiry = isInquiry;
		this.isResponse = isResponse;
		this.voteCount = voteCount;

	}

	public void setInquiry(boolean isInquiry) {
		this.isInquiry = isInquiry;
	}

	public void setResponse(boolean isResponse) {
		this.isResponse = isResponse;
	}

	public boolean isInquiry() {
		return isInquiry;
	}

	public boolean isResponse() {
		return isResponse;

	}

	public void setCandidateID(int candidateID) throws IllegalArgumentException {
		if (candidateID < 0 || candidateID > MAX_CANDIDATE_ID) {
			throw new IllegalArgumentException("Bad CandidateID : " + candidateID);
		}

		this.candidateID = candidateID;
	}

	public int getCandidateID() {
		return candidateID;
	}

	public void setVoteCount(long count) throws IllegalArgumentException {
		if ((count != 0 && !isResponse) || count < 0) {
			throw new IllegalArgumentException("Bad vote count");
		}
		voteCount = count;
	}

	public long getVoteCount() {
		return voteCount;
	}

	@Override
	public String toString() {
		String res = (isInquiry ? "inquiry" : "vote") + " for candidate " + candidateID;
		if (isResponse) {
			res = "response to " + res + " who now has " + voteCount + "vote(s)";
		}
		return res;
	}

	public static void main(String[] args) {

	}

}
