package io.renren.multi_thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/8
 * @since 1.0.0
 */
public class ThreadTest02 {

	public static String now(){
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	public static void main(String[] args) {
		final Object someObject = new Object();
		Thread t1 = new Thread(){
			@Override
			public void run() {
				try {
					System.out.println(now() + " t1 线程已经运行");
					System.out.println(now()+" "+this.getName()+" 试图占有对象： someobject");
					synchronized (someObject){
						System.out.println(now()+this.getName()+" 已经战友对象：someobject");
						Thread.sleep(5000);
						System.out.println(now()+this.getName()+" 释放对象:someobject");
					}
					System.out.println(now() + "t1 线程结束");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t1.setName("t1");
		t1.start();

		Thread t2 = new Thread(){
			@Override
			public void run() {
				try {
					System.out.println(now() + "t2 线程已经运行");
					System.out.println(now()+this.getName()+" 试图占有对象： someobject");
					synchronized (someObject){
						System.out.println(now()+this.getName()+" 已经战友对象：someobject");
						Thread.sleep(5000);
						System.out.println(now()+this.getName()+" 释放对象:someobject");
					}
					System.out.println(now() + "t2 线程结束");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t2.setName("t2");
		t2.start();
	}

}
