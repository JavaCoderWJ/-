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

import dao.ItemsWacthDao;
import dao.PersonalCenterDao;
import pojo.ItemsPojo;
import pojo.UserCommentPojo;
import pojo.UserPojo;
import service.ItemsWacthService;
import util.Status;
import util.Status1;

@Service
public class ItemsWacthServiceImpl implements ItemsWacthService {

	@Autowired
	private ItemsWacthDao itemsWacthDao;
	@Autowired
	private PersonalCenterDao personalCenterDao;
	private Status status;
	private Status1 status1;
	private ItemsPojo itemsPojo;
	private UserPojo userPojo;
	private UserCommentPojo userCommentPojo;
	private List<UserCommentPojo> listUserCommentPojo;
	private List<ItemsPojo> listItemsPojo;
	private Map<?, ?> map;
	private int sqlAction;

	public ItemsWacthServiceImpl() {
		status = new Status();
		status1 = new Status1();
		listItemsPojo = new ArrayList<>();
		listUserCommentPojo = new ArrayList<>();
	}

	@Override
	public Status getItemsPojo(HttpServletRequest request, int goods_id) {
		UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if (user != null) {
			// 添加用户历史记录
			sqlAction = itemsWacthDao.addUser_history(user.getUser_id(), goods_id);
		}
		itemsPojo = itemsWacthDao.getItemsPojo(goods_id);
		return status.check(itemsPojo);
	}

	@Override
	public Status getItemsByAuthor(int user_id) {
		listItemsPojo = itemsWacthDao.getItemsByAuthor(user_id);
		return status.check(listItemsPojo);
	}

	@Override
	public Status addWonPraise(HttpServletRequest request,HttpServletResponse response,int goods_id) {
		String cookieTempName=String.valueOf(goods_id)+"addWonPraise";
		Cookie cookies[] = request.getCookies();
		int temp = 0;
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				System.out.println("cookie name is:"+name);
				if (name.equals(cookieTempName)) {
					temp = 1;
				}
			}	
		}
		if (temp == 0) {
			Cookie cookie = new Cookie(cookieTempName, "已赞");
			response.addCookie(cookie);
			sqlAction = itemsWacthDao.addWonPraise(goods_id);
			if (sqlAction > 0) {
				itemsPojo = itemsWacthDao.returnItems(goods_id);
				if (itemsPojo != null) {
					return new Status(1, "点赞成功！", itemsPojo.getWon_praise_for());
				} else {
					return new Status(0, "点赞失败！", null);
				}
			} else {
				return new Status(0, "点赞失败！", null);
			}
		} else {
			itemsPojo = itemsWacthDao.returnItems(goods_id);
			return new Status(-100, "点赞失败！当前用户已经点赞！", itemsPojo.getWon_praise_for());
		}
	}

	@Override
	public Status addUserful(HttpServletRequest request,HttpServletResponse response,int goods_id) {
		String cookieTempName=String.valueOf(goods_id)+"adduserful";
		Cookie cookies[] = request.getCookies();
		int temp = 0;
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				System.out.println("cookie name is:"+name);
				if (name.equals(cookieTempName)) {
					temp = 1;
				}
			}	
		}
		UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		
		if(temp==0) {
			Cookie cookie = new Cookie(cookieTempName, "已点击");
			response.addCookie(cookie);
			sqlAction = itemsWacthDao.addUserful(goods_id);
			sqlAction=itemsWacthDao.collectGoods(user.getUser_id(),goods_id);
			if (sqlAction > 0) {
				itemsPojo = itemsWacthDao.returnItems(goods_id);
				
				if (itemsPojo != null) {
					return new Status(1, "点击成功！", itemsPojo.getUserful());
				} else {
					return new Status(0, "点击失败！", null);
				}
			} else {
				return new Status(0, "点击失败！", null);
			}	
		}else {
			itemsPojo = itemsWacthDao.returnItems(goods_id);
			return new Status(-100, "点击失败！当前用户已经点击！", itemsPojo.getUserful());
		}
	}

	@Override
	public Status addUnUserful(HttpServletRequest request,HttpServletResponse response,int goods_id) {
		String cookieTempName=String.valueOf(goods_id)+"addUnuserful";
		Cookie cookies[] = request.getCookies();
		int temp = 0;
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				System.out.println("cookie name is:"+name);
				if (name.equals(cookieTempName)) {
					temp = 1;
				}
			}	
		}
		if(temp==0) {
			Cookie cookie = new Cookie(cookieTempName, "已点击");
			response.addCookie(cookie);
			sqlAction = itemsWacthDao.addUnUserful(goods_id);
			if (sqlAction > 0) {
				itemsPojo = itemsWacthDao.returnItems(goods_id);
				if (itemsPojo != null) {
					return new Status(1, "点击成功！", itemsPojo.getUnuserful());
				} else {
					return new Status(0, "点击失败！", null);
				}
			} else {
				return new Status(0, "点击失败！", null);
			}
	
		}else {
			itemsPojo = itemsWacthDao.returnItems(goods_id);
			return new Status(-100, "点击失败！当前用户已经点击！", itemsPojo.getUserful());
		}
    }

	@Override
	public Status shareItems(HttpServletRequest request, int goods_id) {
		itemsPojo = itemsWacthDao.returnItems(goods_id);
		if (itemsPojo == null) {
			return new Status(status.FAILED, "您所分享的文章不存在！或者已被删除！", null);
		} else {
			//获取session
			String sessionID=request.getSession().getId();
			UserPojo user = (UserPojo) request.getSession().getAttribute("user");
			if (user == null) {
				return new Status(0, "当前用户未登录!无法分享此文章！", null);
			} else {
				int user_id = user.getUser_id();
				sqlAction = itemsWacthDao.shareItems(user_id, itemsPojo.getArtical_name(),
						itemsPojo.getArticle(), itemsPojo.getPicture_url(), itemsPojo.getGoods_type(),
						itemsPojo.getGoods_buy_url(), 0, itemsPojo.getUserful(), itemsPojo.getUnuserful(),
						itemsPojo.getWon_praise_for(), itemsPojo.getIs_reprint());
				if (sqlAction > 0) {
					return new Status(1, "分享成功！", null);
				} else {
					return new Status(0, "分享失败！", null);
				}
			}

		}
	}

	@Override
	public Status returnUserComment(int goods_id) {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> listMap = new ArrayList<>();
		listUserCommentPojo = itemsWacthDao.returnUserComment(goods_id);
		for (int i = 0; i < listUserCommentPojo.size(); i++) {
			userPojo = personalCenterDao.getPersonalDetails(listUserCommentPojo.get(i).getOther_user_id());
			map.put("userPojo", userPojo);
			map.put("UserCommentPojo", listUserCommentPojo.get(i));
			listMap.add(map);
			map = new HashMap<>();
		}
		return new Status(1, "获取用户评论成功！", listMap);
	}

	@Override
	public Status giveComment(HttpServletRequest request, int goods_id, String user_comment) {
		if (status.checkParam(user_comment) == 0) {
			return new Status(status.PARAMETER_ERROR, "user_comment为空！", null);
		}
		//获取session
				String sessionID=request.getSession().getId();
				UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if (user == null) {
			return new Status(0, "当前用户未登录!无法评论！", null);
		} else {
			int user_id = user.getUser_id();
			sqlAction = itemsWacthDao.giveComment(user_id, goods_id, user_comment);
			return status.check(sqlAction);
		}
	}

	@Override
	public Status giveCommentPraise(HttpServletRequest request,HttpServletResponse response,int comment_id) {
		String cookieTempName=String.valueOf(comment_id)+"giveCommentPraise";
		Cookie cookies[] = request.getCookies();
		int temp = 0;
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				System.out.println("cookie name is:"+name);
				if (name.equals(cookieTempName)) {
					temp = 1;
				}
			}	
		}
		if(temp==0) {
			Cookie cookie = new Cookie(cookieTempName, "已点击");
			response.addCookie(cookie);
			sqlAction = itemsWacthDao.giveCommentPraise(comment_id);
			if (sqlAction > 0) {
				userCommentPojo = itemsWacthDao.returnUserCommentByComment_id(comment_id);
				if (userCommentPojo != null) {
					return new Status(1, "点击成功！", userCommentPojo.getWon_praise_for());
				} else {
					return new Status(0, "点击失败！", null);
				}
			} else {
				return new Status(0, "点击失败！", null);
			}	
		}else {
			return new Status(-100, "点击失败！当前用户已经点击！", null);
		}
		
	}

}
