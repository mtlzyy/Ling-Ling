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
		//����������ĸ�ʽ���ַ���
		resp.setContentType("text/html;charset=utf-8");
		//���������·��
		String path=req.getParameter("path");
		//����������·��
		path=session.getServletContext().getRealPath("/")+path;
		File file=new File(path);
		BufferedInputStream inputstream=null;
		BufferedOutputStream outputstream=null;
		//����ļ�������
		try {
			if(!file.exists()){
				String s="�ļ�������";
				resp.getWriter().print(s);
				return;
			}
			int index=path.lastIndexOf("/");
			//��ȡ�����ļ�������
			String filename=path.substring(index+1);
			//��ȡ��Ӧ����������
			ServletOutputStream output=resp.getOutputStream();
			//������Ӧ�������Ӧ����
			resp.setHeader("Content-disposition", "attachment;filename="+filename);
			//������������ȡ�ļ�
			inputstream=new BufferedInputStream(new FileInputStream(file));
			//��װ��Ӧ��������������ļ�
			outputstream=new BufferedOutputStream(output);
			//�趨����
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
