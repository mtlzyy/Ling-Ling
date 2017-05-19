package com.music.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.pojo.TempUser;

public interface TempUserDao {
	
	/**
	 * ����ID�����û�
	 * @param id
	 * @return
	 */
	public TempUser findUserById(int id);
	
	/**
	 * ������ʱ�û�
	 * @param name
	 * @param pwd
	 * @param email
	 * @param avcode
	 */
	public void createTempUser(String name,String pwd,String email,String avcode,@Param("regDate")Date d);
	
	/**
	 * �ж��Ƿ�����ͬ������
	 * @param avcode
	 * @return
	 */
	public TempUser avcodeMatch(String avcode);
	
	/**
	 * �ҵ����ע���һ���û�
	 * @return
	 */
	public TempUser findLatestUser();
	
	/**
	 * ����IDɾ���û�
	 * @param id
	 */
	public void removeUserById(int id);
	
	public void removeTempUsersTwoDaysAgo();
}
