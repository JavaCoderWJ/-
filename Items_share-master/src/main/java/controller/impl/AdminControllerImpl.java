package controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import controller.AdminController;
import service.AdminService;
import util.Status;

@Controller
@RequestMapping("/Admin")
public class AdminControllerImpl implements AdminController {

	private AdminService adminService;
	private Status status;

	@Override
	@Autowired
	public void setadminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@Override
	@RequestMapping("/adminLogin")
	@ResponseBody
	public Status adminLogin(HttpServletRequest request, String adminName, String adminPassword) {
		System.out.println("管理员登陆！登录名："+adminName+",密码："+adminPassword);
		status = adminService.adminLogin(request, adminName, adminPassword);
		return status;
	}

	@Override
	@RequestMapping("/getAllItems")
	@ResponseBody
	public Status getAllItems() {
		System.out.println("管理员查看所有文章");
		status = adminService.getAllItems();
		return status;
	}

	@Override
	@RequestMapping("/getAllFeedback")
	@ResponseBody
	public Status getAllFeedback() {
		System.out.println("管理员查看所有反馈");
		status = adminService.getAllFeedback();
		return status;
	}

	@Override
	@RequestMapping("/getFeedbackById")
	@ResponseBody
	public Status getFeedbackById(int feedback_id) {
		System.out.println("管理员查看id为："+feedback_id+"的反馈信息");
		status = adminService.getFeedbackById(feedback_id);
		return status;
	}

	@Override
	@RequestMapping("/getAllItemsCheckPojo")
	@ResponseBody
	public Status getAllItemsCheckPojo() {
		System.out.println("管理员查看所有待审核文章");
		status = adminService.getAllItemsCheckPojo();
		return status;
	}

	@Override
	@RequestMapping("/insertIntoItems")
	@ResponseBody
	public Status insertIntoItems(HttpServletRequest request, HttpServletResponse response, int check_id) {
		System.out.println("管理员让id为"+check_id+"的待审核文章通过");
		status = adminService.insertIntoItems(request, response, check_id);
		return status;
	}

	@Override
	@RequestMapping("/itemsNotPass")
	@ResponseBody
	public Status itemsNotPass(HttpServletRequest request, HttpServletResponse response, int check_id) {
		System.out.println("管理员让id为"+check_id+"的待审核文章不通过");
		status = adminService.itemsNotPass(request, response, check_id);
		return status;
	}

}
