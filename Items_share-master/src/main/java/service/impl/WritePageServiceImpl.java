package service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.WritePageDao;
import pojo.ItemsCheckPojo;
import pojo.UserPojo;
import service.WritePageService;
import util.FileSavePath;
import util.FileUploadBySSM;
import util.Status;

@Service
public class WritePageServiceImpl implements WritePageService {

	@Autowired
	private WritePageDao writePageDao;
	int sqlAction = 0;
	private FileSavePath fileSavePath;
	private Status status;
	private ItemsCheckPojo itemsCheckPojo;

	public WritePageServiceImpl() {
		status = new Status();
	}

	@Override
	public Status writeItems_Share(HttpServletRequest request, String goods_type, String artical_name, String article) {
		if(status.checkParam(goods_type)==0) {
			return new Status(status.PARAMETER_ERROR,"goods_type为空",null);
		}
		System.out.println("goods_type:"+goods_type);
		if(status.checkParam(artical_name)==0) {
			return new Status(status.PARAMETER_ERROR,"artical_name为空",null);
		}
		System.out.println("artical_name:"+artical_name);
		if(status.checkParam(article)==0) {
			return new Status(status.PARAMETER_ERROR,"article为空",null);
		}
		String[] keyWords= {"习近平"};
		for(int i=0;i<keyWords.length-1;i++) {
			if(artical_name.indexOf(keyWords[i])!=-1) {
				return new Status(status.PARAMETER_ERROR,"您输入的文章标题含有敏感词汇！请重新输入！",null);
			}
			if(article.indexOf(keyWords[i])!=-1){
				return new Status(status.PARAMETER_ERROR,"您输入的文章内容含有敏感词汇！请重新输入！",null);
			}
		}
		System.out.println("article:"+article);
		UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		ItemsCheckPojo itemsCheck = (ItemsCheckPojo) request.getSession().getAttribute("write");
		if (user != null) {
			if (itemsCheck != null) {
				int check_id = itemsCheck.getCheck_id();
				System.out.println(check_id + ":" + goods_type + ":" + artical_name + ":" + article );
				sqlAction = writePageDao.writeItems_Share(check_id, artical_name, article, goods_type);
				if (sqlAction > 0) {
					itemsCheckPojo = writePageDao.getItemsCheckPojo(
							"http://203.195.151.80:8080/Items_share/img/items/3ec1d25a-88ae-43ed-aaf7-323e1d954264W020170619513861455017.jpg");
					return new Status(status.SUCCESS, "上传成功！待审核！", itemsCheckPojo);
				} else {
					return new Status(status.FAILED, "上传失败！未知原因！您可以反馈给网站管理员！", null);
				}
			} else {
				return new Status(status.FAILED, "未上传头图或头图上传失败！", null);
			}
		} else {
			return new Status(status.FAILED, "当前用户未登录！", null);
		}
	}

	@Override
	public Status uploadPicture(HttpServletRequest request, CommonsMultipartFile picture) {
		UserPojo user = (UserPojo) request.getSession().getAttribute("user");
		if (user != null) {
			FileUploadBySSM fileuploadBySSM = new FileUploadBySSM();
			// 上传位置,设定文件保存的目录
			String path = fileSavePath.imgPath + "items\\";
			String newFileName = fileuploadBySSM.fileDealWith(picture, request, path);
			if (newFileName != null) {
				int user_id = user.getUser_id();
				String picture_url = fileSavePath.img_sql_savePath + "items/" + newFileName;
				sqlAction = writePageDao.uploadPicture(user_id, picture_url);
				if (sqlAction > 0) {
					itemsCheckPojo = writePageDao.getItemsCheckPojo(picture_url);
					// 发送一个session
					request.getSession().setAttribute("write", itemsCheckPojo);
					return new Status(status.SUCCESS, "头图上传成功！待审核", itemsCheckPojo);
				} else {
					return new Status(status.FAILED, "上传失败！未知原因！您可以反馈给网站管理员！", null);
				}
			} else {
				return new Status(status.FAILED, "图片上传失败！", null);
			}
		} else {
			return new Status(status.FAILED, "当前用户未登录！", null);
		}
	}
}
