package com.whg.web.util;

public class EmailUtil {
	public static void sendEmail(
			String email,String content){
		System.out.println("给用户:"+email
				+"发送验证码"+content);
	}
}
