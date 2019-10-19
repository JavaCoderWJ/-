package pojo;

public class ArticlePicturePojo {

	String picture_url;
	int picture_id;
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
	 * @return the picture_id
	 */
	public int getPicture_id() {
		return picture_id;
	}
	
	/**
	 * @param picture_id
	 *            the picture_id to set
	 */
	public void setPicture_id(int picture_id) {
		this.picture_id = picture_id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArticlePicturePojo [picture_url=" + picture_url + ", picture_id=" + picture_id + "]";
	}


}
