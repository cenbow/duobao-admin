package com.aibinong.backyard.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Util{	
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private static final ThreadLocal<MessageDigest> threadDigest = new ThreadLocal<MessageDigest>();
	

	public static String getMD5String(String str) {
		return getMD5String(str.getBytes());
	}
	
	
	public static String getMD5String(byte[] bytes) {
		MessageDigest messageDigest = threadDigest.get();
		if(messageDigest == null){
			try {
				messageDigest = MessageDigest.getInstance("MD5");
				threadDigest.set(messageDigest);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		
		messageDigest.update(bytes);
		return bytesToHex(messageDigest.digest());
	}
	

	public static String bytesToHex(byte bytes[]) {
		return bytesToHex(bytes, 0, bytes.length);
	}
	
	
	public static String bytesToHex(byte bytes[], int start, int end) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < start + end; i++) {
			sb.append(byteToHex(bytes[i]));
		}
		return sb.toString();
	}
	
	public static String byteToHex(byte bt) {
		return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];
	}	
	
    public static void main(String[] args) throws Exception{
    	System.out.println(MD5Util.getMD5String("hello"));
    	System.out.println(MD5Util.getMD5String("123456"));
    }
}