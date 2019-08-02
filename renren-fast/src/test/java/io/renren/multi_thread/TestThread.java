package io.renren.multi_thread;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/4
 * @since 1.0.0
 */
public class TestThread {

	public static void main(String[] args) {

		final Hero a = new Hero();
		final Hero b = new Hero();
		final Hero c = new Hero();

		Thread t1 = new Thread() {
			@Override
			public void run() {
				synchronized (a) {
					System.out.println(this.getName() + "占有了a");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("t1试图占有 b");
					System.out.println("t1 等待中、、、");
					synchronized (b) {
						System.out.println("占有b ,do something");
					}
					System.out.println("t1试图占有 c");
					System.out.println("t1 等待中、、、");
					synchronized (b) {
						System.out.println("t1占有c ,do something");
					}
				}
			}
		};
		t1.start();

		Thread t2 = new Thread() {
			@Override
			public void run() {
				synchronized (b) {
					System.out.println(this.getName() + "已经占有b");

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("t2 试图占有a");
					System.out.println("t2 等待中、、、");
					synchronized (a) {
						System.out.println("占有a,do something");
					}
					System.out.println("t2 试图占有c");
					System.out.println("t2 等待中、、、");
					synchronized (a) {
						System.out.println("t2占有c,do something");
					}
				}
			}
		};
		t2.start();

		Thread t3 = new Thread(){
			@Override
			public void run() {
				synchronized (c){
					System.out.println(this.getName() + "已经占有c");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("t3 试图占有a");
					System.out.println("t3 等待中、、、");
					synchronized (a) {
						System.out.println("占有a,do something");
					}
					System.out.println("t3 试图占有c");
					System.out.println("t3 等待中、、、");
					synchronized (b) {
						System.out.println("t3占有b,do something");
					}

				}
			}
		};
		t3.start();


	}

}
