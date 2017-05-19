package com.music.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommonUtil {
	
	/**
	 * 测试判断某个字符串时候含有‘@’字符
	 * @param s
	 * @return
	 */
	public static boolean containsAt(String s){
		char[] chars = s.toCharArray();
		boolean hasAt = false;
		for(char c:chars){
			if(c=='@'){
				hasAt = true;
				break;
			}
		}
		return hasAt;
	}
	
	/**
	 * 生成激活码
	 * @param length
	 * @return
	 */
	public static String generateCode(int length){
		String base = "1234567890qwertyuioplkjhgfdsazxcvbnm";
		char[] chars = base.toCharArray();
		int len = chars.length;
		Random r = new Random();
		StringBuffer sb = new StringBuffer("");
		if(length>=1){
			for(int i=0;i<length;i++){
				char c = chars[r.nextInt(len)];
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	
	/**
	 * 把a,b,c每个值取出存入list
	 * @param list
	 * @return
	 */
	public static List<String> newlist(String list){
		List<String> newlist=new ArrayList<String>();
		if(list.indexOf(",")!=-1){
		for(int i=0;i<list.length();i++){
			if(i<list.lastIndexOf(",")){
				int beginIndex=i;
				i=list.indexOf(",",i+1);
				list.substring(beginIndex, i);
				newlist.add(list.substring(beginIndex, i));
				
			}else if(i-1==list.lastIndexOf(",")){
				newlist.add(list.substring(i,list.length()));
			}
		}
		}else{
			newlist.add(list);
		}
		return newlist;
	}
	
	public static int findMaxPage(int x,int size){
		int maxPage = 0;
		if(x%size==0){
			maxPage = x/size;
		}
		else{
			maxPage = x/size+1;
		}
		return maxPage;
	}
	
	public static <T>int  findMaxPageForList(List<T> list,int size){
		int max;
		if(list==null){
			max = 0;
		}
		else{
			max = findMaxPage(list.size(), size);
		}
		return max;
	}
	
	public static boolean maxPageChange(int page,int omp,int nmp){
		if(page==omp&&omp!=nmp){
			return true;
		}
		else{
			return false;
		}
	}
}
