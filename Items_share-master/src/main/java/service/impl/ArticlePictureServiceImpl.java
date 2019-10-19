package service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.ArticlePictureDao;
import pojo.ArticlePicturePojo;
import service.ArticlePictureService;
import util.FileSavePath;
import util.FileUploadBySSM;
import util.Status;

@Service
public class ArticlePictureServiceImpl implements ArticlePictureService {

	@Autowired
	private ArticlePictureDao articlePictureDao;
	int sqlAction = 0;
	private FileSavePath fileSavePath;
	private Status status;
	private ArticlePicturePojo articlePicturePojo;
	public ArticlePictureServiceImpl() {
		status = new Status();
	}
	@Override
	public Status addPicture(HttpServletRequest request, CommonsMultipartFile picture) {
		FileUploadBySSM fileuploadBySSM = new FileUploadBySSM();
		// 上传位置,设定文件保存的目录
		String path = fileSavePath.imgPath + "articlePictureUrl\\";
		String newFileName = fileuploadBySSM.fileDealWith(picture, request, path);
		if (newFileName != null) {
			String picture_url = fileSavePath.img_sql_savePath + "articlePictureUrl/" + newFileName;
			sqlAction = articlePictureDao.addPicture(picture_url);
			if (sqlAction > 0) {
				articlePicturePojo = articlePictureDao.getArticlePicturePojo(picture_url);
				return new Status(status.SUCCESS, "插入文章图片上传成功！待审核", articlePicturePojo);
			} else {
				return new Status(status.FAILED, "插入文章图片失败！未知原因！您可以反馈给网站管理员！", null);
			}
		} else {
			return new Status(status.FAILED, "插入文章图片失败！", null);
		}
	}

}
