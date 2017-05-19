package com.music.controller.rank.util;

import java.util.ArrayList;
import java.util.List;

public class RankUtil {
	public static List change(String arrChk){
		String s = arrChk.substring(arrChk.indexOf("[")+1, arrChk.indexOf("]"));
		String news;
		news = s.substring(s.lastIndexOf(",")+1);
		news = news.substring(1,news.length()-1);
		List list = new ArrayList();
		list.add(news);
		for(int i = 0;i<=s.length()+1;i++){
			int index = s.indexOf(",");
			news = s.substring(0, index);
			s=s.substring(index+1);
			news = news.substring(1,news.length()-1);
			list.add(news);
		}
		return list;
	}
}
