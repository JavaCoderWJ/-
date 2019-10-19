package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.HomePageService;
import util.Status;

public interface HomePageController {

	void setHomePageService(HomePageService homePageService);

	// 最新
	Status getNewItems();

	// 精选推荐
	Status getRecommend();

	// 登录
	Status getUserPojo(HttpServletRequest request,HttpServletResponse response, String user_name, String user_password);

	// 注册
	Status userRegist(HttpServletRequest request,
			String user_name,
			String user_password,
			String father_name,
			String mother_name);

	// 找回密码
	Status findPassword(String user_name, String father_name, String mother_name);

	// 更改密码
	Status updatePassword(String user_name, String user_password, String new_password);

	// 轮播图
	Status getShufflingItems();

	// 点击图片向发送相应好物文章信息
	Status getItemsById(int goods_id);

	// 站内关键字搜索
	Status getItemsByUser(String goods_type);

	// 分类栏
	Status getDifferentGoods_type();

	// 点击分类栏上面类别
	Status getItemsByGoods_type(String goods_type);

	// 用户反馈
	Status insertUserFeedback(HttpServletRequest request, String feedback);

	// 推荐4个类别
	Status getGoods_type_picture();

}
