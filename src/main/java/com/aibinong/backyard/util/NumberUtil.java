package com.aibinong.backyard.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.lang3.StringUtils;

public class NumberUtil {

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	/**
	* 提供精确的加法运算。
	* 
	* @param v1
	*            被加数
	* @param v2
	*            加数
	* @return 两个参数的和
	*/
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	* 提供精确的减法运算。
	* 
	* @param v1
	*            被减数
	* @param v2
	*            减数
	* @return 两个参数的差
	*/
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	* 提供精确的乘法运算。
	* 
	* @param v1
	*            被乘数
	* @param v2
	*            乘数
	* @return 两个参数的积
	*/
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/** */
	/**
	* 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	* 
	* @param v1
	*            被除数
	* @param v2
	*            除数
	* @return 两个参数的商
	*/
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	* 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	* 
	* @param v1
	*            被除数
	* @param v2
	*            除数
	* @param scale
	*            表示表示需要精确到小数点以后几位。
	* @return 两个参数的商
	*/
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	* 提供精确的小数位四舍五入处理。
	* 
	* @param v
	*            需要四舍五入的数字
	* @param scale
	*            小数点后保留几位
	* @return 四舍五入后的结果
	*/
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 字符串转长整型, 出错返回-1
	 * @author zhang_zg
	 * @param s
	 * @return
	 */
	public static long parseLong(String s) {
		try {
			if (StringUtils.isNumeric(s)) {
				return Long.parseLong(s);
			}
		} catch (Exception e) {
		}
		return -1;
	}

	/**
	 * 
	 * 描述  格式化百分比
	 * @param v1  除数
	 * @param v2  被除数
	 * @return 34.45%
	 */
	public static String getPercent(double v1, double v2) {
		if (v2 > 0) {
			double percent = (float) v1 / v2;
			NumberFormat nt = NumberFormat.getPercentInstance();
			nt.setMinimumFractionDigits(2);
			return nt.format(percent);
		} else {
			return "0.00%";
		}
	}

	/**
	 * double类型格式化
	 * 描述  
	 * @author zhang_zg
	 * @param num
	 * @param formatStr
	 * @return
	 */
	public static String formatDouble(double num, String formatStr) {
		String str = "0.00";

		if ((Math.abs(num - 0.0) < 0.00000001) || Double.isNaN(num)) {
			DecimalFormat dFormat = new DecimalFormat(formatStr);
			str = dFormat.format(0);
			return str;
		}

		DecimalFormat dFormat = new DecimalFormat(formatStr);
		str = dFormat.format(num);

		return str;
	}
}
