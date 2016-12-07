package com.aibinong.backyard.dao;

import java.io.Serializable;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.aibinong.backyard.JedisUtils;


@IocBean
public class RedisDao {
	private final static Logger LOG = LoggerFactory.getLogger(RedisDao.class);
    private JedisPool pool;
    private final static int DEFAULT_MAX_IDLE = 8;
	private final static int DEFAULT_MAX_TOTAL = 8;
	private final static int DEFAULT_TIMEOUT = 3000;
	private final static int DEFAULT_WAITTIME=5000;
	private final static boolean DEFAULT_TEST_ON_BORROW = false;
	private final static boolean DEFAULT_TEST_ON_RETURN = false;
	
	private String host;
	private int port;
	private String password;
	private int timeout = DEFAULT_TIMEOUT;
	private int maxIdel = DEFAULT_MAX_IDLE;
	private int maxTotal = DEFAULT_MAX_TOTAL;
	private int waitTime= DEFAULT_WAITTIME;
	private boolean testOnBorrow = DEFAULT_TEST_ON_BORROW;
	private boolean testOnReturn = DEFAULT_TEST_ON_RETURN;

    /**
     * 缓存生存时间
     */
    private final int expire = 60;



	public void init() throws Exception{
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(maxIdel);
			config.setMaxTotal(maxTotal);
			config.setTestOnBorrow(testOnBorrow);
			config.setTestOnReturn(testOnReturn);
			config.setMaxWaitMillis(waitTime);
			if (StringUtils.isNotBlank(password)) {
				pool = new JedisPool(config, host, port, timeout, password);
			} else {
				pool = new JedisPool(config, host, port, timeout);
			}
			JedisUtils.jedisInit(this);
			LOG.info("[{}:{}] redis pool init success!", new Object[] { this.host, this.port });
		} catch (Exception e) {
			LOG.error("redis poll init error", e);
			throw e;
		}
	}
	
	public void close() {
		if(pool != null) {
			pool.destroy();
		}
	}
    
    /**
     * 从jedis连接池中获取获取jedis对象
     * 
     * @return
     */
    public Jedis getJedis() {
        return pool.getResource();
    }
    
    /**
	 * 关闭实例
	 * @author zhang_zg
	 * @param jedis
	 */
	public void closeJedis(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	/**
	 * 回收jedis
	 * 
	 * @param jedis
	 */
    public void returnJedis(Jedis jedis) {
        pool.returnResourceObject(jedis);
    }
    
    /**
     * 设置过期时间
     *
     * @author ruan 2013-4-11
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        Jedis jedis = getJedis();
        try {
            jedis.expire(key, seconds);
        } finally {
            returnJedis(jedis);
        }
    }
    
    /**
     * 设置默认过期时间
     *
     * @author ruan 2013-4-11
     * @param key
     */
    public void expire(String key) {
        expire(key, expire);
    }
    
    public void setx(String key, String value, int expire) {
        Jedis jedis = getJedis();
        try {
            jedis.set(key, value);
			if (expire > 0) {
				jedis.expire(key, expire);
			}
        } finally {
            returnJedis(jedis);
        }
    }
    
    public void setx(String key, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.set(key, value);
            jedis.expire(key, expire);
        } finally {
            returnJedis(jedis);
        }
    }

    public void del(String key) {
        Jedis jedis = getJedis();
        try {
            jedis.del(key);
        } finally {
            returnJedis(jedis);
        }
    }

    public String get(String key) {
        String value = null;
        Jedis jedis = getJedis();
        try {
            value = jedis.get(key);
        } finally {
            returnJedis(jedis);
        }
        return value;
    }
    
    
    public Object getByKey(String key) {
    	Object value = null;
        Jedis jedis = getJedis();
        try {
            byte[] b = jedis.get(key.getBytes());
			if(b == null || b.length == 0)
				return null;
			value= (Object) SerializationUtils.deserialize(b);
        } finally {
            returnJedis(jedis);
        }
        return value;
    }

	/**
	 * 发送队列消息
	 * @author zhang_zg
	 * @param key
	 * @param value
	 */
	public void pushQueueMessage(String key, String value) {
		Jedis jedis = getJedis();
		try {
			jedis.lpush(key, value);
		} finally {
			returnJedis(jedis);
		}
	}

	public String popQueueMessage(String key) {
		String value = null;
		Jedis jedis = getJedis();
		try {
			value = jedis.rpop(key);
		} finally {
			returnJedis(jedis);
		}
		return value;
	}
    public void set(String key, Serializable value){

        Jedis jedis = getJedis();
        try{
            jedis.set(key.getBytes(), SerializationUtils.serialize(value));
        }finally{
            returnJedis(jedis);
        }
    }

    public void set(String key, Serializable value, int expiredTime){
        Jedis jedis = getJedis();
        try{
            jedis.setex(key.getBytes(), expiredTime, SerializationUtils.serialize(value));
        }finally{
            returnJedis(jedis);
        }
    }
    
    public void lpush(String key,String[] strings){
    	 Jedis jedis = getJedis();
         try{
        	 jedis.lpush(key, strings);
         }finally {
    		returnJedis(jedis);
		}
    }

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxIdel() {
		return maxIdel;
	}

	public void setMaxIdel(int maxIdel) {
		this.maxIdel = maxIdel;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
    
}
