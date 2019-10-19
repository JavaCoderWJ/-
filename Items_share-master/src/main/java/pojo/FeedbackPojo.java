package pojo;

public class FeedbackPojo {

	int user_id;
	String feedback;
	String feedback_time;
	int feedback_id;

	/**
	 * @return the feedback_id
	 */
	public int getFeedback_id() {
		return feedback_id;
	}

	/**
	 * @param feedback_id
	 *            the feedback_id to set
	 */
	public void setFeedback_id(int feedback_id) {
		this.feedback_id = feedback_id;
	}

	/**
	 * @return the feedback_time
	 */
	public String getFeedback_time() {
		return feedback_time;
	}

	/**
	 * @param feedback_time
	 *            the feedback_time to set
	 */
	public void setFeedback_time(String feedback_time) {
		this.feedback_time = feedback_time;
	}

	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback
	 *            the feedback to set
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FeedbackPojo [user_id=" + user_id + ", feedback=" + feedback + ", feedback_time=" + feedback_time
				+ ", feedback_id=" + feedback_id + "]";
	}


}
