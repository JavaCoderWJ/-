package pojo;

public class GoodsTypePojo {
	
	String goods_type;
	String goods_type_picture;

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
	 * @return the goods_type_picture
	 */
	public String getGoods_type_picture() {
		return goods_type_picture;
	}

	/**
	 * @param goods_type_picture
	 *            the goods_type_picture to set
	 */
	public void setGoods_type_picture(String goods_type_picture) {
		this.goods_type_picture = goods_type_picture;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GoodsTypePojo [goods_type=" + goods_type + ", goods_type_picture=" + goods_type_picture + "]";
	}
	
	
	
}
