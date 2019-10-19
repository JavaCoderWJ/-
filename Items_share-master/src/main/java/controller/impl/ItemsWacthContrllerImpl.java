package controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import controller.ItemsWacthContrller;
import service.ItemsWacthService;
import util.Status;
import util.Status1;

@Controller
@RequestMapping("/ItemsPage")
public class ItemsWacthContrllerImpl implements ItemsWacthContrller {

	private ItemsWacthService itemsWacthService;
	private Status status;
	private Status1 status1;


	@Override
	@Autowired
	public void setItemsWacthService(ItemsWacthService itemsWacthService) {
		this.itemsWacthService = itemsWacthService;
	}

	@Override
	@RequestMapping("/getItemsPojo")
	@ResponseBody
	public Status getItemsPojo(HttpServletRequest request, int goods_id) {
		System.out.println("点击文章图片或者标题");
		status = itemsWacthService.getItemsPojo(request, goods_id);
		return status;
	}

	@Override
	@RequestMapping("/getItemsByAuthor")
	@ResponseBody
	public Status getItemsByAuthor(int user_id) {
		System.out.println("相关文章");
		status = itemsWacthService.getItemsByAuthor(user_id);
		return status;
	}

	@Override
	@RequestMapping("/addWonPraise")
	@ResponseBody
	public Status addWonPraise(HttpServletRequest request,HttpServletResponse response,int goods_id) {
		System.out.println("点击爱心点赞");
		status = itemsWacthService.addWonPraise(request,response,goods_id);
		return status;
	}

	@Override
	@RequestMapping("/addUserful")
	@ResponseBody
	public Status addUserful(HttpServletRequest request,HttpServletResponse response,int goods_id) {
		System.out.println("点击有用");
		status = itemsWacthService.addUserful(request,response,goods_id);
		return status;
	}

	@Override
	@RequestMapping("/addUnUserful")
	@ResponseBody
	public Status addUnUserful(HttpServletRequest request,HttpServletResponse response,int goods_id) {
		System.out.println("点击无用");
		status = itemsWacthService.addUnUserful(request,response,goods_id);
		return status;
	}

	@Override
	@RequestMapping("/shareItems")
	@ResponseBody
	public Status shareItems(HttpServletRequest request, int goods_id) {
		System.out.println("分享");
		status = itemsWacthService.shareItems(request, goods_id);
		return status;
	}

	@Override
	@RequestMapping("/returnUserComment")
	@ResponseBody
	public Status returnUserComment(int goods_id) {
		System.out.println("文章评论");
		status = itemsWacthService.returnUserComment(goods_id);
		return status;
	}

	@Override
	@RequestMapping("/giveComment")
	@ResponseBody
	public Status giveComment(HttpServletRequest request, int goods_id, String user_comment) {
		System.out.println("用户编辑评论");
		status = itemsWacthService.giveComment(request, goods_id, user_comment);
		return status;
	}

	@Override
	@RequestMapping("/giveCommentPraise")
	@ResponseBody
	public Status giveCommentPraise(HttpServletRequest request,HttpServletResponse response,int comment_id) {
		System.out.println("点赞评论");
		status = itemsWacthService.giveCommentPraise(request,response,comment_id);
		return status;
	}

}
