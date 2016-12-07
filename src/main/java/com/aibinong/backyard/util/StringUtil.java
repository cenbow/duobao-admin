package com.aibinong.backyard.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.MissingFormatArgumentException;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.nutz.lang.Encoding;
import org.nutz.lang.Lang;


/**
 * StringUtil
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年7月6日 上午11:16:00
 */
public class StringUtil {
	private static Random random = new Random();
	private final static char BRACKET_START = '{';
	private final static char BRACKET_END = '}';

	private StringUtil() {
	}

	/**
	 * 格式化手机号码
	 * @author zhang_zg
	 * @param mobile
	 * @return
	 */
	public static String formatMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return "";
		}

		char[] ch = mobile.toCharArray();
		for (int i = 3; i <= 6; i++) {
			ch[i] = '*';
		}
		return String.valueOf(ch);
	}

	public static String randomString(Integer length) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			boolean b = random.nextBoolean();
			if (b) { // 字符串  
				int choice = random.nextBoolean() ? 65 : 97; //取得65大写字母还是97小写字母  
				sb.append((char) (choice + random.nextInt(26)));// 取得大写字母  

			} else { // 数字  
				sb.append(random.nextInt(10));
			}
		}
		return sb.toString();
	}

	/**
	 * 订单ID(总共25位):yyyyMMddHHmmssSSS+userId(8位userid)
	 * @author zhang_zg
	 * @param userId
	 * @return
	 */
	public static String genOrderId(long userId) {
		return DateUtil.format(new Date(), DateUtil.SDF_MSEC) + userId;
	}

	/**
	 * 从订单编号获得用户ID
	 * @author zhang_zg
	 * @param orderId
	 * @return
	 */
	public static long getUserIdByOrderId(String orderId) {
		String userId = orderId.substring(17);
		return Long.parseLong(userId);
	}

	public static String decode(String s) {
		if (null == s)
			return "";
		try {
			return URLDecoder.decode(s.toString(), Encoding.CHARSET_UTF8.name());
		} catch (UnsupportedEncodingException e) {
			throw Lang.wrapThrow(e);
		}
	}

	/**
	 * 占位符替换
	 * 	如:StringUtil.format("{domain}:{port}", "http://www.baidu.com", 80)
	 * @author zhang_zg
	 * @param format
	 * @param args
	 * @return
	 */
	public static String format(String format, Object... args) {
		StringBuilder sb = new StringBuilder();
		StringBuilder specifier = new StringBuilder();
		try {
			char[] ch = format.toCharArray();
			int num = 0;
			for (char c : ch) {
				if (c == BRACKET_START) {
					specifier.append(c);
					continue;
				}
				if (StringUtils.isNotBlank(specifier) && c != BRACKET_END) {
					specifier.append(c);
					continue;
				}
				if (c == BRACKET_END) {
					specifier.append(c);
					sb.append(args[num++]);
					specifier = new StringBuilder();
					continue;
				}
				sb.append(c);
			}

			// 没找到'}'
			if (StringUtils.isNotBlank(specifier)) {
				throw new IllegalArgumentException("can not find '}'");
			}

			return sb.toString();
		} catch (Exception ex) {
			throw new MissingFormatArgumentException(specifier.toString());
		}
	}

	public static void main(String[] args) {
		System.out.println(StringUtil.format("{domain}:{port}", "http://www.baidu.com", 80));
	}
}
