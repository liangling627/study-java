package com.study.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarutorDemo {
	
	private static final String base = "/zk/test";
	private static final String path = "/zk/test/07";

	public static void main(String[] args) throws Exception {
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString("192.168.192.111:2181")
				.sessionTimeoutMs(5000)
				.connectionTimeoutMs(3000)
				.retryPolicy(new ExponentialBackoffRetry(1000, 3))
				.build();
		client.start();
		
		
		String s = client.create().creatingParentsIfNeeded().forPath(path, "hello".getBytes());
		System.out.println(s);
		
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		/**
         * 监听数据节点的变化情况
         */
		final NodeCache nodeCache = new NodeCache(client, path, false);
		nodeCache.start();
		nodeCache.getListenable().addListener(new NodeCacheListener() {
			
			@Override
			public void nodeChanged() throws Exception {
				System.out.println("node is changed , new data " + new String(nodeCache.getCurrentData().getData()));
			}
		}, pool);
		
		/**
         * 监听子节点的变化情况
         */
		
		final PathChildrenCache childrenCache= new PathChildrenCache(client, base, true);
		childrenCache.start(StartMode.POST_INITIALIZED_EVENT);
		childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
			
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				// TODO Auto-generated method stub
				switch (event.getType()) {
				case CHILD_ADDED:
					System.out.println("CHILD_ADDED: " + event.getData().getPath());
                    break;
				case CHILD_REMOVED:
					System.out.println("CHILD_REMOVED: " + event.getData().getPath());
					break;
				case CHILD_UPDATED:
					System.out.println("CHILD_UPDATED: " + event.getData().getPath());
					break;

				default:
					break;
				}
			}
		});
		
		System.out.println("new data1 " + new String(client.getData().forPath(path)));
		
		client.setData().forPath(path, "word".getBytes());
		
		Thread.sleep(10* 1000);
		
		System.out.println("new data2 " + new String(client.getData().forPath(path)));
		
		
		
		Thread.sleep(10* 1000);
		
		client.setData().forPath(path, "word +1".getBytes());
		
		System.out.println("new data3  " + new String(client.getData().forPath(path)));
		
		
		Thread.sleep(10* 1000);
		String p = path + "/test";
		client.create().creatingParentsIfNeeded().forPath(p, "".getBytes());
		client.setData().forPath(p, "hello word".getBytes());
		System.out.println("new data4  " + new String(client.getData().forPath(p)));
		
		
		pool.shutdown();
		client.close();
		
	}

}
