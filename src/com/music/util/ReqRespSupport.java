package com.music.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReqRespSupport {
	private static HttpServletRequest req;
	private static HttpServletResponse resp;
	
	
	public static HttpSession getSession(){
		HttpSession ss = null;
		if(req!=null){
			ss =  req.getSession();
		}
		return ss;
	}
	
	public static HttpServletRequest getReq() {
		return req;
	}
	public static void setReq(HttpServletRequest req) {
		ReqRespSupport.req = req;
	}
	public static HttpServletResponse getResp() {
		return resp;
	}
	public static void setResp(HttpServletResponse resp) {
		ReqRespSupport.resp = resp;
	}
	
	public static void destroy(){
		req = null;
		resp = null;
	}
	
}
