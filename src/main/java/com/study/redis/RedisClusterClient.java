package com.study.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

//import org.junit.Test;

public class RedisClusterClient {
	
	private String serverInfo = "192.168.192.111:7000,192.168.192.111:7001,192.168.192.111:7002,192.168.192.111:7003,192.168.192.111:7004,192.168.192.111:7005";
	private JedisCluster jc;

	public Set<HostAndPort> getClusterInfo(String serverInfo) {
		Set<HostAndPort> set = new HashSet<>();
		String ipPost[] = serverInfo.split(",");
		for (String ip : ipPost) {
			String server[]  = ip.split(":");
			set.add(new HostAndPort(server[0], Integer.parseInt(server[1])));
		}
		return set;
	}
	
	//@Test
	public void test() {
		try {
			Set<HostAndPort> jedisClusterNode = getClusterInfo(serverInfo);
			GenericObjectPoolConfig config = new GenericObjectPoolConfig();
			config.setMaxTotal(1000);
			config.setMinIdle(8);
			config.setMaxIdle(100);
			config.setMaxWaitMillis(0);
			
			
			jc = new JedisCluster(jedisClusterNode, 2000, 1000, config);
			for (int i = 0; i < 10000; i++) {
				//jc.set("name" + i , "is data " + i);
				System.out.println(jc.get("name" + i) );
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
