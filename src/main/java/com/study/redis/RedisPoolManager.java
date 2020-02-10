package com.study.redis;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoolManager {

	private static JedisPool pool;
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if (bundle == null) {
			throw  new IllegalArgumentException("[redis.properties] is not found");
		}
		JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));  
        config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait")));  
        config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));  
        config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));  
        pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")));
        
        
        
	}
	
	public static Jedis createInstance() {
		Jedis jedis = pool.getResource();
		return jedis;
	}

	public static void returnResource(Jedis jedis) {
		// TODO Auto-generated method stub
		pool.returnResource(jedis);
	}
	
	
}
