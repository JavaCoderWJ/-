package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Status;
import util.Status1;

public interface ItemsWacthService {

	// 返回相应文章信息
	Status getItemsPojo(HttpServletRequest request, int goods_id);

	// 返回当前作者发表的文章
	Status getItemsByAuthor(int user_id);

	// 用户点击爱心并返回点击后的数量
	Status addWonPraise(HttpServletRequest request,HttpServletResponse response,int goods_id);

	// 用户点击有用并返回点击后的数量
	Status addUserful(HttpServletRequest request,HttpServletResponse response,int goods_id);

	// 用户点击无用并返回点击后的数量
	Status addUnUserful(HttpServletRequest request,HttpServletResponse response,int goods_id);

	// 转载其他用户发表法人文章
	Status shareItems(HttpServletRequest request, int goods_id);

	// 返回当前文章的所有评论
	Status returnUserComment(int goods_id);

	// 插入用户评论，返回插入状态
	Status giveComment(HttpServletRequest request, int goods_id, String user_comment);

	// 根据评论id，给评论点赞，返回点赞情况
	Status giveCommentPraise(HttpServletRequest request,HttpServletResponse response,int comment_id);
}
