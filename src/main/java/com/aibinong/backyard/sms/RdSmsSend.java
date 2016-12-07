package com.aibinong.backyard.sms;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

/**
 * 容大友信
 * 
 * @author lea
 *
 */
public class RdSmsSend implements SmsSend {
	public static final String ALIULIAN_SMS_URL = "http://116.213.72.20/SMSHttpService/send.aspx";
	public static final String ALIULIAN_SMS_USERNAME = "all";
	public static final String ALIULIAN_SMS_PASSWORD = "all";

	@Override
	public void send(String mobile, String message,String title) throws SmsException {
		String mes_title="爱榴莲";
		if(StringUtils.isNotEmpty(title)){
			mes_title=title;
		}
		message = "【"+mes_title+"】" + message;
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(ALIULIAN_SMS_URL);
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("username", ALIULIAN_SMS_USERNAME),
				new NameValuePair("password", ALIULIAN_SMS_PASSWORD), new NameValuePair("mobile", mobile),
				new NameValuePair("content", message) };
		post.setRequestBody(data);
		try {
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
			if (result != null && result.equals("-2")) {
				throw new SmsException("提交的号码中包含不符合格式的手机号码");
			}
			if (result != null && result.equals("-1")) {
				throw new SmsException("数据保存失败");
			}
			if (result != null && result.equals("0")) {

			}
			if (result != null && result.equals("1001")) {
				throw new SmsException("用户名或密码错误");
			}
			if (result != null && result.equals("1002")) {
				throw new SmsException("余额不足");
			}
			if (result != null && result.equals("1003")) {
				throw new SmsException("参数错误，请输入完整的参数");
			}
			if (result != null && result.equals("1004")) {
				throw new SmsException("其他错误");
			}
			if (result != null && result.equals("1005")) {
				throw new SmsException("预约时间格式不正确");
			}
		} catch (HttpException e) {
			throw new SmsException(e);
		} catch (IOException e) {
			throw new SmsException(e);
		} finally {
			post.releaseConnection();
		}

	}

}
