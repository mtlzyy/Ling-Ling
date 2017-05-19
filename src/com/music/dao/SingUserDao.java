package com.music.dao;

import com.music.pojo.SingUser;
import com.music.pojo.TempUser;

public interface SingUserDao {
		/**
	 * ID,�û�������������û�
	 * @param uname
	 * @return
	 */
	public SingUser findUserByUserName(String uname);
	public SingUser findUserByEmail(String email);
	public SingUser findUserByUserId(int id);
	
	/**
	 * ������û�
	 * @param tu
	 */
	public void createNewUser(TempUser tu);
	/**
	 * �ϴ�ͷ��
	 * @param singuser
	 */
	public void uploadNewHead(SingUser singuser);
	public void updateEmail(int userid, String email);
	public void updateNkName(int userid,String nkName);
	public void updatePwd(int userid,String pwd);
}
