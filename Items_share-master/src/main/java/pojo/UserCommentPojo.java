package pojo;

public class UserCommentPojo {

	int goods_id;
	int other_user_id;
	String user_comment;
	String comment_date;
	int is_author_see;
	int comment_id;
	int won_praise_for;

	/**
	 * @return the won_praise_for
	 */
	public int getWon_praise_for() {
		return won_praise_for;
	}

	/**
	 * @param won_praise_for
	 *            the won_praise_for to set
	 */
	public void setWon_praise_for(int won_praise_for) {
		this.won_praise_for = won_praise_for;
	}

	/**
	 * @return the goods_id
	 */
	public int getGoods_id() {
		return goods_id;
	}

	/**
	 * @param goods_id
	 *            the goods_id to set
	 */
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	/**
	 * @return the other_user_id
	 */
	public int getOther_user_id() {
		return other_user_id;
	}

	/**
	 * @param other_user_id
	 *            the other_user_id to set
	 */
	public void setOther_user_id(int other_user_id) {
		this.other_user_id = other_user_id;
	}

	/**
	 * @return the user_comment
	 */
	public String getUser_comment() {
		return user_comment;
	}

	/**
	 * @param user_comment
	 *            the user_comment to set
	 */
	public void setUser_comment(String user_comment) {
		this.user_comment = user_comment;
	}

	/**
	 * @return the comment_date
	 */
	public String getComment_date() {
		return comment_date;
	}

	/**
	 * @param comment_date
	 *            the comment_date to set
	 */
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}

	/**
	 * @return the is_author_see
	 */
	public int getIs_author_see() {
		return is_author_see;
	}

	/**
	 * @param is_author_see
	 *            the is_author_see to set
	 */
	public void setIs_author_see(int is_author_see) {
		this.is_author_see = is_author_see;
	}

	/**
	 * @return the comment_id
	 */
	public int getComment_id() {
		return comment_id;
	}

	/**
	 * @param comment_id
	 *            the comment_id to set
	 */
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserCommentPojo [goods_id=" + goods_id + ", other_user_id=" + other_user_id + ", user_comment="
				+ user_comment + ", comment_date=" + comment_date + ", is_author_see=" + is_author_see + ", comment_id="
				+ comment_id + "]";
	}

}
