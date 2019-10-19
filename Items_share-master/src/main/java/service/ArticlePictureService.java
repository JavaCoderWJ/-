package service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import util.Status;

public interface ArticlePictureService {

	Status addPicture(HttpServletRequest request, CommonsMultipartFile picture);
}
