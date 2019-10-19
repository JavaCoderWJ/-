package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.ItemsPojo;
import pojo.UserCommentPojo;

public interface ItemsWacthDao {

	// 返回相应goods_id的好物文章信息
	public ItemsPojo getItemsPojo(@Param("goods_id") int goods_id);
	
	// 增加用户历史浏览记录
	public int addUser_history(@Param("user_id") int user_id, @Param("goods_id") int goods_id);

	// 返回当前作者发表过的好物分享文章
	public List<ItemsPojo> getItemsByAuthor(@Param("user_id") int user_id);

	// 返回当前作者文章被点击小图标后的返回
	public ItemsPojo returnItems(@Param("goods_id") int goods_id);

	// 点击爱心，增加当前文章作者的文章的won_praise_for
	public int addWonPraise(@Param("goods_id") int goods_id);

	// 点击“有 用”，增加“有用数量”
	public int addUserful(@Param("goods_id") int goods_id);

	// 点击“无用”
	public int addUnUserful(@Param("goods_id") int goods_id);

	// 返回当前文章的所有评论
	public List<UserCommentPojo> returnUserComment(@Param("goods_id") int goods_id);

	// 当前用户 发表评论
	public int giveComment(@Param("user_id") int user_id, @Param("goods_id") int goods_id,
			@Param("user_comment") String user_comment);

	// 更新用户评论表，增加评论赞的数量+1
	public int giveCommentPraise(@Param("comment_id") int comment_id);

	// 根据comment_id返回相应评论信息
	public UserCommentPojo returnUserCommentByComment_id(@Param("comment_id") int comment_id);

	// 分享文章
	public int shareItems(@Param("user_id") int user_id,
			@Param("artical_name") String artical_name, @Param("article") String article,
			@Param("picture_url") String picture_url, @Param("goods_type") String goods_type,
			@Param("goods_buy_url") String goods_buy_url, @Param("is_original") int is_original,
			@Param("userful") int userful, @Param("unuserful") int unuserful,
			@Param("won_praise_for") int won_praise_for, @Param("is_reprint") int is_reprint);

	// 获取当前作者有用的数量
		public int comeBackUserful(@Param("user_id") Integer user_id);

		// 获取当前用户所有原创文章总数量
		public int comeBackOrigin(@Param("user_id") Integer user_id);
		
		public int collectGoods(@Param("user_id") Integer user_id,@Param("goods_id") Integer goods_id);
}
