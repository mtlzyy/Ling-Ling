package com.music.email;



public class SendMail {
	public void sendActivationMail(String email,String activationInfo){
		MailSenderInfo mailInfo = new MailSenderInfo(); 
	    mailInfo.setMailServerHost("smtp.163.com");   
	    mailInfo.setMailServerPort("25");    
	    mailInfo.setValidate(true);  
	    mailInfo.setUserName("yyzRandom@163.com"); 	    
	    mailInfo.setPassword("zyy2011");
	    mailInfo.setFromAddress("yyzRandom@163.com");    
	    mailInfo.setToAddress(email); 
	    mailInfo.setSubject("��֤�ʼ�");  
	    mailInfo.setContent(activationInfo);  
	    System.out.println("�ʼ�����");
	    SimpleMailSender sms = new SimpleMailSender();   
	    sms.sendTextMail(mailInfo);
	  
	}
}
