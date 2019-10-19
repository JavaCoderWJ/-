package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ItemsWacthService;
import util.Status;
import util.Status1;

public interface ItemsWacthContrller {

	public void setItemsWacthService(ItemsWacthService itemsWacthService);

	// 点击文章图片或者标题
	Status getItemsPojo(HttpServletRequest request, int goods_id);

	// 相关文章
	Status getItemsByAuthor(int user_id);

	// 点击爱心点赞
	Status addWonPraise(HttpServletRequest request,HttpServletResponse response,int goods_id);

	// 点击有用
	Status addUserful(HttpServletRequest request,HttpServletResponse response,int goods_id);

	// 点击无用
	Status addUnUserful(HttpServletRequest request,HttpServletResponse response,int goods_id);

	// 分享
	Status shareItems(HttpServletRequest request, int goods_id);

	// 文章评论
	Status returnUserComment(int goods_id);

	// 用户编辑评论
	Status giveComment(HttpServletRequest request, int goods_id, String user_comment);

	// 点赞评论
	Status giveCommentPraise(HttpServletRequest request,HttpServletResponse response,int comment_id);
}

