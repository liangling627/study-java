package com.study;

public class Test {
	static final Object object = new Object();

	public static void main(String[] args) {

		PrintLetter printLetter = new PrintLetter();
		Thread thread1 = new Thread(printLetter);
		thread1.start();
		PrintNum printNum = new PrintNum();
		Thread thread2 = new Thread(printNum);
		thread2.start();







	}

	static class PrintNum implements Runnable {
		int[] a = {1, 2, 3, 4, 5};
		@Override
		public void run() {
			for (int i = 0; i <= a.length; i++) {
				try {
					synchronized (object) {
						System.out.println(a[i]);
						object.notify();

						object.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}


		}
	}

	static class PrintLetter implements Runnable {
		String[] a = {"A", "B", "C", "D", "E"};
		@Override
		public void run() {
			for (int i = 0; i < a.length; i++) {
				try {
					synchronized (object) {
						System.out.println(a[i]);
						object.notify();
						object.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}


		}
	}


}






