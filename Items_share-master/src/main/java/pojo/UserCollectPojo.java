package pojo;

public class UserCollectPojo {

	int collect_id;
	String collect_time;
	ItemsPojo itemsPojo;
	UserPojo userPojo;
	
	

	public UserPojo getUserPojo() {
		return userPojo;
	}

	public void setUserPojo(UserPojo userPojo) {
		this.userPojo = userPojo;
	}

	/**
	 * @return the collect_id
	 */
	public int getCollect_id() {
		return collect_id;
	}

	/**
	 * @param collect_id
	 *            the collect_id to set
	 */
	public void setCollect_id(int collect_id) {
		this.collect_id = collect_id;
	}

	/**
	 * @return the collect_time
	 */
	public String getCollect_time() {
		return collect_time;
	}

	/**
	 * @param collect_time
	 *            the collect_time to set
	 */
	public void setCollect_time(String collect_time) {
		this.collect_time = collect_time;
	}

	/**
	 * @return the itemsPojo
	 */
	public ItemsPojo getItemsPojo() {
		return itemsPojo;
	}

	/**
	 * @param itemsPojo
	 *            the itemsPojo to set
	 */
	public void setItemsPojo(ItemsPojo itemsPojo) {
		this.itemsPojo = itemsPojo;
	}

	@Override
	public String toString() {
		return "UserCollectPojo [collect_id=" + collect_id + ", collect_time=" + collect_time + ", itemsPojo="
				+ itemsPojo + ", userPojo=" + userPojo + "]";
	}


}
