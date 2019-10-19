package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import service.ArticlePictureService;
import util.Status;

public interface ArticlePictureController {

	Status addPicture(HttpServletRequest request, @RequestParam("picture") CommonsMultipartFile picture);

	void setArticlePictureService(ArticlePictureService articlePictureService);
}
