package com.music.dao.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.music.util.ReqRespSupport;

@Service
public class DownloadService {
	public void download(){
		HttpServletRequest req=ReqRespSupport.getReq();
		HttpServletResponse resp=ReqRespSupport.getResp();
		HttpSession session=req.getSession();
		//设置输出流的格式和字符集
		resp.setContentType("text/html;charset=utf-8");
		//服务器相对路径
		String path=req.getParameter("path");
		//服务器绝对路径
		path=session.getServletContext().getRealPath("/")+path;
		File file=new File(path);
		BufferedInputStream inputstream=null;
		BufferedOutputStream outputstream=null;
		//如果文件不存在
		try {
			if(!file.exists()){
				String s="文件不存在";
				resp.getWriter().print(s);
				return;
			}
			int index=path.lastIndexOf("/");
			//获取下载文件的名称
			String filename=path.substring(index+1);
			//获取响应对象的输出流
			ServletOutputStream output=resp.getOutputStream();
			//设置响应对象的响应类型
			resp.setHeader("Content-disposition", "attachment;filename="+filename);
			//创建输入流读取文件
			inputstream=new BufferedInputStream(new FileInputStream(file));
			//封装响应对象的输出流输出文件
			outputstream=new BufferedOutputStream(output);
			//设定缓存
			byte[] buff=new byte[2048];
			int data;
			while(-1!=(data=inputstream.read(buff, 0, buff.length))){
				outputstream.write(buff, 0, data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				inputstream.close();
				outputstream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
