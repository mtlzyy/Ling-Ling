package com.music.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.pojo.TempUser;

public interface TempUserDao {
	
	/**
	 * 根据ID查找用户
	 * @param id
	 * @return
	 */
	public TempUser findUserById(int id);
	
	/**
	 * 创建临时用户
	 * @param name
	 * @param pwd
	 * @param email
	 * @param avcode
	 */
	public void createTempUser(String name,String pwd,String email,String avcode,@Param("regDate")Date d);
	
	/**
	 * 判断是否有相同激活码
	 * @param avcode
	 * @return
	 */
	public TempUser avcodeMatch(String avcode);
	
	/**
	 * 找到最近注册的一名用户
	 * @return
	 */
	public TempUser findLatestUser();
	
	/**
	 * 根据ID删除用户
	 * @param id
	 */
	public void removeUserById(int id);
	
	public void removeTempUsersTwoDaysAgo();
}
