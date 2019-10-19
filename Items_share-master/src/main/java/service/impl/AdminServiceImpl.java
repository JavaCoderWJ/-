package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AdminDao;
import dao.PersonalCenterDao;
import pojo.AdminPojo;
import pojo.FeedbackPojo;
import pojo.ItemsCheckPojo;
import pojo.ItemsPojo;
import pojo.UserPojo;
import service.AdminService;
import util.Status;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	@Autowired
	private PersonalCenterDao personalCenterDao;
	private AdminPojo adminPojo;
	private FeedbackPojo feedbackPojo;
	private ItemsCheckPojo itemsCheckPojo;
	private UserPojo userPojo;
	private ItemsPojo itemsPojo;
	private Status status;
	private List<ItemsPojo> listItemsPojo;
	private List<FeedbackPojo> listFeeadback;
	private List<ItemsCheckPojo> listItemsCheck;

	public AdminServiceImpl() {
		status = new Status();
	}

	@Override
	public Status adminLogin(HttpServletRequest request, String adminName, String adminPassword) {
		if (status.checkParam(adminName) == 0) {
			return new Status(status.PARAMETER_ERROR, "用户名为空！", null);
		}
		if (status.checkParam(adminPassword) == 0) {
			return new Status(status.PARAMETER_ERROR, "密码为空！", null);
		}
		adminPojo = adminDao.adminNameCheck(adminName);
		if (adminPojo != null && !adminPojo.equals("")) {
			adminPojo = adminDao.admingLogin(adminName, adminPassword);
			if (adminPojo != null && !adminPojo.equals("")) {
				return status.check(adminPojo);
			} else {
				return new Status(status.PARAMETER_ERROR, "密码错误！", null);
			}
		} else {
			return new Status(status.PARAMETER_ERROR, "您不是管理员！", null);
		}
	}

	@Override
	public Status getAllItems() {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> listMap = new ArrayList<>();
		listItemsCheck = adminDao.getAllItemsCheckPojo();
		for (int j = 0; j < listItemsCheck.size(); j++) {
			itemsCheckPojo = listItemsCheck.get(j);
			userPojo = personalCenterDao.getUserPojo(itemsCheckPojo.getUser_id());
			map.put("user_name", userPojo.getUser_name());
			map.put("user_head", userPojo.getUser_head());
			map.put("artical_name", itemsCheckPojo.getArtical_name());
			map.put("article", itemsCheckPojo.getArticle());
			map.put("picture_url", itemsCheckPojo.getPicture_url());
			map.put("goods_type", itemsCheckPojo.getGoods_type());
			map.put("is_original", 1);
			map.put("is_reprint", itemsCheckPojo.getIs_reprint());
			map.put("publis_date", itemsCheckPojo.getPublis_date());
			map.put("check_state", 0);
			map.put("check_state_intro", "未通过");
			map.put("check_id",itemsCheckPojo.getCheck_id());
			listMap.add(map);
			map = new HashMap<>();
		}
		listItemsPojo = adminDao.getAllItems();
		for (int i = 0; i < listItemsPojo.size(); i++) {
			itemsPojo=listItemsPojo.get(i);
			userPojo = itemsPojo.getUserPojo();
			map.put("user_name", userPojo.getUser_name());
			map.put("user_head", userPojo.getUser_head());
			map.put("artical_name", itemsPojo.getArtical_name());
			map.put("article", itemsPojo.getArticle());
			map.put("picture_url", itemsPojo.getPicture_url());
			map.put("goods_type", itemsPojo.getGoods_type());
			map.put("is_original", itemsPojo.getIs_original());
			map.put("is_reprint", itemsPojo.getIs_reprint());
			map.put("publis_date", itemsPojo.getPublis_date());
			map.put("check_state", 1);
			map.put("check_state_intro", "通过");
			listMap.add(map);
			map = new HashMap<>();
		}
		return status.check(listMap);
	}

	@Override
	public Status getAllFeedback() {
		listFeeadback = adminDao.getAllFeedback();
		return status.check(listFeeadback);
	}
	
	@Override
	public Status getFeedbackById(int feedback_id) {
		feedbackPojo = adminDao.getFeedbackById(feedback_id);
		return status.check(feedbackPojo);
	}

	@Override
	public Status getAllItemsCheckPojo() {
		listItemsCheck = adminDao.getAllItemsCheckPojo();
		return status.check(listItemsCheck);
	}

	@Override
	public Status insertIntoItems(HttpServletRequest request, HttpServletResponse response, int check_id) {

		String cookieTempName = String.valueOf(check_id) + "insertIntoItems";
		Cookie cookies[] = request.getCookies();
		int temp = 0;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				System.out.println("cookie name is:" + name);
				if (name.equals(cookieTempName)) {
					temp = 1;
				}
			}
		}
		if (temp == 0) {
			Cookie cookie = new Cookie(cookieTempName, "已点击");
			response.addCookie(cookie);
			itemsCheckPojo = adminDao.getItemsCheckPojoByCheckID(check_id);
			if(itemsCheckPojo!=null) {
				// 上传文章总表
				int sqlAction1 = adminDao.insertIntoItems(itemsCheckPojo.getUser_id(), itemsCheckPojo.getArtical_name(),
						itemsCheckPojo.getArticle(), itemsCheckPojo.getPicture_url(), itemsCheckPojo.getGoods_type(),
						itemsCheckPojo.getIs_reprint(), 1);
				// 改变审核状态
				int sqlAction2 = adminDao.changeItemsCheckStateByID(check_id);
				String message = "您的文章《" + itemsCheckPojo.getArtical_name() + "》已经审核成功！";
				// 发送系统消息给用户
				int sqlAction3 = adminDao.sendMessageToUser(message, itemsCheckPojo.getUser_id());
				return new Status(1, "审核成功，系统已自动将审核情况发送给用户！", null);
			}else {
				return new Status(-100,"您查找的文章不存在",null);
			}
			
		} else {
			return new Status(-100, "已点击，请勿重复点击！", null);
		}
	}

	@Override
	public Status itemsNotPass(HttpServletRequest request, HttpServletResponse response, int check_id) {
		String cookieTempName = String.valueOf(check_id) + "itemsNotPass";
		Cookie cookies[] = request.getCookies();
		int temp = 0;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				System.out.println("cookie name is:" + name);
				if (name.equals(cookieTempName)) {
					temp = 1;
				}
			}
		}
		if (temp == 0) {
			Cookie cookie = new Cookie(cookieTempName, "已点击");
			response.addCookie(cookie);
			itemsCheckPojo = adminDao.getItemsCheckPojoByCheckID(check_id);
			if(itemsCheckPojo!=null) {
				String message = "抱歉！您的文章《" + itemsCheckPojo.getArtical_name() + "》审核失败！如有反馈，请及时反馈给网站管理员！";
				int sqlAction1=adminDao.deleteItemsByID(check_id);
				int sqlAction = adminDao.sendMessageToUser(message, itemsCheckPojo.getUser_id());
				return new Status(1, "审核成功，系统已自动将审核情况发送给用户！", null);
			}else {
				return new Status(-100,"您查找的文章不存在",null);
			}
			} else {
			return new Status(-100, "已点击，请勿重复点击！", null);
		}
	}

}
