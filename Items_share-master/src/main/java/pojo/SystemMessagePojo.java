package pojo;

public class SystemMessagePojo {

	String message;
	int user_id;
	int message_id;
	String date;

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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
	 * @return the message_id
	 */
	public int getMessage_id() {
		return message_id;
	}

	/**
	 * @param message_id
	 *            the message_id to set
	 */
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SystemMessagePojo [message=" + message + ", user_id=" + user_id + ", message_id=" + message_id + "]";
	}

}
