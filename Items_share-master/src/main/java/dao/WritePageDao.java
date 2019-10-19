package dao;

import org.apache.ibatis.annotations.Param;

import pojo.ItemsCheckPojo;

public interface WritePageDao {

	// 用户编辑文章，发表文章
		public int writeItems_Share(@Param("check_id") int check_id, @Param("artical_name") String artical_name,
				@Param("article") String article, @Param("goods_type") String goods_type);

		// 用户上传文章头图
		public int uploadPicture(@Param("user_id") int user_id, @Param("picture_url") String picture_url);

		// 返回文章头图插入的相应id
		public ItemsCheckPojo getItemsCheckPojo(@Param("picture_url") String picture_url);
}
