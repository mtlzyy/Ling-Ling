package com.music.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.dao.SingUserDao;
import com.music.pojo.SingUser;
import com.music.pojo.TempUser;

@Service
public class SingUserService {
	@Autowired
	private SingUserDao sudao;
	
	public SingUser findUserByUserName(String uname){
		return sudao.findUserByUserName(uname); 
	}
	
	public SingUser findUserByEmail(String email){
		return sudao.findUserByEmail(email);
	}
	
	public void createNewUser(TempUser tu){
		sudao.createNewUser(tu);
	}
	public SingUser findUserByUserid(int userid){
		return sudao.findUserByUserId(userid);
	}

	public void updateUserExEmail(String nkName, String pwd, int userid) {
		// TODO Auto-generated method stub
		SingUser u = findUserByUserid(userid);
		if(nkName!=null){
			sudao.updateNkName(userid, nkName);
		}
		if(!pwd.equals(u.getPasswords())){
			sudao.updatePwd(userid, pwd);
		}
	}

	public void updateEmail(int userid, String em) {
		// TODO Auto-generated method stub
		sudao.updateEmail(userid, em);
	}
	
	
	

}
