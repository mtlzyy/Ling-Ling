package com.music.controller.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.music.util.ReqRespSupport;

@Controller
@RequestMapping("logOut")
public class LogoutController {
	
	@RequestMapping("logOut")
	public ModelAndView logOut(){
		//HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		ModelAndView mav = new ModelAndView();
		HttpSession ss = ReqRespSupport.getSession();
		HttpServletRequest req = ReqRespSupport.getReq();
		HttpServletResponse resp = ReqRespSupport.getResp();
		ss.removeAttribute("loginUser");
		Cookie[] cookies = req.getCookies();
		for(Cookie c:cookies){
			if(c.getName().equals("userName")){
				c.setMaxAge(0);
				resp.addCookie(c);
				break;
			}
		}
		//mav.setViewName("logOut");
		mav.setView(new RedirectView("../main/init.do"));
		return mav;
	}
}
