package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AdminService;
import util.Status;

public interface AdminController {

	// 管理员登录
	public Status adminLogin(HttpServletRequest request, String adminName, String adminPassword);

	void setadminService(AdminService adminService);

	// 获取文章总表
	public Status getAllItems();
	// 根据文章goods_id查看文章调用：http://140.143.4.223:8080/Items_share/HomePage/getItemsById

	// 获取用户反馈总表
	public Status getAllFeedback();

	// 根据管理员点击查看，根据返回的反馈id查询
	public Status getFeedbackById(int feedback_id);

	// 获取待审核文章总表
	public Status getAllItemsCheckPojo();

	// 待审核文章通过
	public Status insertIntoItems(HttpServletRequest request, HttpServletResponse response, int check_id);

	// 待审核文章不通过
	public Status itemsNotPass(HttpServletRequest request, HttpServletResponse response, int check_id);

}
