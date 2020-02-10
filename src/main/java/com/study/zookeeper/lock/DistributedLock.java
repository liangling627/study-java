package com.study.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 分布式锁
 * @author liangyuanbing
 *
 */
public class DistributedLock implements Lock, Watcher {
	private ZooKeeper zk = null;
	private String root = "/locks"; 	//  根
	private String lockName;			// 竞争资源的标志
	private String waitNode;			//等待前一个锁
	private String myZnode;			// 当前锁
	
	private CountDownLatch latch;	// 计数器
	private int sessionTimeout = 5000;
	private boolean isGetLock = false;
	public boolean isGetLock() {
		return isGetLock;
	}

	public void setGetLock(boolean isGetLock) {
		this.isGetLock = isGetLock;
	}


	private DistributedLock(){}
	
	/**
	 * 创建分布式锁,使用前请确认config配置的zookeeper服务可用
	 * @param lockName 竞争资源标志,lockName中不能包含单词lock
	 */
	private DistributedLock(String lockName) {
		this.lockName = lockName;
		try {
			zk = initZK();
			Stat stat = zk.exists(root, false);
			if (stat == null) {
				zk.create(root, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private ZooKeeper initZK() {
		try {
			if (zk == null) {
				zk = new ZooKeeper("192.168.192.111:2181", sessionTimeout, this);
			}
		} catch (IOException e) {
			throw new LockException("zk init connect fail" + e.getMessage());
		}
		return zk;
	}

	public static DistributedLock instanceLock(String lockName) {
		return new DistributedLock(lockName);
	}
	

	/**
	 * zookeeper节点的监视器
	 */
	@Override
	public void process(WatchedEvent event) {
		if (this.latch != null) {
			this.latch.countDown();
		}
	}

	@Override
	public void lock() {
		try {
			if (this.tryLock()) {
			    return ;
			} else {
				// wait lock
				waitForLock(waitNode, sessionTimeout);
			}
		} catch (KeeperException | InterruptedException e) {
			throw new LockException(e);
		}
	}


	@Override
	public void lockInterruptibly() throws InterruptedException {
		this.lock();
	}

	@Override
	public boolean tryLock() {
		try {
			String splitStr = "_lock_";
			if (lockName.contains(splitStr)) {
				throw new LockException("lockName can not contains \\u000B");
			}
			// 创建零时子节点
			myZnode = zk.create(root + "/" + lockName + splitStr, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			//取出所有子节点
			List<String> subNodes = zk.getChildren(root, false);
			List<String> lockObjNodes = new ArrayList<>();
			//取出所有lockName的锁
			for (String node : subNodes) {
				String _node = node.split(splitStr)[0];
				if (_node.equals(lockName)) {
					lockObjNodes.add(node);
				}
			}
			Collections.sort(lockObjNodes);
			if (myZnode.equals(root + "/" + lockObjNodes.get(0))) {
				//如果是最小的节点,则表示取得锁
				return true;
			}
			//如果不是最小的节点，找到比自己小1的节点
			String subMyZnode = myZnode.substring(myZnode.lastIndexOf("/") + 1);
			waitNode = lockObjNodes.get(Collections.binarySearch(lockObjNodes, subMyZnode) - 1);
		} catch (KeeperException | InterruptedException e) {
			throw new LockException(e);
		}
		return false;
	}

	@SuppressWarnings("finally")
	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		try {
			if (this.tryLock()) {
				return true;
			}
			return waitForLock(waitNode, time);
		} catch (Exception e) {
			throw new LockException(e);
		} finally {
			return false;
		}
	}
	
	private boolean waitForLock(String lower, long waitTime) throws KeeperException, InterruptedException {
		//判断比自己小一个数的节点是否存在,如果不存在则无需等待锁,同时注册监听
		Stat stat = zk.exists(root + "/" + lower, true);
		if (stat != null) {
			this.latch = new CountDownLatch(1);
			isGetLock = this.latch.await(waitTime, TimeUnit.MILLISECONDS);
			this.latch = null;
		}
		return true;
	}

	@Override
	public void unlock() {
		try {
			zk.delete(myZnode, -1);
			myZnode = null;
		} catch (InterruptedException | KeeperException e) {
			throw new LockException(e);
		}
	}
	

	@Override
	public Condition newCondition() {
		return null;
	}

}
