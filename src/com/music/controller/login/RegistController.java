package com.music.controller.login;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.music.dao.service.SingUserService;
import com.music.dao.service.TempUserService;
import com.music.email.SendMail;
import com.music.form.RegistForm;
import com.music.pojo.SingUser;
import com.music.pojo.TempUser;
import com.music.util.CommonUtil;

@Controller
@RequestMapping("regist")
public class RegistController {
	private ModelAndView mav = new ModelAndView();
	@Autowired
	private SingUserService sus;
	@Autowired
	private TempUserService tus;
	
	@RequestMapping("test")
	public ModelAndView testRegist(){
		mav.setViewName("regist");
		return mav;
	}
	
	@RequestMapping("naming")
	public ModelAndView nameMatch(@RequestParam("uname")String uname){
		
		SingUser su = sus.findUserByUserName(uname);
		if(su==null){
			mav.addObject("nameConflict", "f");
			mav.setViewName("ajax/nameNotExist");
		}
		else{
			mav.addObject("nameConflict", "t");
			mav.setViewName("ajax/nameExist");
		}		
		return mav;
	}
	@RequestMapping("regist")
	public synchronized ModelAndView regist(RegistForm form){
		String userName = form.getUserName();
		String pwd = form.getPwd();
		String email = form.getEmail();
		System.out.println("email:"+email);
		String avcode = CommonUtil.generateCode(14);
		TempUser u = null;
		while((u=tus.avcodeMatch(avcode))!=null){
			avcode = CommonUtil.generateCode(14);
		}
		tus.createTempUser(userName, pwd, email, avcode);
		TempUser tuser = tus.findLatestUser();
		System.out.println(tuser.getTempID());
		SendMail sm = new SendMail();
		String avlink = "http://localhost:8080/MusicWeb/regist/activation.do?avcode="+avcode+"&tempId="+tuser.getTempID();
		sm.sendActivationMail(email, avlink);
		mav.setViewName("temp/email_sent");
		return mav;
	}
	
	@RequestMapping("activation")
	public ModelAndView activate(@RequestParam("avcode")String avcode,@RequestParam("tempId")String tempId){
		//通过激活码寻找临时用户
		TempUser user = tus.avcodeMatch(avcode);
		//如果存在该用户
		if(user!=null){
			//如果该临时用户的ID不等于当时提供链接的ID
			if(!(user.getTempID()+"").equals(tempId)){
				mav.addObject("linkVf", "链接已失效");
				mav.addObject("sf", "failed");
			}
			else{
				mav.addObject("linkVf", "用户激活成功！");
				mav.addObject("sf", "success");
				tus.ActivateUserById(user.getTempID());
			}
		}
		//如果不存在该用户
		else{
			mav.addObject("linkVf", "链接无效或已失效");
			mav.addObject("sf", "failed");
		}
		mav.setViewName("temp/activation");
		return mav;
	}
}
