package service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.HomePageDao;
import dao.PersonalCenterDao;
import pojo.ItemsPojo;
import pojo.SystemMessagePojo;
import pojo.UserCollectPojo;
import pojo.UserCommentPojo;
import pojo.UserPojo;
import service.PersonalCenterService;
import util.FileSavePath;
import util.FileUploadBySSM;
import util.Status;

@Service
public class PersonCenterServiceImpl implements PersonalCenterService {

	@Autowired
	private PersonalCenterDao personalCenterDao;
	@Autowired
	private HomePageDao homePageDao;
	private UserPojo userPojo;
	private UserCommentPojo userCommentPojo;
	private ItemsPojo itemsPojo;
	private List<UserCommentPojo> listUserCommentPojo;
	private List<List<UserCommentPojo>> listInlistUserCommentPojo;
	private List<UserPojo> listUserPojo;
	private List<ItemsPojo> listItemsPojo;
	private List<SystemMessagePojo> listSystemMessagePojo;
	private List<UserCollectPojo> listUserCollectPojo;
	private Map<String, String> map;
	private List<Map<String, String>> list2;
	private int sqlAction;
	private Status status;
	private FileSavePath fileSavePath;

	public PersonCenterServiceImpl() {
		status = new Status();
		map = new HashMap<>();
		list2 = new ArrayList<>();
		listUserPojo = new ArrayList<>();
		listItemsPojo = new ArrayList<>();
		listUserCommentPojo = new ArrayList<>();
		listInlistUserCommentPojo = new ArrayList<>();
		listSystemMessagePojo = new ArrayList<>();
		listUserCollectPojo = new ArrayList<>();
	}


	@Override
	public Status getPersonalDetails(HttpServletRequest request) {
		//获取session
		String sessionID=request.getSession().getId();
		UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		System.out.println("个人信息session："+user);
		if (user != null) {
		//	System.out.println(user.getUser_id()+":"+user.getUser_name());
			int user_id = user.getUser_id();
			System.out.println("登陆user_id"+user_id);
			listUserCollectPojo = personalCenterDao.getUserCollect(user_id);
			map.put("focus_numbers", String.valueOf(listUserCollectPojo.size()));
			userPojo = personalCenterDao.getPersonalDetails(user_id);
			map.put("user_name", userPojo.getUser_name());
			map.put("user_head", userPojo.getUser_head());
			map.put("user_intro", userPojo.getUser_intro());
			map.put("fans", String.valueOf(userPojo.getFans()));
			map.put("won_praise_for", String.valueOf(userPojo.getWon_praise_for()));
			map.put("create_date", userPojo.getCreate_date());
			map.put("user_integal", String.valueOf(userPojo.getUser_integal()));
			map.put("user_expericence", String.valueOf(userPojo.getUser_expericence()));
			System.out.println("获取个人信息:"+map);
			return status.check(map);
		} else {
			return new Status(status.PARAMETER_ERROR, "未登录！请先登录！", null);
		}
	}

	@Override
	public Status getOriginal(HttpServletRequest request) {
		//获取session
		String sessionID=request.getSession().getId();
		UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if (user != null) {
			System.out.println(user.getUser_name());
			int user_id = user.getUser_id();
			System.out.println("getOriginal"+user_id);
			listItemsPojo = personalCenterDao.getOriginal(user_id);
			return status.check(listItemsPojo);
		} else {
			return new Status(status.PARAMETER_ERROR, "未登录！请先登录！", null);
		}
	}

	@Override
	public Status getNotOriginal(HttpServletRequest request) {
		//获取session
		String sessionID=request.getSession().getId();
		UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if (user != null) {
			int user_id = user.getUser_id();
			listItemsPojo = personalCenterDao.getNotOriginal(user_id);
			return status.check(listItemsPojo);
		} else {
			return new Status(status.PARAMETER_ERROR, "未登录！请先登录！", null);
		}
	}

	@Override
	public Status getUserPraise(HttpServletRequest request) {
		//获取session
				String sessionID=request.getSession().getId();
				UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if (user != null) {
			int user_id = user.getUser_id();
			System.out.println("getUserPraise"+user_id);
			listItemsPojo = personalCenterDao.getUserPraise(user_id);
			System.out.println(listItemsPojo);
			return status.check(listItemsPojo);
		} else {
			return new Status(status.PARAMETER_ERROR, "未登录！请先登录！", null);
		}
	}

	@Override
	public Status updatePersonalDetails(HttpServletRequest request, String user_name, String user_intro) {
		//获取session
				String sessionID=request.getSession().getId();
				UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if (user != null) {
			int user_id = user.getUser_id();
			System.out.println("更改用户名为："+user_name);
			userPojo = homePageDao.check_user_name(user_name);
			System.out.println(userPojo);
			if (userPojo != null && !userPojo.equals("")) {
				return new Status(status.PARAMETER_ERROR, "用户已存在！", null);
			} else {
				sqlAction = personalCenterDao.updatePersonalDetails(user_id, user_name, user_intro);
				return status.check(sqlAction);
			}
		} else {
			return new Status(status.PARAMETER_ERROR, "未登录！请先登录！", null);
		}
	}

	@Override
	public Status updatePersonalHead(HttpServletRequest request, CommonsMultipartFile user_head) {
		//获取session
				String sessionID=request.getSession().getId();
				UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		System.out.println(user);
		// 上传位置,设定文件保存的目录
		FileUploadBySSM fileuploadBySSM = new FileUploadBySSM();
		String path = fileSavePath.imgPath + "user\\";
		String newFileName = fileuploadBySSM.fileDealWith(user_head, request, path);
		if (newFileName != null) {
			if (user != null) {
				int user_id = user.getUser_id();
				String user_head_url = fileSavePath.img_sql_savePath + "user/" + newFileName;
				System.out.println("user_head_url:"+user_head_url);
				sqlAction = personalCenterDao.updatePersonalHead(user_id,user_head_url);
				userPojo=personalCenterDao.getUserPojo(user_id);
				return status.check(userPojo);
			} else {
				return new Status(status.PARAMETER_ERROR, "未登录！请先登录！", null);
			}
		} else {
			return new Status(status.FAILED, "头像上传失败！", null);
		}
	}

	@Override
	public Status updatePassword(HttpServletRequest request, String user_password, String new_password,
			String sure_new_password) {
		//获取session
				String sessionID=request.getSession().getId();
				UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if (user != null) {
			int user_id = user.getUser_id();
			if (new_password != sure_new_password && !new_password.equals(sure_new_password)) {
				return new Status(status.PARAMETER_ERROR, "新密码确认错误", null);
			} else {
				userPojo = personalCenterDao.selectPassword(user_id, user_password);
				if (userPojo != null) {
					sqlAction = personalCenterDao.updatePassword(user_id, new_password);
					return status.check(sqlAction);
				} else {
					return new Status(status.PARAMETER_ERROR, "原密码输入错误", null);
				}
			}
		} else {
			return new Status(status.PARAMETER_ERROR, "未登录！请先登录！", null);
		}
	}

	@Override
	public Status getUserComent(HttpServletRequest request) {
		//获取session
				String sessionID=request.getSession().getId();
				UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if (user != null) {
			int user_id = user.getUser_id();
			System.out.println("个人中心获取我的评论"+user_id);
			listUserCommentPojo = personalCenterDao.getUserComent(user_id);
			
			if (listUserCommentPojo != null&&!listInlistUserCommentPojo.equals("")) {
				System.out.println("listUserCommentPojo:" + listUserCommentPojo);
				for (int i = 0; i < listUserCommentPojo.size(); i++) {
					userPojo = personalCenterDao.getUserPojo(user_id);
					System.out.println("userPojo:" + userPojo);
				    map.put("user_head", userPojo.getUser_head());
					map.put("user_id", String.valueOf(userPojo.getUser_id()));
					userCommentPojo = listUserCommentPojo.get(i);
					System.out.println("userCommentPojo:" + userCommentPojo);
					map.put("user_comment", userCommentPojo.getUser_comment());
					map.put("comment_date", userCommentPojo.getComment_date());
					itemsPojo = personalCenterDao.getItemsBygoodsID(userCommentPojo.getGoods_id());
					System.out.println("itemsPojo:" + itemsPojo);
					map.put("picture_url", itemsPojo.getPicture_url());
					map.put("artical_name", itemsPojo.getArtical_name());
					System.out.println("list1:" + map);
					list2.add(map);
					map = new HashMap<>();
					System.out.println("new list1:" + map);
				}
				return status.check(list2);
			} else {
				return new Status(status.FAILED, "您未评论过任何好物分享！", null);
			}
		} else {
			return new Status(status.PARAMETER_ERROR, "未登录！请先登录！", null);
		}
	}

	@Override
	public Status getOtherUserComment(HttpServletRequest request) {
		//获取session
				String sessionID=request.getSession().getId();
				UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if (user != null) {
			int user_id = user.getUser_id();
			System.out.println(user_id);
			listItemsPojo = personalCenterDao.getUserItemsGoods_id(user_id);
			for (int i = 0; i < listItemsPojo.size(); i++) {
				System.out.println("listItemsPojo.get(i).getGoods_id():" + listItemsPojo.get(i).getGoods_id());
				
				listUserCommentPojo = personalCenterDao.getOtherUserComment(listItemsPojo.get(i).getGoods_id());
				for (int j = 0; j < listUserCommentPojo.size(); j++) {
					System.out.println(listUserCommentPojo.get(j).getGoods_id());
					itemsPojo = personalCenterDao.getItemsBygoodsID(listUserCommentPojo.get(j).getGoods_id());
					map.put("goods_id", String.valueOf(itemsPojo.getGoods_id()));
					map.put("artical_name", itemsPojo.getArtical_name());
					map.put("picture_url", itemsPojo.getPicture_url());
					userPojo = personalCenterDao.getUserPojo(listUserCommentPojo.get(j).getOther_user_id());
					map.put("user_name", userPojo.getUser_name());
					map.put("user_head", userPojo.getUser_head());
					userCommentPojo = personalCenterDao
							.getUserCommentByCommentID(listUserCommentPojo.get(j).getComment_id());
					map.put("user_comment", userCommentPojo.getUser_comment());
					map.put("comment_date", userCommentPojo.getComment_date());
					System.out.println("list1:" + map);
					list2.add(map);
					map = new HashMap<>();
					System.out.println("new list1:" + map);
				}
			}
			return status.check(list2);
		} else {
			return new Status(status.PARAMETER_ERROR, "未登录！请先登录！", null);
		}
	}

	@Override
	public Status getSystemMessage(HttpServletRequest request) {
		//获取session
				String sessionID=request.getSession().getId();
				UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if (user != null) {
			int user_id = user.getUser_id();
			listSystemMessagePojo = personalCenterDao.getSystemMessage(user_id);
			return status.check(listSystemMessagePojo);
		} else {
			return new Status(status.PARAMETER_ERROR, "未登录！请先登录！", null);
		}
	}

	@Override
	public Status getUserCollect(HttpServletRequest request) {
		//获取session
				String sessionID=request.getSession().getId();
				UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if (user != null) {
			int user_id = user.getUser_id();
			listUserCollectPojo = personalCenterDao.getUserCollect(user_id);
			System.out.println("+++++++用户收藏"+listUserCollectPojo+"+++++++用户收藏");
			return status.check(listUserCollectPojo);
		} else {
			return new Status(status.PARAMETER_ERROR, "未登录！请先登录！", null);
		}
	}
	
	@Override
	public Status getUserFocusOn(HttpServletRequest request) {
		//获取session
		String sessionID=request.getSession().getId();
		UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if(user!=null) {
			int user_id=user.getUser_id();
			listUserCollectPojo = personalCenterDao.getUserCollect(user_id);
			int size = listUserCollectPojo.size();
			return new Status(status.SUCCESS, "获取当前用户关注数量成功", size);
		}else {
			return new Status(status.PARAMETER_ERROR, "未登录！请先登录！", null);
		}
        
	}

}
