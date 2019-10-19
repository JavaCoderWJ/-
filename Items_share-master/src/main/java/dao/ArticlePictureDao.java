package dao;

import org.apache.ibatis.annotations.Param;

import pojo.ArticlePicturePojo;

public interface ArticlePictureDao {

	public int addPicture(@Param("picture_url") String picture_url);

	public ArticlePicturePojo getArticlePicturePojo(@Param("picture_url") String picture_url);
}
