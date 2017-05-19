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
	    mailInfo.setSubject("验证邮件");  
	    mailInfo.setContent(activationInfo);  
	    System.out.println("邮件发送");
	    SimpleMailSender sms = new SimpleMailSender();   
	    sms.sendTextMail(mailInfo);
	  
	}
}
