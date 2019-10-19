package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import service.PersonalCenterService;
import util.Status;

public interface PersonalCenterController {

	public void setPersonalCenterService(PersonalCenterService personalCenterService);
	// 个人信息
	Status getPersonalDetails(HttpServletRequest request);

	// 原创文章
	Status getOriginal(HttpServletRequest request);

	// 非原创文章
	Status getNotOriginal(HttpServletRequest request);

	// 我的赞
	Status getUserPraise(HttpServletRequest request);

	// 账户设置之个人信息更改
		Status updatePersonalDetails(HttpServletRequest request, String user_name, String user_intro);

		// 个人信息更改之更换头像
		Status updatePersonalHead(HttpServletRequest request, CommonsMultipartFile user_head);

	// 账户设置之修改密码
	Status updatePassword(HttpServletRequest request, @RequestParam("user_password") String user_password,
			@RequestParam("new_password") String new_password,
			@RequestParam("sure_new_password") String sure_new_password);

	// 我发出的评论
	Status getUserComent(HttpServletRequest request);

	// 我收到的评论
	Status getOtherUserComment(HttpServletRequest request);

	// 系统消息
	Status getSystemMessage(HttpServletRequest request);

	// 对我有用
	Status getUserCollect(HttpServletRequest request);
	
	// 关注数量
		Status getUserFocusOn(HttpServletRequest request);


}
