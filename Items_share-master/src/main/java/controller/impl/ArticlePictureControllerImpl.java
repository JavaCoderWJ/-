package controller.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import controller.ArticlePictureController;
import service.ArticlePictureService;
import util.Status;

@Controller
@RequestMapping("/ArticlePicture")
public class ArticlePictureControllerImpl implements ArticlePictureController {

	private ArticlePictureService articlePictureService;
	private Status status;

	@Override
	@Autowired
	public void setArticlePictureService(ArticlePictureService articlePictureService) {
		this.articlePictureService = articlePictureService;

	}

	@Override
	@RequestMapping("/addPicture")
	@ResponseBody
	public Status addPicture(HttpServletRequest request, @RequestParam("picture") CommonsMultipartFile picture) {
		status = articlePictureService.addPicture(request, picture);
		return status;
	}

}
