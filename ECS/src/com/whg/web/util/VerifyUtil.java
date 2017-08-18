package com.whg.web.util;

import java.util.UUID;

public class VerifyUtil {
	public static String createVerifyCode(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}

}
