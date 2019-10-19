package pojo;

public class ItemsCheckPojo {

	private int user_id;
	private String artical_name;
	private String article;
	private String picture_url;
	private String goods_type;
	private String publis_date;
	private String goods_buy_url;
	private int is_reprint;
	private int check_state;
	private int check_id;

	/**
	 * @return the publis_date
	 */
	public String getPublis_date() {
		return publis_date;
	}

	/**
	 * @param publis_date
	 *            the publis_date to set
	 */
	public void setPublis_date(String publis_date) {
		this.publis_date = publis_date;
	}

	/**
	 * @return the check_id
	 */
	public int getCheck_id() {
		return check_id;
	}

	/**
	 * @param check_id
	 *            the check_id to set
	 */
	public void setCheck_id(int check_id) {
		this.check_id = check_id;
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
	 * @return the artical_name
	 */
	public String getArtical_name() {
		return artical_name;
	}

	/**
	 * @param artical_name
	 *            the artical_name to set
	 */
	public void setArtical_name(String artical_name) {
		this.artical_name = artical_name;
	}

	/**
	 * @return the article
	 */
	public String getArticle() {
		return article;
	}

	/**
	 * @param article
	 *            the article to set
	 */
	public void setArticle(String article) {
		this.article = article;
	}

	/**
	 * @return the picture_url
	 */
	public String getPicture_url() {
		return picture_url;
	}

	/**
	 * @param picture_url
	 *            the picture_url to set
	 */
	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}

	/**
	 * @return the goods_type
	 */
	public String getGoods_type() {
		return goods_type;
	}

	/**
	 * @param goods_type
	 *            the goods_type to set
	 */
	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}

	/**
	 * @return the goods_buy_url
	 */
	public String getGoods_buy_url() {
		return goods_buy_url;
	}

	/**
	 * @param goods_buy_url
	 *            the goods_buy_url to set
	 */
	public void setGoods_buy_url(String goods_buy_url) {
		this.goods_buy_url = goods_buy_url;
	}

	/**
	 * @return the is_reprint
	 */
	public int getIs_reprint() {
		return is_reprint;
	}

	/**
	 * @param is_reprint
	 *            the is_reprint to set
	 */
	public void setIs_reprint(int is_reprint) {
		this.is_reprint = is_reprint;
	}

	/**
	 * @return the check_state
	 */
	public int getCheck_state() {
		return check_state;
	}

	/**
	 * @param check_state
	 *            the check_state to set
	 */
	public void setCheck_state(int check_state) {
		this.check_state = check_state;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemsCheckPojo [user_id=" + user_id + ", artical_name=" + artical_name + ", article=" + article
				+ ", picture_url=" + picture_url + ", goods_type=" + goods_type + ", goods_buy_url=" + goods_buy_url
				+ ", is_reprint=" + is_reprint + ", check_state=" + check_state + "]";
	}

}
