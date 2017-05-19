package com.music.controller.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import com.music.dao.service.SingUserService;
import com.music.exception.LoginFormException;
import com.music.form.LoginForm;
import com.music.pojo.SingUser;
import com.music.util.CommonUtil;
import com.music.util.ReqRespSupport;

@Controller
@RequestMapping("login")
public class LoginController {
	private ModelAndView mav = new ModelAndView();
	@Autowired
	private SingUserService sus;
	
	@RequestMapping("test")
	public ModelAndView testLogin(){
		HttpSession ss = ReqRespSupport.getSession();
		SingUser u = (SingUser) ss.getAttribute("loginUser");
		System.out.println(u);
		if(u!=null){
			System.out.println(u.getUserID());
		}
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping("login")
	public ModelAndView login(LoginForm form){
		boolean x = CommonUtil.containsAt(form.getUserName());
		SingUser su = null;
		if(x){
			su = sus.findUserByEmail(form.getUserName());
		}
		else{
			su = sus.findUserByUserName(form.getUserName());
		}
		//System.out.println(form.getRmb());
		if(su!=null){
			if(su.getPasswords().equals(form.getPwd())){
				mav.setView(new RedirectView("../main/init.do"));
				HttpSession ss = ReqRespSupport.getSession();
				ss.setAttribute("loginUser", su);
				//mav.addObject("loginUser", su);
				if(form.getRmb()!=null){
					Cookie userName = new Cookie("userName", su.getUserName());
					userName.setMaxAge(1000*60*60*240);
					HttpServletResponse resp = ReqRespSupport.getResp();
					if(resp!=null){
						resp.addCookie(userName);
					}
				}
			}
			else{
				mav.setViewName("failure");
				mav.addObject("errorMsg", "用户名或密码错误");
			}
		}
		else{
			mav.setViewName("failure");
			mav.addObject("errorMsg", "用户名或密码错误");
		}
		return mav;
	}

/*	@ExceptionHandler
	public ModelAndView loginError(Exception ex){
		mav.setViewName("error");
		mav.addObject("errorMsg", "系统出错请重试");
		return mav;
	}*/
}
