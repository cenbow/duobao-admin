package com.aibinong.backyard.sms;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 中国网建
 * 
 * @author lea
 *
 */
public class WebChineseSmsSend implements SmsSend {
	public static final String ALIULIAN_SMS_URL = "http://gbk.sms.webchinese.cn";
	public static final String ALIULIAN_SMS_USERNAME = "aliulian_sms";
	public static final String ALIULIAN_SMS_KEYS = "78fb5e09156613a5e156";

	@Override
	public void send(String mobile, String message,String title) throws SmsException {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(ALIULIAN_SMS_URL);
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", ALIULIAN_SMS_USERNAME), new NameValuePair("Key", ALIULIAN_SMS_KEYS),
				new NameValuePair("smsMob", mobile), new NameValuePair("smsText", message) };
		post.setRequestBody(data);
		try {
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
			if (result != null && result.equals("-1")) {
				throw new SmsException("没有该用户账户");
			}
			if (result != null && result.equals("-2")) {
				throw new SmsException("接口密钥不正确");
			}
			if (result != null && result.equals("-1")) {
				throw new SmsException("MD5接口密钥加密不正确");
			}
			if (result != null && result.equals("-21")) {
				throw new SmsException("没有该用户账户");
			}
			if (result != null && result.equals("-3")) {
				throw new SmsException("短信数量不足");
			}
			if (result != null && result.equals("-11")) {
				throw new SmsException("该用户被禁用");
			}
			if (result != null && result.equals("-14")) {
				throw new SmsException("短信内容出现非法字符");
			}
			if (result != null && result.equals("-6")) {
				throw new SmsException("IP限制");
			}
			int i = Integer.parseInt(result);
			if (i >= 0) {
				return;
			}
			if (result != null) {
				throw new SmsException("错误代码:" + result);
			}
		} catch (HttpException e) {
			throw new SmsException(e);
		} catch (IOException e) {
			throw new SmsException(e);
		} finally {
			post.releaseConnection();
		}
		// return result;

	}

//	public static void main(String[] a) throws SmsException {
//		WebChineseSmsSend ws = new WebChineseSmsSend();
//		ws.send("18600028249", "亲爱的用户，1234是您本次手机注册的验证码。");
//	}
}
