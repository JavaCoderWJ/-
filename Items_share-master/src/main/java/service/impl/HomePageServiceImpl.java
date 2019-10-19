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
import dao.HomePageDao;
import dao.ItemsWacthDao;
import dao.PersonalCenterDao;
import pojo.AdminPojo;
import pojo.GoodsTypePojo;
import pojo.ItemsPojo;
import pojo.UserCollectPojo;
import pojo.UserPojo;
import service.HomePageService;
import util.FileSavePath;
import util.Status;

@Service
public class HomePageServiceImpl implements HomePageService {

	@Autowired
	private HomePageDao homePageDao;
	@Autowired
	private PersonalCenterDao personalCenterDao;
	@Autowired
     private ItemsWacthDao itemsWacthDao;
	@Autowired
	private AdminDao adminDao;
 	private UserPojo userPojo;
	private ItemsPojo itemsPojo;
	private AdminPojo adminPojo;
	private FileSavePath fileSavePath;
	private List<GoodsTypePojo> goodsTypePojoList;
	private Map<String, Object> map;
	private List<UserCollectPojo> listUserCollectPojo;
	private Status status;
	private List<ItemsPojo> itemsList;
	private List<String> list;
	int sql_action;
	public HomePageServiceImpl(){
		status = new Status();
		itemsList = new ArrayList<>();
		listUserCollectPojo = new ArrayList<>();
		map=new HashMap<>();
	}

	@Override
	public Status getNewItems() {
		itemsList = homePageDao.getNewItems();
		return status.check(itemsList);
	}

	@Override
	public Status getRecommend() {
		itemsList = homePageDao.getRecommend();
		return status.check(itemsList);
	}

	@Override
	public Status getUserPojo(HttpServletRequest request,HttpServletResponse response, String user_name, String user_password) {
		if (status.checkParam(user_name) == 0) {
			return new Status(status.PARAMETER_ERROR, "用户名为空！", null);
		}
		if (status.checkParam(user_password) == 0) {
			return new Status(status.PARAMETER_ERROR, "密码为空！", null);
		}
		if (user_name.equals("admin")) {
			adminPojo = adminDao.adminNameCheck(user_name);
			if (adminPojo != null && !adminPojo.equals("")) {
				adminPojo = adminDao.admingLogin(user_name, user_password);
				if (adminPojo != null && !adminPojo.equals("")) {
					String message = "欢迎您！管理员" + adminPojo.getName();
					return new Status(2, message, adminPojo);
				} else {
					return new Status(status.PARAMETER_ERROR, "密码错误！", null);
				}
			} else {
				return new Status(status.PARAMETER_ERROR, "您不是管理员！", null);
			}
		}
		userPojo = homePageDao.check_user_name(user_name);
		if (userPojo != null && !userPojo.equals("")) {
			//发送session
			String sessionID=request.getSession().getId();
			request.getSession().setAttribute("user", userPojo);
			userPojo=new UserPojo();
			userPojo = homePageDao.getUserPojo(user_name, user_password);
			if (userPojo != null && !userPojo.equals("")) {
				System.out.println(userPojo.getUser_id());
				listUserCollectPojo = personalCenterDao.getUserCollect(userPojo.getUser_id());
				System.out.println(listUserCollectPojo.size());
				map.put("user_id", userPojo.getUser_id());
				map.put("user_name", userPojo.getUser_name());
				map.put("user_password", userPojo.getUser_password());
				map.put("father_name", userPojo.getFather_name());
				map.put("mother_name", userPojo.getMother_name());
				map.put("user_head", userPojo.getUser_head());
				map.put("user_intro", userPojo.getUser_intro());
				map.put("fans", userPojo.getFans());
				map.put("won_praise_for", userPojo.getWon_praise_for());
				map.put("create_date", userPojo.getCreate_date());
				map.put("user_integal", userPojo.getUser_integal());
				map.put("user_expericence", userPojo.getUser_expericence());
				if (listUserCollectPojo != null) {
					map.put("focus_on", listUserCollectPojo.size());
					System.out.println("focus_on:" + map.get("focus_on"));
					return status.check(map);
				} else {
					map.put("focus_on", 0);
					return status.check(map);
				}	
			} else {
				return new Status(status.PARAMETER_ERROR, "密码错误！", null);
			}
		} else {
			return new Status(status.PARAMETER_ERROR, "用户未注册！", null);
		}
	}

	@Override
	public Status userRegist(HttpServletRequest request, String user_name, String user_password,
			String father_name,
			String mother_name) {
		System.out.println(user_name + "+");
		if (status.checkParam(user_name) == 0) {
			return new Status(status.PARAMETER_ERROR, "用户名为空！", null);
		}
		if(user_name=="admin") {
			return new Status(status.PARAMETER_ERROR,"抱歉！您不能注册为该用户名",null);
		}
		if (status.checkParam(user_password) == 0) {
			return new Status(status.PARAMETER_ERROR, "密码为空！", null);
		}
		if (status.checkParam(father_name) == 0) {
			return new Status(status.PARAMETER_ERROR, "父亲名为空！", null);
		}
		if (status.checkParam(mother_name) == 0) {
			return new Status(status.PARAMETER_ERROR, "母亲名为空！", null);
		}
		userPojo = homePageDao.check_user_name(user_name);
		if (userPojo != null && !userPojo.equals("")) {
			return new Status(status.PARAMETER_ERROR, "用户已存在！", null);
		} else {
			sql_action = homePageDao.userRegist(user_name, user_password, father_name, mother_name);
			return status.check(sql_action);
		}
	}

	@Override
	public Status findPassword(String user_name, String father_name, String mother_name) {
		if (status.checkParam(user_name) == 0) {
			return new Status(status.PARAMETER_ERROR, "用户为空！", null);
		}
		if (status.checkParam(father_name) == 0) {
			return new Status(status.PARAMETER_ERROR, "父亲名为空！", null);
		}
		if (status.checkParam(mother_name) == 0) {
			return new Status(status.PARAMETER_ERROR, "母亲名为空！", null);
		}
		userPojo = homePageDao.findPassword(user_name, father_name, mother_name);
		System.out.println(userPojo);
		if (userPojo != null && !userPojo.equals("")) {
			return status.check(userPojo);
		}
		return new Status(status.PARAMETER_ERROR, "密保问题回答错误！密保找回失败！", null);
	}

	@Override
	public Status updatePassword(String user_name, String user_password, String new_password) {

		if (status.checkParam(user_name) == 0) {
			return new Status(status.PARAMETER_ERROR, "用户名为空！", null);
		}
		if (status.checkParam(user_password) == 0) {
			return new Status(status.PARAMETER_ERROR, "原密码为空！", null);
		}
		if (status.checkParam(new_password) == 0) {
			return new Status(status.PARAMETER_ERROR, "新密码为空！", null);
		}
		userPojo = homePageDao.check_user_name(user_name);
		if (userPojo != null && !userPojo.equals("")) {
			userPojo=homePageDao.getUserPojo(user_name, user_password);
			if (userPojo != null && !userPojo.equals("")) {
				sql_action = homePageDao.updatePassword(user_name, user_password, new_password);
				return status.check(sql_action);
			} else {
				return new Status(status.PARAMETER_ERROR, "原密码输入错误！", null);
			}

		} else {
			return new Status(status.PARAMETER_ERROR, "用户不存在！", null);
		}
	}

	@Override
	public Status getShufflingItems() {
		itemsList = homePageDao.getShufflingItems();
		return status.check(itemsList);
	}

	@Override
	public Status getItemsById(int goods_id) {
		Map<String, Object> map = new HashMap<>();
		itemsPojo = homePageDao.getItemsById(goods_id);
		map.put("itemsPojo", itemsPojo);
		int comeBackUserful = itemsWacthDao.comeBackUserful(itemsPojo.getUserPojo().getUser_id());
		map.put("Alluserful", comeBackUserful);
		int comeBackOriginal = itemsWacthDao.comeBackOrigin(itemsPojo.getUserPojo().getUser_id());
		map.put("AllOriginal", comeBackOriginal);
		return status.check(map);
	}

	@Override
	public Status getItemsByUser(String goods_type) {
		if (status.checkParam(goods_type) == 0) {
			return new Status(status.PARAMETER_ERROR, "goods_type为空！", null);
		}
		String[] beauty = { "美妆", "护肤", "护理", "美护", "化妆" };
		for (int i = 0; i < beauty.length;) {
			if (goods_type.indexOf(beauty[i]) < 0) {
				i++;
			} else {
				goods_type = "美妆美护";
				itemsList = homePageDao.getItemsByUser(goods_type);
				return status.check(itemsList);
			}
		}
		String[] fashion = { "时尚", "前沿", "前卫", "流行" };
		for (int i = 0; i < fashion.length;) {
			if (goods_type.indexOf(fashion[i]) < 0) {
				i++;
			} else {
				goods_type = "时尚";
				itemsList = homePageDao.getItemsByUser(goods_type);
				return status.check(itemsList);
			}
		}
		String[] DIY = { "DIY", "diy", "创意", "手工" };
		for (int i = 0; i < DIY.length;) {
			if (goods_type.indexOf(DIY[i]) < 0) {
				i++;
			} else {
				goods_type = "DIY生活";
				itemsList = homePageDao.getItemsByUser(goods_type);
				return status.check(itemsList);
			}
		}
		String[] toWear = { "潮流", "服饰", "潮流穿搭" };
		for (int i = 0; i < toWear.length;) {
			if (goods_type.indexOf(toWear[i]) < 0) {
				i++;
			} else {
				goods_type = "潮流穿搭";
				itemsList = homePageDao.getItemsByUser(goods_type);
				return status.check(itemsList);
			}
		}
		String[] toy = { "玩具", "文具" };
		for (int i = 0; i < toy.length;) {
			if (goods_type.indexOf(toy[i]) < 0) {
				i++;
			} else {
				goods_type = "玩具文具";
				itemsList = homePageDao.getItemsByUser(goods_type);
				return status.check(itemsList);
			}
		}
		String[] EL = { "数码", "相机", "电子产品" };
		for (int i = 0; i < EL.length;) {
			if (goods_type.indexOf(EL[i]) < 0) {
				i++;
			} else {
				goods_type = "数码";
				itemsList = homePageDao.getItemsByUser(goods_type);
				return status.check(itemsList);
			}
		}
		return new Status(status.FAILED, "没有找到！", null);
	}

	@Override
	public Status getDifferentGoods_type() {
		list = homePageDao.getDifferentGoods_type();
		return status.check(list);
	}

	@Override
	public Status getItemsByGoods_type(String goods_type) {
		if (status.checkParam(goods_type) == 0) {
			return new Status(status.PARAMETER_ERROR, "goods_type为空！", null);
		}
		itemsList = homePageDao.getItemsByGoods_type(goods_type);
		return status.check(itemsList);
	}

	@Override
	public Status insertUserFeedback(HttpServletRequest request, String feedback) {
		String[] keyWords= {"习近平","操","日","妈","爸"};
		for(int i=0;i<keyWords.length-1;i++) {
			if(feedback.indexOf(keyWords[i])!=-1) {
				return new Status(status.PARAMETER_ERROR,"您输入的文章标题含有敏感词汇！请重新输入！",null);
			}
		}
		System.out.println("反馈意见："+feedback);
		if (status.checkParam(feedback) == 0) {
			return new Status(status.PARAMETER_ERROR, "反馈意见栏为空，提交失败！！", null);
		}
		//获取session
		String sessionID=request.getSession().getId();
		UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if (user!=null) {
			System.out.println(user.getUser_id()+":"+user.getUser_name());
			sql_action = homePageDao.insertUserFeedback(user.getUser_id(), feedback);
			return status.check(sql_action);
		} else {
			return new Status(status.PARAMETER_ERROR, "反馈失败！请先登录！", null);
		}
	}

	@Override
	public Status giveLike(int user_id, int goods_id) {
		sql_action = homePageDao.giveLike(user_id, goods_id);
		return status.check(sql_action);
	}

	@Override
	public Status getGoods_type_picture() {
		goodsTypePojoList = homePageDao.getGoods_type_picture();
		return status.check(goodsTypePojoList);
	}
}
