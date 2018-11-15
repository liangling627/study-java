package com.study;

import java.util.concurrent.ThreadLocalRandom;

public class Test {

	public static void main(String[] args) {
//		long[] arrayLong = new long[20000];
//		// 使用随机数赋值
//		Arrays.parallelSetAll(arrayLong, index -> ThreadLocalRandom.current().nextInt(100000));
//		// 查询前10个
//		Arrays.stream(arrayLong).limit(10).forEach(
//				i -> System.out.println(i+"")
//		);
//		// 对数组排序
//		Arrays.parallelSort(arrayLong);
//		// 取出排序后的前10个数
//		Arrays.stream(arrayLong).limit(10).forEach(
//				i -> System.out.println(i + "")
//		);
		for (int i = 0; i < 100; i++) {
			System.out.println(ThreadLocalRandom.current().nextInt(10));
			System.out.println("第一次提交");

		}



	}


}
