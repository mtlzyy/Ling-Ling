package com.music.controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class MyTestController {
	@RequestMapping("test")
	public void test(String albumName,String albumimage,@RequestParam("songlist")String list){

		//获取请求对象
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//获取session
		HttpSession session = request.getSession();
		
		System.out.println("songlist:"+list);
		System.out.println("albumimage:"+albumimage);
		System.out.println("albumName:"+albumName);
		
		System.out.println("req:"+request.getParameter("albumName"));
}
}
