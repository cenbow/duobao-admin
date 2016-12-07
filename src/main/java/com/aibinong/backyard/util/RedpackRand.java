package com.aibinong.backyard.util;

import java.util.Random;

/**
 * 红包随机数
 * @author zhang_zg 
 * @version 1.0    
 * @created 2016年9月28日 下午5:09:29
 */
public class RedpackRand {

	private static Random random = new Random();

	private RedpackRand() {
	}

	public static int[] random(int total, int max, int avg, int min) {
		if (total <= 0 || avg <= 0 || max <= 0 || min <= 0) {
			throw new IllegalArgumentException("all paramters value required > 0.");
		}

		if (avg > max) {
			throw new IllegalArgumentException("avg can not > max.");
		}

		if (avg < min) {
			throw new IllegalArgumentException("avg can not < min.");
		}

		if (min > max) {
			throw new IllegalArgumentException("min can not > max.");
		}

		int[] result = new int[total];

		for (int i = 0; i < total; i++) {
			int temp = 0;
			if (random(min, max) > avg) { // 随机数>平均值，则产生小红包
				temp = min + gRandom(min, avg);
			} else {
				temp = max - gRandom(avg, max); // 否则, 产生大红包
			}
			result[i] = temp;
		}
		return result;
	}

	private static int random(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}

	/**
	 * 高斯分布处理随机数
	 * @param min
	 * @param max
	 * @return
	 */
	private static int gRandom(int min, int max) {
		double g = 0;
		for (;;) {
			g = Math.abs(random.nextGaussian()); // 取绝对值
			if (g <= 1)
				break;
		}
		return (int) Math.round((max - min) * (1 - g));
	}

	public static void main(String[] args) {
		int total = 100;
		int max = 30;
		int avg = 3;
		int min = 1;

		for (int i = 0; i <= 100; i++) {
			int[] array = RedpackRand.random(total, max, avg, min);
			int sum = 0;
			for (int j : array) {
				sum += j;
				System.out.print(j + ", ");
			}
			System.out.println("[avg = " + sum / total + ",sum = " + sum + "]");
		}
	}
}
