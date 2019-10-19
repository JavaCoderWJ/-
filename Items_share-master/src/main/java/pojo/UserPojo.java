package pojo;

public class UserPojo {

	int user_id;
	String user_name;
	String user_password;
	String father_name;
	String mother_name;
	String user_head;
	String user_intro;
	int fans;
	int won_praise_for;
	String create_date;
	int user_integal;
	int user_expericence;

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
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name
	 *            the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return the user_password
	 */
	public String getUser_password() {
		return user_password;
	}

	/**
	 * @param user_password
	 *            the user_password to set
	 */
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	/**
	 * @return the father_name
	 */
	public String getFather_name() {
		return father_name;
	}

	/**
	 * @param father_name
	 *            the father_name to set
	 */
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	/**
	 * @return the mother_name
	 */
	public String getMother_name() {
		return mother_name;
	}

	/**
	 * @param mother_name
	 *            the mother_name to set
	 */
	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}

	/**
	 * @return the user_head
	 */
	public String getUser_head() {
		return user_head;
	}

	/**
	 * @param user_head
	 *            the user_head to set
	 */
	public void setUser_head(String user_head) {
		this.user_head = user_head;
	}

	/**
	 * @return the user_intro
	 */
	public String getUser_intro() {
		return user_intro;
	}

	/**
	 * @param user_intro
	 *            the user_intro to set
	 */
	public void setUser_intro(String user_intro) {
		this.user_intro = user_intro;
	}

	/**
	 * @return the fans
	 */
	public int getFans() {
		return fans;
	}

	/**
	 * @param fans
	 *            the fans to set
	 */
	public void setFans(int fans) {
		this.fans = fans;
	}

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
	 * @return the create_date
	 */
	public String getCreate_date() {
		return create_date;
	}

	/**
	 * @param create_date
	 *            the create_date to set
	 */
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	/**
	 * @return the user_integal
	 */
	public int getUser_integal() {
		return user_integal;
	}

	/**
	 * @param user_integal
	 *            the user_integal to set
	 */
	public void setUser_integal(int user_integal) {
		this.user_integal = user_integal;
	}

	/**
	 * @return the user_expericence
	 */
	public int getUser_expericence() {
		return user_expericence;
	}

	/**
	 * @param user_expericence
	 *            the user_expericence to set
	 */
	public void setUser_expericence(int user_expericence) {
		this.user_expericence = user_expericence;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserPojo [user_id=" + user_id + ", user_name=" + user_name + ", user_password=" + user_password
				+ ", father_name=" + father_name + ", mother_name=" + mother_name + ", user_head=" + user_head
				+ ", user_intro=" + user_intro + ", fans=" + fans + ", won_praise_for=" + won_praise_for
				+ ", create_date=" + create_date + ", user_integal=" + user_integal + ", user_expericence="
				+ user_expericence + "]";
	}


}
