<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC
        "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="dao.AdminDao">
    
    
    <select id="admingLogin" resultMap="adminPojoMap">
       select *
       from admin
       where 
       name=#{adminName}
       and password=#{adminPassword}
    </select>
    
    <select id="adminNameCheck" resultMap="adminPojoMap">
      select *
      from admin
      where 
      name=#{adminName}
    </select>
    
    <select id="getAllItems" resultMap="ItemsPojoMapAndUserPojoMap">
     select * 
     from items i,user u
    </select>
    
    <select id="getAllFeedback" resultMap="FeedbackPojoMap">
     select *
     from user_feedback u
    </select>
    
    <select id="getFeedbackById" resultMap="FeedbackPojoMap">
     select *
     from user_feedback 
     where
     feedback_id=#{feedback_id}
    </select>
    
    <select id="getAllItemsCheckPojo" resultMap="ItemsCheckMap">
     select *
     from items_check
     where 
     check_state=0
    </select>
    
    <insert id="insertIntoItems">
    insert into 
    items(user_id,artical_name,article
          ,picture_url,goods_type,is_original,is_reprint)
    values(#{user_id},#{artical_name},#{article}
          ,#{picture_url},#{goods_type},#{is_original},#{is_reprint})
    </insert>
    
    <select id="getItemsCheckPojoByCheckID" resultMap="ItemsCheckMap">
    select *
    from items_check
    where 
    check_id=#{check_id}
    </select>
    
    <update id="changeItemsCheckStateByID">
    update items_check
    set check_state=1
    where
    check_id=#{check_id}
    </update>
    
    <insert id="sendMessageToUser">
    insert into 
    system_message(message,user_id)
    values(#{message},#{user_id})
    </insert>

    <resultMap type="pojo.AdminPojo" id="adminPojoMap">
       <id property="id" column="id"/>
       <result property="name" column="name"/>
       <result property="password" column="password"/>
       <result property="head" column="head"/>
       <result property="intro" column="intro"/>
       <result property="fans" column="fans"/>
    </resultMap>
    
    <resultMap id="ItemsPojoMapAndUserPojoMap" type="pojo.ItemsPojo">
		<id property="goods_id" column="goods_id" />
		<result property="artical_name" column="artical_name" />
		<result property="article" column="article" />
		<result property="picture_url" column="picture_url" />
		<result property="goods_type" column="goods_type" />
		<result property="publis_date" column="publis_date" />
		<result property="goods_buy_url" column="goods_buy_url" />
		<result property="is_original" column="is_original" />
		<result property="userful" column="userful" />
		<result property="unuserful" column="unuserful" />
		<result property="won_praise_for" column="won_praise_for" />
		<result property="is_reprint" column="is_reprint"/>
		<association property="userPojo" javaType="pojo.UserPojo">
		    <id property="user_id" column="user_id"/>
	        <result property="user_name" column="user_name"/>
	        <result property="user_password" column="user_password"/>
	        <result property="father_name" column="father_name"/>
	        <result property="mother_name" column="mother_name"/>
	        <result property="user_head" column="user_head"/>
	        <result property="user_intro" column="user_intro"/>
	        <result property="fans" column="fans"/>
	        <result property="won_praise_for" column="won_praise_for"/>
	        <result property="create_date" column="create_date"/>
	        <result property="user_integal" column="user_integal"/>
	        <result property="user_expericence" column="user_expericence"/>
		</association>
	</resultMap>
	
	<resultMap type="pojo.FeedbackPojo" id="FeedbackPojoMap">
	    <id property="user_id" column="user_id"/>
	    <result property="feedback" column="feedback"/>
	    <result property="feedback_time" column="feedback_time"/>
	    <result property="feedback_id" column="feedback_id"/>
	</resultMap>
	
	 <resultMap type="pojo.ItemsCheckPojo" id="ItemsCheckMap">
	    <result property="user_id" column="user_id"/>
	    <result property="artical_name" column="artical_name"/>
	    <result property="article" column="article"/>
	    <result property="picture_url" column="picture_url"/>
	    <result property="goods_type" column="goods_type"/>
	    <result property="publis_date" column="publis_date"/>
	    <result property="goods_buy_url" column="goods_buy_url"/>
	    <result property="is_reprint" column="is_reprint"/>
	    <result property="check_state" column="check_state"/>
	    <result property="check_id" column="check_id"/>
	</resultMap>

</mapper>