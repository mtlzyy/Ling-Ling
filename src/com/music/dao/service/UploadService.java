package com.music.dao.service;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.music.dao.AlbumDao;
import com.music.dao.FavSongDao;
import com.music.dao.SingUserDao;
import com.music.dao.SingerDao;
import com.music.dao.SongTypeDao;
import com.music.dao.SongsDao;
import com.music.pojo.Album;
import com.music.pojo.FavSong;
import com.music.pojo.SingUser;
import com.music.pojo.Singer;
import com.music.pojo.SongType;
import com.music.pojo.Songs;
import com.music.util.ReqRespSupport;
@Service
public class UploadService {
@Autowired
private SingerDao singerDao;
@Autowired
private SongsDao songsDao;
@Autowired
private SongTypeDao songTypeDao;
@Autowired
private AlbumDao albumDao;
@Autowired
private SingUserDao singUserDao;
@Autowired
private FavSongDao favsongDao;

	public void upload(){
		List<FileItem> formfield=new ArrayList<FileItem>();
		List<FileItem> notformfield=new ArrayList<FileItem>();
		DiskFileUpload disk=new DiskFileUpload();

		

		
		HttpServletRequest req=ReqRespSupport.getReq();
		HttpSession session = req.getSession();
		disk.setHeaderEncoding("utf-8");
		//创建缓冲临时文件夹
		File tempDirectory=new File("d:/temp");
		if(!tempDirectory.exists()){
			tempDirectory.mkdir();
		}
		//设定缓冲临时文件
		disk.setRepositoryPath("d:/temp");
		//设定缓冲文件大小  单位字节
		disk.setSizeThreshold(1024*10);
		//设置上传文件大小
		disk.setSizeMax(1024*1024*100);
		try {
			List<FileItem> filelist=disk.parseRequest(req);
			for (FileItem f : filelist) {
				if(!f.isFormField()){
					notformfield.add(f);
				}else{
					formfield.add(f);
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String singerid=null;
		String singername=null;
		String keyword=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		
		for (FileItem f : formfield) {
			if(f.getFieldName().equals("singerid")){
				singerid=f.getString();
			}
			if(f.getFieldName().equals("singername")){
				singername=f.getString();
			}
			if(f.getFieldName().equals("keyword")){
				keyword=f.getString();
			}
		}
		
		
		try {
			byte[] singerbytes = singername.getBytes("ISO-8859-1");
			singername = new String(singerbytes,"utf-8");
			byte[] keywordbytes = keyword.getBytes("ISO-8859-1");
			keyword = new String(keywordbytes,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int singid=Integer.parseInt(singerid);
	
		System.out.println(singid);
		System.out.println(singername);
		System.out.println(keyword);
		
		
		String path=session.getServletContext().getRealPath("/");
		File dest=null;
		
		Map<String,String> uploadMap = new HashMap<String, String>();
		if(keyword.equals("album")){
			String albumname = null;
			String albumInfo = null;
			String createdate = null;
			
			String songnumber=null;
			String songlistname=null;
			String songlistvip=null;
			String songlistlanguage=null;
			String songlisttype=null;
			String songlistdate=null;
	
			for (FileItem f : formfield) {
				if(f.getFieldName().equals("albumName")){
					albumname=f.getString();
				}
				if(f.getFieldName().equals("albumInfo")){
					albumInfo=f.getString();
				}
				if(f.getFieldName().equals("createDate")){
					createdate=f.getString();
				}
				if(f.getFieldName().equals("songnumber")){
					songnumber=f.getString();
				}
			}
			int number=Integer.parseInt(songnumber);
			
				try {
					byte[] bytes1 = albumname.getBytes("ISO-8859-1");
					albumname = new String(bytes1,"utf-8");
					dest=new File(path+"\\image\\album\\"+singid+"\\"+albumname);		
					if(!dest.exists()){
						dest.mkdirs();
					}
					for(FileItem f:notformfield){
							//获取上传文件的输入流
							BufferedInputStream input=new BufferedInputStream(f.getInputStream());
							//获取上传文件的文件
							String filename=f.getName().substring(0, f.getName().lastIndexOf("."));							
							String filetype=f.getName().substring(f.getName().lastIndexOf(".")+1);
							//设置上传路径
							String sf=dest.getAbsolutePath()+"\\"+filename+"."+filetype;
							String str=singid+"/"+albumname+"/"+filename+"."+filetype;
							for(int i=1;i<=number;i++){
								if(f.getFieldName().equals("songlistpath"+i)){
									for (FileItem f2: formfield) {
										if(f2.getFieldName().equals("songlistname"+i)){
											songlistname=f2.getString();
											File dest1=new File(path+"\\audio\\"+singid+"\\"+songlistname);
											if(!dest1.exists()){
												dest1.mkdirs();
											}
											sf=dest1.getAbsolutePath()+"\\"+filename+"."+filetype;
											str=singid+songlistname;
										}
									}

								}
							}
							//获取输出流
							BufferedOutputStream output=new BufferedOutputStream(new FileOutputStream(new File(sf)));
							uploadMap.put(f.getFieldName(), str);
							int len;
							byte[] buffer=new byte[1024];
							while((len=input.read(buffer))!=-1){
								output.write(buffer,0,len);
							}
							output.flush();
							output.close();
							input.close();

					}
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			//新增专辑
			
			Date createDate=null;
			try {
				createDate=sdf.parse(createdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String image=uploadMap.get("albumimage");
			

			
			try {
				byte[] bytes2 = albumInfo.getBytes("ISO-8859-1");
				albumInfo = new String(bytes2,"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			albumDao.addAlbum(new Album(0, albumname, albumInfo,0, image, createDate,singid));
			
			Album a=albumDao.findAlbumByname(albumname);
			Date date=null;
			
			for(int i=1;i<=number;i++){
				String songlistpath=uploadMap.get("songlistpath"+i);
				String songlistimage=uploadMap.get("songlistimage"+i);
				String songlistlyric=uploadMap.get("songlistlyric"+i);
				for (FileItem f : formfield) {
					if(f.getFieldName().equals("songlistvip"+i)){
						songlistvip=f.getString();
					}
					if(f.getFieldName().equals("songlistlanguage"+i)){
						songlistlanguage=f.getString();
					}
					if(f.getFieldName().equals("songlisttype"+i)){
						songlisttype=f.getString();
					}
					if(f.getFieldName().equals("songlistdate"+i)){
						songlistdate=f.getString();
					}
				}
				try {
					byte[] bytes3 = songlistname.getBytes("ISO-8859-1");
					songlistname= new String(bytes3,"utf-8");
					byte[] bytes5 = songlistlanguage.getBytes("ISO-8859-1");
					songlistlanguage= new String(bytes5,"utf-8");
					byte[] bytes6 = songlisttype.getBytes("ISO-8859-1");
					songlisttype = new String(bytes6,"utf-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					date=sdf.parse(songlistdate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int vip=Integer.parseInt(songlistvip);
				SongType s=songTypeDao.findTypeByTypeName(songlisttype);
				songsDao.addsongs(new Songs(0, songlistname, a.getAlbumID(), s.getSongTypeID(), songlistlanguage, 0, 0,vip , songlistpath,date, songlistlyric, songlistimage,singid));
			}	
		}else if(keyword.equals("singer")){
			
			String sex = null;
			String birth = null;
			String nationality = null;
			String beginEn = null;
			String intro = null;
			String grouptype=null;
			for (FileItem f : formfield) {
				if(f.getFieldName().equals("sex")){
					sex=f.getString();
				}
				if(f.getFieldName().equals("birth")){
					birth=f.getString();
				}
				if(f.getFieldName().equals("nationality")){
					nationality=f.getString();
				}
				if(f.getFieldName().equals("beginEn")){
					beginEn=f.getString();
				}
				if(f.getFieldName().equals("intro")){
					intro=f.getString();
				}
				if(f.getFieldName().equals("grouptype")){
					grouptype=f.getString();
				}
			}
			
			
				try {
					dest=new File(path+"\\image\\singer\\"+singid);
					if(!dest.exists()){
					dest.mkdirs();
					}
					
					for(FileItem f:notformfield){
							//获取上传文件的输入流
							BufferedInputStream input=new BufferedInputStream(f.getInputStream());
							//获取上传文件的文件
							String filename=f.getName().substring(0, f.getName().lastIndexOf("."));

							String filetype=f.getName().substring(f.getName().lastIndexOf(".")+1);
							//设置上传路径
							String sf=dest.getAbsolutePath()+"\\"+filename+"."+filetype;
							//获取输出流
							BufferedOutputStream output=new BufferedOutputStream(new FileOutputStream(new File(sf)));
							String str=singid+filename+"."+filetype;
							uploadMap.put(f.getFieldName(), str);
							int len;
							byte[] buffer=new byte[1024];
							while((len=input.read(buffer))!=-1){
								output.write(buffer,0,len);
							}
							output.flush();
							output.close();
							input.close();

					}
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			//新增歌手
			int s=0;
			if(sex.equals("女")){
				s=1;
			}
			
			Date b=null;
			try {
				b=sdf.parse(birth);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String singerimage=uploadMap.get("singerimage");
			
			
			try {
				byte[] bytes2 = nationality.getBytes("ISO-8859-1");
				nationality = new String(bytes2,"utf-8");
				byte[] bytes3 = intro.getBytes("ISO-8859-1");
				intro = new String(bytes3,"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int group=0;
			if(grouptype.equals("是")){
				group=1;
			}
			
			
			singerDao.addSinger(new Singer(0,singername, s, b, nationality, singerimage, beginEn,intro,group));
		}else{
			//歌曲
			String languages = null;
			String vip = null;
			String songtypename = null;
			String songName = null;
			String songdate = null;
			
			
			for (FileItem f : formfield) {
				if(f.getFieldName().equals("languages")){
					languages = f.getString();
				}
				if(f.getFieldName().equals("vip")){
					vip = f.getString();
				}
				if(f.getFieldName().equals("type")){
					songtypename = f.getString();
				}
				if(f.getFieldName().equals("songName")){
					songName = f.getString();
				}
				if(f.getFieldName().equals("songDate")){
					songdate = f.getString();
				}
			}
				try {
					dest=new File(path+"\\image\\song\\"+singid);
					File dest1=new File(path+"\\audio\\"+singid); 
					if(!dest.exists()){
						dest.mkdirs();
					}
					if(!dest1.exists()){
						dest1.mkdirs();
					}
					for(FileItem f:notformfield){
							//获取上传文件的输入流
							BufferedInputStream input=new BufferedInputStream(f.getInputStream());
							//获取上传文件的文件
							String filename=f.getName().substring(0, f.getName().lastIndexOf("."));

							String filetype=f.getName().substring(f.getName().lastIndexOf(".")+1);
							//设置上传路径
							String sf=dest.getAbsolutePath()+"\\"+filename+"."+filetype;
							//获取输出流
							if(f.getFieldName().equals("uploadsong")){
								sf=dest1.getAbsolutePath()+"\\"+filename+"."+filetype;
							}
							BufferedOutputStream output=new BufferedOutputStream(new FileOutputStream(new File(sf)));
							String str=singid+"/"+filename+"."+filetype;
							uploadMap.put(f.getFieldName(), str);
							
							int len;
							byte[] buffer=new byte[1024];
							while((len=input.read(buffer))!=-1){
								output.write(buffer,0,len);
							}
							output.flush();
							output.close();

					}
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			//新增歌曲
			
			Date songDate=null;
			try {
				songDate=sdf.parse(songdate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int vipSong=Integer.parseInt(vip);
			
			
			
			String songimage=uploadMap.get("songimage");
			String lyric=uploadMap.get("lyric");
			String uploadsong=uploadMap.get("uploadsong");

			try {
				byte[] bytes1 = songName.getBytes("ISO-8859-1");
				songName = new String(bytes1,"utf-8");
				byte[] bytes2 = languages.getBytes("ISO-8859-1");
				languages = new String(bytes2,"utf-8");
				byte[] bytes3 = songtypename.getBytes("ISO-8859-1");
				songtypename = new String(bytes3,"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(songtypename);
			 SongType s=songTypeDao.findTypeByTypeName(songtypename);
			songsDao.addsongs(new Songs(0,songName,0, s.getSongTypeID(), languages, 0, 0, vipSong, uploadsong, songDate, lyric, songimage,singid));
		}

	}
		
	public List<Singer> findallsinger(String singername){
		try {
			byte[] bytes1 = singername.getBytes("ISO-8859-1");
			singername = new String(bytes1,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Singer> singerlist=singerDao.findSingerByname("%"+singername+"%");
		return singerlist;
	}
	
	
	
	//头像的name要为head
	//accept
	public void uploadHead(){
		DiskFileUpload disk=new DiskFileUpload();
		HttpServletRequest req=ReqRespSupport.getReq();
		HttpSession session=req.getSession();
		
		SingUser user=(SingUser) session.getAttribute("loginUser");
		int userid=user.getUserID();
		disk.setHeaderEncoding("utf-8");
		File temp=new File("d:/temp");
		if(!temp.exists()){
			temp.mkdirs();
		}
		//设定缓冲临时文件
		disk.setRepositoryPath("d:/temp");
		//设定缓冲文件大小  单位字节
		disk.setSizeThreshold(1024*10);
		//设置上传文件大小
		disk.setSizeMax(1024*1024*100);
		
		String path=session.getServletContext().getRealPath("/");
		File dest=new File(path+"\\image"+"\\user\\"+userid);
		if(!dest.exists()){
		dest.mkdirs();
		}
		Map<String, String> uploadMap=new HashMap<String, String>();
		
		try {
			List<FileItem> fileList=disk.parseRequest(req);
			for (FileItem item : fileList) {
				if(!item.isFormField()){
					
						//获取上传文件的输入流
						BufferedInputStream input=new BufferedInputStream(item.getInputStream());
						//获取上传文件的文件名
						String filename=item.getName().substring(0, item.getName().lastIndexOf("."));							
						String filetype=item.getName().substring(item.getName().lastIndexOf(".")+1);
						//设置上传路径
						String sf=dest.getAbsolutePath()+"1.jpg";
						//获取输出流
						BufferedOutputStream output=new BufferedOutputStream(new FileOutputStream(new File(sf)));
						String str=userid+"/1.jpg";
						uploadMap.put(item.getFieldName(), str);
						int len;
						byte[] buffer=new byte[1024];
						while((len=input.read(buffer))!=-1){
							output.write(buffer,0,len);
						}
						output.flush();
						output.close();
						input.close();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user.getHead().equals("default.jpg")){
			String head= uploadMap.get("head");
			singUserDao.uploadNewHead(new SingUser(userid,null,null,null,0,null,0,null,0,0,head));
		}
	}
	
	
	public void uploadFavsongImage(){
		DiskFileUpload disk=new DiskFileUpload();
		HttpServletRequest req=ReqRespSupport.getReq();
		HttpSession session=req.getSession();
		
		disk.setHeaderEncoding("utf-8");
		File temp=new File("d:/temp");
		if(!temp.exists()){
			temp.mkdirs();
		}
		disk.setRepositoryPath("d:/temp");
		disk.setSizeThreshold(1024*10);
		disk.setSizeMax(1024*1024*10);
		String path=session.getServletContext().getRealPath("/");
		File dest=null;
		String favsongid=null;
		Map<String, String> uploadMap=new HashMap<String, String>();
		try {
			List<FileItem> filelist=disk.parseRequest(req);
			for (FileItem f : filelist) {
				if(f.getFieldName().equals("favsongid")){
					favsongid=f.getString();
					dest=new File(path+"\\image\\fav\\"+favsongid);
					if(!dest.exists()){
						dest.mkdirs();
					}
				}
					if(dest!=null){
					if(!f.isFormField()){
						//获取上传文件的输入流
						BufferedInputStream input=new BufferedInputStream(f.getInputStream());
						//设置上传路径
						String sf=dest.getAbsolutePath()+"1.jpg";
						//获取输出流
						BufferedOutputStream output=new BufferedOutputStream(new FileOutputStream(new File(sf)));
						String str=favsongid+"/1.jpg";
						uploadMap.put(f.getFieldName(), str);
						int len;
						byte[] buffer=new byte[1024];
						while((len=input.read(buffer))!=-1){
							output.write(buffer,0,len);
						}
						output.flush();
						output.close();
						input.close();
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int fsongid=Integer.parseInt(favsongid);
		FavSong fav=favsongDao.findFavsongByFavsongid(fsongid);
		if(fav.getImage().equals("1.jpg")){
			String image= uploadMap.get("head");
			favsongDao.uploadFavsongImage(new FavSong(fsongid,null,0,0,image,null));
		}
	}
}
