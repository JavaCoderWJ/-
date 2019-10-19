package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.GoodsTypePojo;
import pojo.ItemsPojo;
import pojo.UserPojo;

public interface HomePageDao {

	// 返回items表中最新增加的好物
	public List<ItemsPojo> getNewItems();

	// 返回items表中精选推荐（获赞数最高的前10条）
	public List<ItemsPojo> getRecommend();

	// 验证用户是否存在
	public UserPojo check_user_name(@Param("user_name") String user_name);

	// 验证用户登录信息，返回用户信息
	public UserPojo getUserPojo(@Param("user_name") String user_name,
			@Param("user_password") String user_password);

	// 用户注册
	public int userRegist(@Param("user_name") String user_name, @Param("user_password") String user_password,
			@Param("father_name") String father_name, @Param("mother_name") String mother_name);

	// 用户忘记密码，找回密码
	public UserPojo findPassword(@Param("user_name") String user_name, @Param("father_name") String father_name,
			@Param("mother_name") String mother_name);

	// 用户修改密码
	public int updatePassword(@Param("user_name") String user_name,
			@Param("user_password") String user_password, @Param("new_password") String new_password);

	// 轮播图，利用useful降序查询前5条
	public List<ItemsPojo> getShufflingItems();

	// 根据goods_id，查询相应goods_id全部数据
	public ItemsPojo getItemsById(@Param("goods_id") int goods_id);

	// 根据用户输入关键字，搜索好物类型
	public List<ItemsPojo> getItemsByUser(@Param("goods_type") String goods_type);

	// 查询物品分类图片表，返回物品所有类别
	public List<String> getDifferentGoods_type();

	// 根据不同好物文章类别，查询该类别所有数据
	public List<ItemsPojo> getItemsByGoods_type(@Param("goods_type") String goods_type);

	// 将用户返回信息添加至user_feedback表中
	public int insertUserFeedback(@Param("user_id") int user_id, @Param("feedback") String feedback);

	// 增加items表中某goods_id，user_id下的won_praise_for
	public int giveLike(@Param("user_id") int user_id, @Param("goods_id") int goods_id);

	// 后台控制，返回四个好物文章的类别
	public List<GoodsTypePojo> getGoods_type_picture();
}
