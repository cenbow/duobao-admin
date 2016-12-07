package com.aibinong.backyard;

import com.aibinong.backyard.dao.RedisDao;


public class JedisUtils {
	public static RedisDao redisDao;
	public static void jedisInit(RedisDao jedis){
		redisDao=jedis;
	}
	public static RedisDao getJedis(){
		return redisDao;
	}

}
