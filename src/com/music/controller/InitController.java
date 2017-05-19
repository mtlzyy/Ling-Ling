package com.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InitController {
	@RequestMapping("init")
	public String forwardLogin(){
		return "main";
	}
}
