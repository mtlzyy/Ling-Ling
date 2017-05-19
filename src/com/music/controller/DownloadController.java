package com.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.music.dao.service.DownloadService;

@Controller
public class DownloadController {
	@Autowired
	private DownloadService downloadservice;
	
	
@RequestMapping("download")
public ModelAndView download(){
	ModelAndView mav=new ModelAndView();
	mav.setViewName("download");
	return mav;
}
@RequestMapping("download2")
public ModelAndView download2(){
	ModelAndView mav=new ModelAndView();
	downloadservice.download();
	mav.setViewName("download");
	return mav;
}

}
