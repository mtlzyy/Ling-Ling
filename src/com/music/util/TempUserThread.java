package com.music.util;

import com.music.dao.service.TempUserService;

public class TempUserThread extends Thread{
	
	private TempUserService tus;
	public void run() {
		// TODO Auto-generated method stub
		while(1==1){
			tus.removeUserByTime();
			try {
				sleep(3600*1000*24);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
