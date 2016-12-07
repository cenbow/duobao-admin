package com.aibinong.backyard.util;

import java.util.HashMap;

import org.nutz.http.Http;
import org.nutz.lang.Lang;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aibinong.backyard.sms.SmsException;

public class SendUtils {
	private final static Logger log = LoggerFactory.getLogger(SendUtils.class);
	public static void sendMesage(String mobile,String content,String title) throws SmsException{
		 Long timestamp = System.currentTimeMillis() / 1000;
	        String token = Lang.md5(mobile + content + timestamp);
	        HashMap<String, Object> params = new HashMap<String, Object>();
	        params.put("mobile", mobile);
	        params.put("message", content);
	        params.put("timestamp", timestamp + "");
	        params.put("token", token.toLowerCase());

	        String sendMessageUrl = "http://api.dizhubangapp.com/service/sendSPMessage.html";
	        log.info("发送短信:" + params + "\r\n");
	        String responseStr = Http.post(sendMessageUrl, params, 30000);
	        log.info("短信发送结果:" + responseStr + "\r\n");
	}
}
