package service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import util.Status;

public interface WritePageService {
	Status writeItems_Share(HttpServletRequest request, String goods_type, String artical_name, String article);

	Status uploadPicture(HttpServletRequest request, CommonsMultipartFile picture);


}
