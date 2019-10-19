package service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import util.Status;

public interface PersonalCenterService {

	// 封装当前用户信息，根据session值判断用户信息
	Status getPersonalDetails(HttpServletRequest request);

	// 封装当前用户原创文章信息，根据session值判断用户信息
	Status getOriginal(HttpServletRequest request);

	// 封装当前用户非原创文章信息，根据session值判断用户信息
	Status getNotOriginal(HttpServletRequest request);

	// 封装当前用户获赞文章信息，根据session值判断用户信息
	Status getUserPraise(HttpServletRequest request);

	// 返回用户更改个人信息状态
		Status updatePersonalDetails(HttpServletRequest request, String user_name, String user_intro);

		// 返回个人信息修改之更换头像
		Status updatePersonalHead(HttpServletRequest request, CommonsMultipartFile user_head);

	// 返回用戶更改密碼狀態
	Status updatePassword(HttpServletRequest request, String user_password, String new_password,
			String sure_new_password);

	// 返回当前用户评论过的文章
	Status getUserComent(HttpServletRequest request);

	// 返回当前用户
	Status getOtherUserComment(HttpServletRequest request);

	// 返回系统发送给用户的信息
	Status getSystemMessage(HttpServletRequest request);

	// 返回用户收藏表中数据
	Status getUserCollect(HttpServletRequest request);
	
	// 返回当前用户关注的文章数量
		Status getUserFocusOn(HttpServletRequest request);

}
