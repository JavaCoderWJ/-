package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.AdminPojo;
import pojo.FeedbackPojo;
import pojo.ItemsCheckPojo;
import pojo.ItemsPojo;

public interface AdminDao {

	// 管理员登录
	public AdminPojo admingLogin(@Param("adminName") String adminName, @Param("adminPassword") String adminPassword);

	// 管理员登录用户名检查
	public AdminPojo adminNameCheck(@Param("adminName") String adminName);

	// 返回文章总表
	public List<ItemsPojo> getAllItems();

	// 返回用户反馈总表
	public List<FeedbackPojo> getAllFeedback();

	// 根据反馈id，查看反馈信息
	public FeedbackPojo getFeedbackById(@Param("feedback_id") int feedback_id);

	// 返回全部待审核文章数据
	public List<ItemsCheckPojo> getAllItemsCheckPojo();

	// 使待审核文章通过
	public int insertIntoItems(@Param("user_id") int user_id, @Param("artical_name") String artical_name,
			@Param("article") String article, @Param("picture_url") String picture_url,
			@Param("goods_type") String goods_type, @Param("is_reprint") int is_reprint,
			@Param("is_original") int is_original);


	// 根据check_id查询相应待审核文章
	public ItemsCheckPojo getItemsCheckPojoByCheckID(@Param("check_id") int check_id);

	// 根据check_id更新check_state状态
	public int changeItemsCheckStateByID(@Param("check_id") int check_id);

	// 根据管理员审核情况，发送系统消息
	public int sendMessageToUser(@Param("message") String message, @Param("user_id") int user_id);
	
	//删除审核未通过文章
	public int deleteItemsByID(@Param("check_id") int check_id);

}
