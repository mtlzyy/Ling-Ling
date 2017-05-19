package com.music.dao.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.SingUserDao;
import com.music.dao.TempUserDao;
import com.music.pojo.TempUser;

@Service
public class TempUserService {
	
	@Autowired
	private TempUserDao tudao;
	@Autowired
	private SingUserDao sudao;
	
	/**
	 * 创建用户
	 * @param name
	 * @param pwd
	 * @param email
	 * @param avcode
	 */
	public synchronized void createTempUser(String name,String pwd,String email,String avcode){
		tudao.createTempUser(name, pwd, email, avcode, new Date());
	}
	
	public TempUser avcodeMatch(String avcode){
		return tudao.avcodeMatch(avcode);

	}
	
	public TempUser findLatestUser(){
		return tudao.findLatestUser();
	}
	
	public synchronized void ActivateUserById(int id){
		TempUser tu = tudao.findUserById(id);
		sudao.createNewUser(tu);
		tudao.removeUserById(id);
	}
	
	public void removeUserByTime(){
		tudao.removeTempUsersTwoDaysAgo();
	}
}
