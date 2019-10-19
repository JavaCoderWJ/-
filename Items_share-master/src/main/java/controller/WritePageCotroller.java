package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import service.WritePageService;
import util.Status;

public interface WritePageCotroller {

	public void setWritePageServicee(WritePageService WritePageService);

	// 用户发表文章
		Status writeItems_Share(HttpServletRequest request, String goods_type, String artical_name, String article);

		// 用户上传文章头图
		Status uploadPicture(HttpServletRequest request,HttpServletResponse response, @RequestParam("picture") CommonsMultipartFile picture);
}
