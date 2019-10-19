package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Status;

//对mybatis返回的数据，进行封装，逻辑处理
public interface HomePageService {

	// 封装最新好物的查询结果
	Status getNewItems();

	// 封装精选查询结果
	Status getRecommend();

	// 返回登录验证结果
	Status getUserPojo(HttpServletRequest request,HttpServletResponse response, String user_name, String user_password);

	// 用户注册，返回注册情况
	Status userRegist(HttpServletRequest request, String user_name, String user_password,
			String father_name,
			String mother_name);

	// 用户找回密码，密保验证
	Status findPassword(String user_name, String father_name, String mother_name);

	// 用户更改密码，返回更新状态
	Status updatePassword(String user_name, String user_password, String new_password);

	// 轮播图数据封装处理
	Status getShufflingItems();

	// 返回相应goods_id全部数据
	Status getItemsById(int goods_id);

	// 对用户输入的字段进行逻辑处理，返回dao成查询的goods_type类型数据
	Status getItemsByUser(String goods_type);

	// 封装dao成查询出来的所有好物分享文章类型
	Status getDifferentGoods_type();

	// 封装同一类别的好物文章所有数据
	Status getItemsByGoods_type(String goods_type);

	// 返回用户提交反馈信息到数据表的情况
	Status insertUserFeedback(HttpServletRequest request, String feedback);

	// 返回点赞情况
	Status giveLike(int user_id, int goods_id);

	// 返回四个好物文章的类别
	Status getGoods_type_picture();

	// // 根据不同类别“有用”总和比较，返回最大的前4个类别的数据
	// Status getListItemsByGoods_typeMostUseful();

}
