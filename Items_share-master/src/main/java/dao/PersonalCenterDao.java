package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.ItemsPojo;
import pojo.SystemMessagePojo;
import pojo.UserCollectPojo;
import pojo.UserCommentPojo;
import pojo.UserPojo;

public interface PersonalCenterDao {

	// 返回当前用户信息
	public UserPojo getPersonalDetails(@Param("user_id") int user_id);

	// 账户设置之个人信息更改
		public int updatePersonalDetails(@Param("user_id") int user_id,
				@Param("user_name") String user_name, @Param("user_intro") String user_intro);

		// 个人信息更改之更换头像
		public int updatePersonalHead(@Param("user_id") int user_id, @Param("user_head") String user_head);

	// 賬戶設置之更改密碼
	public int updatePassword(@Param("user_id") int user_id, @Param("user_password") String user_password);

	// 确认密码是否正确
	public UserPojo selectPassword(@Param("user_id") int user_id, @Param("user_password") String user_password);

	// 返回用户原创好物分享文章
	public List<ItemsPojo> getOriginal(@Param("user_id") int user_id);

	// 返回用户非原创好物分享文章
	public List<ItemsPojo> getNotOriginal(@Param("user_id") int user_id);

	// 返回用户被赞文章
	public List<ItemsPojo> getUserPraise(@Param("user_id") int user_id);

	// 返回用户的评论
	public List<UserCommentPojo> getUserComent(@Param("user_id") int user_id);
	public UserPojo getUserPojo(@Param("user_id") int user_id);
	public UserCommentPojo getUserCommentByCommentID(@Param("comment_id") int comment_id);
	public ItemsPojo getItemsBygoodsID(@Param("goods_id") int goods_id);

	// 查询用户所发表的文章，返回文章ID（goods_id）
	public List<ItemsPojo> getUserItemsGoods_id(@Param("user_id") int user_id);
	
	//返回用户收到的评论
	public List<UserCommentPojo> getOtherUserComment(@Param("goods_id") int goods_id);

	// 返回系统发送给用户的信息
	public List<SystemMessagePojo> getSystemMessage(@Param("user_id") int user_id);

	// 根据用户收藏表，查询用户“对我有用”文章
	public List<UserCollectPojo> getUserCollect(@Param("user_id") int user_id);
}
