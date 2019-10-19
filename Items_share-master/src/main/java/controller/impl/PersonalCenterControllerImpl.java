package controller.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import controller.PersonalCenterController;
import service.PersonalCenterService;
import util.Status;

@Controller
@RequestMapping("/PersonalCenter")
public class PersonalCenterControllerImpl implements PersonalCenterController {

	private PersonalCenterService personalCenterService;
	private Status status;
	
	@Override
	@Autowired
	public void setPersonalCenterService(PersonalCenterService personalCenterService) {
		this.personalCenterService = personalCenterService;

	}

	@Override
	@RequestMapping("/getPersonalDetails")
	@ResponseBody
	public Status getPersonalDetails(HttpServletRequest request) {
		System.out.println(" 个人信息");
		status = personalCenterService.getPersonalDetails(request);
		return status;
	}

	@Override
	@RequestMapping("/getOriginal")
	@ResponseBody
	public Status getOriginal(HttpServletRequest request) {
		System.out.println("原创文章");
		status = personalCenterService.getOriginal(request);
		return status;
	}

	@Override
	@RequestMapping("/getNotOriginal")
	@ResponseBody
	public Status getNotOriginal(HttpServletRequest request) {
		System.out.println("非原创文章");
		status = personalCenterService.getNotOriginal(request);
		return status;
	}

	@Override
	@RequestMapping("/getUserPraise")
	@ResponseBody
	public Status getUserPraise(HttpServletRequest request) {
		System.out.println(" 我的赞");
		status = personalCenterService.getUserPraise(request);
		return status;
	}

	@Override
	@RequestMapping("/updatePersonalDetails")
	@ResponseBody
	public Status updatePersonalDetails(HttpServletRequest request, String user_name, String user_intro) {
		System.out.println("更改个人信息"+user_name+":"+user_intro);
		status = personalCenterService.updatePersonalDetails(request, user_name, user_intro);
		return status;
	}

	@Override
	@RequestMapping("/updatePersonalHead")
	@ResponseBody
	public Status updatePersonalHead(HttpServletRequest request,
			@RequestParam("user_head") CommonsMultipartFile user_head) {
		System.out.println("更换头像");
		status = personalCenterService.updatePersonalHead(request, user_head);
		return status;
	}

	@Override
	@RequestMapping("/updatePassword")
	@ResponseBody
	public Status updatePassword(HttpServletRequest request, String user_password, String new_password,
			String sure_new_password) {
		System.out.println("账户设置之修改密码");
		status = personalCenterService.updatePassword(request, user_password, new_password, sure_new_password);
		return status;
	}

	@Override
	@RequestMapping("/getUserComent")
	@ResponseBody
	public Status getUserComent(HttpServletRequest request) {
		System.out.println("我发出的评论");
		status = personalCenterService.getUserComent(request);
		return status;
	}

	@Override
	@RequestMapping("/getOtherUserComment")
	@ResponseBody
	public Status getOtherUserComment(HttpServletRequest request) {
		System.out.println("我收到的评论");
		status = personalCenterService.getOtherUserComment(request);
		return status;
	}

	@Override
	@RequestMapping("/getSystemMessage")
	@ResponseBody
	public Status getSystemMessage(HttpServletRequest request) {
		System.out.println("系统消息");
		status = personalCenterService.getSystemMessage(request);
		return status;
	}

	@Override
	@RequestMapping("/getUserCollect")
	@ResponseBody
	public Status getUserCollect(HttpServletRequest request) {
		System.out.println("对我有用");
		status = personalCenterService.getUserCollect(request);
		return status;
	}

	@Override
	@RequestMapping("/getUserFocusOn")
	@ResponseBody
	public Status getUserFocusOn(HttpServletRequest request) {
		status = personalCenterService.getUserFocusOn(request);
		return status;
	}
}
