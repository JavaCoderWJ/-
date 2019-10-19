package controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import controller.HomePageController;
import service.HomePageService;
import util.Status;

@Controller
@RequestMapping("/HomePage")
public class HomePageControllerImpl implements HomePageController {

	private HomePageService homePageService;
	private Status status;

	@Override
	@Autowired
	public void setHomePageService(HomePageService homePageService) {
		this.homePageService=homePageService;
	}

	@Override
	@RequestMapping("/getNewItems")
	@ResponseBody
	public Status getNewItems() {
		System.out.println("最新");
		status = homePageService.getNewItems();
		return status;
	}

	@Override
	@RequestMapping("/getRecommend")
	@ResponseBody
	public Status getRecommend() {
		System.out.println("点击精选文章");
		status = homePageService.getRecommend();
		return status;
	}

	@Override
	@RequestMapping("/getUserPojo")
	@ResponseBody
	public Status getUserPojo(HttpServletRequest request,HttpServletResponse response, String user_name, String user_password) {
		System.out.println(user_name + "登陆！输入密码为：" + user_password);
		status = homePageService.getUserPojo(request,response, user_name, user_password);
		return status;
	}

	@Override
	@RequestMapping("/userRegist")
	@ResponseBody
	public Status userRegist(HttpServletRequest request,
			String user_name, String user_password,
			String father_name,
			String mother_name) {
		System.out.println("用户注册！");
		status = homePageService.userRegist(request, user_name, user_password, father_name, mother_name);
		return status;
	}

	@Override
	@RequestMapping("/findPassword")
	@ResponseBody
	public Status findPassword(String user_name, String father_name, String mother_name) {
		System.out.println("用户找回密码");
		status = homePageService.findPassword(user_name, father_name, mother_name);
		return status;
	}

	@Override
	@RequestMapping("/updatePassword")
	@ResponseBody
	public Status updatePassword(String user_name, String user_password, String new_password) {
		System.out.println("用户更改密码");
		status = homePageService.updatePassword(user_name, user_password, new_password);
		return status;
	}

	@Override
	@RequestMapping("/getShufflingItems")
	@ResponseBody
	public Status getShufflingItems() {
		System.out.println("轮播图接口调用");
		status = homePageService.getShufflingItems();
		return status;
	}

	@Override
	@RequestMapping("/getItemsById")
	@ResponseBody
	public Status getItemsById(int goods_id) {
		System.out.println("点击文章或者图片"+goods_id);
		status = homePageService.getItemsById(goods_id);
		return status;
	}

	@Override
	@RequestMapping("/getItemsByUser")
	@ResponseBody
	public Status getItemsByUser(String goods_type) {
		System.out.println("站内关键字搜索");
		status = homePageService.getItemsByUser(goods_type);
		return status;
	}

	@Override
	@RequestMapping("/getDifferentGoods_type")
	@ResponseBody
	public Status getDifferentGoods_type() {
		System.out.println("分类栏类别");
		status = homePageService.getDifferentGoods_type();
		return status;
	}

	@Override
	@RequestMapping("/getItemsByGoods_type")
	@ResponseBody
	public Status getItemsByGoods_type(String goods_type) {
		System.out.println("点击分类栏类别:"+goods_type);
		status = homePageService.getItemsByGoods_type(goods_type);
		return status;
	}

	@Override
	@RequestMapping("/insertUserFeedback")
	@ResponseBody
	public Status insertUserFeedback(HttpServletRequest request, String feedback) {
		System.out.println("用户反馈");
		status = homePageService.insertUserFeedback(request, feedback);
		return status;
	}

	@Override
	@RequestMapping("/getGoods_type_picture")
	@ResponseBody
	public Status getGoods_type_picture() {
		System.out.println("推荐四个类别");
		status = homePageService.getGoods_type_picture();
		return status;
	}
}
