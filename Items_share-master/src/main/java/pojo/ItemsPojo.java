package pojo;

public class ItemsPojo {

	int goods_id;
	String artical_name;
	String article;
	String picture_url;
	String goods_type;
	String publis_date;
	String goods_buy_url;
	int is_original;
	int userful;
	int unuserful;
	int won_praise_for;
	int is_reprint;

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

	UserPojo userPojo;

	/**
	 * @return the userPojo
	 */
	public UserPojo getUserPojo() {
		return userPojo;
	}

	/**
	 * @param userPojo
	 *            the userPojo to set
	 */
	public void setUserPojo(UserPojo userPojo) {
		this.userPojo = userPojo;
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
	 * @return the is_original
	 */
	public int getIs_original() {
		return is_original;
	}

	/**
	 * @param is_original
	 *            the is_original to set
	 */
	public void setIs_original(int is_original) {
		this.is_original = is_original;
	}

	/**
	 * @return the userful
	 */
	public int getUserful() {
		return userful;
	}

	/**
	 * @param userful
	 *            the userful to set
	 */
	public void setUserful(int userful) {
		this.userful = userful;
	}

	/**
	 * @return the unuserful
	 */
	public int getUnuserful() {
		return unuserful;
	}

	/**
	 * @param unuserful
	 *            the unuserful to set
	 */
	public void setUnuserful(int unuserful) {
		this.unuserful = unuserful;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemsPojo [goods_id=" + goods_id + ", artical_name=" + artical_name + ", article=" + article
				+ ", picture_url=" + picture_url + ", goods_type=" + goods_type + ", publis_date=" + publis_date
				+ ", goods_buy_url=" + goods_buy_url + ", is_original=" + is_original + ", userful=" + userful
				+ ", unuserful=" + unuserful + ", won_praise_for=" + won_praise_for + ", userPojo=" + userPojo + "]";
	}


}
