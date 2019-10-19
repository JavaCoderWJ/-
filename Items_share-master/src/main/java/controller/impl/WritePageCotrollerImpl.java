package controller.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import controller.WritePageCotroller;
import service.WritePageService;
import util.Status;

@Controller
@RequestMapping("/WritePage")
public class WritePageCotrollerImpl implements WritePageCotroller {

	private WritePageService WritePageService;
	private Status status;

	@Override
	@Autowired
	public void setWritePageServicee(WritePageService WritePageService) {
		this.WritePageService = WritePageService;

	}

	@Override
	@RequestMapping("/writeItems_Share")
	@ResponseBody
	public Status writeItems_Share(HttpServletRequest request, String goods_type, String artical_name, String article) {
		status = WritePageService.writeItems_Share(request, goods_type, artical_name, article);
		return status;
	}

	@Override
	@RequestMapping("/uploadPicture")
	@ResponseBody
	public Status uploadPicture(HttpServletRequest request, HttpServletResponse response,@RequestParam("picture") CommonsMultipartFile picture) {
		status = WritePageService.uploadPicture(request, picture);
		response.setContentType("image/jpeg;charset=UTF-8");
		return status;
	}

}
