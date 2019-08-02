package io.renren.multi_thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author wangjiaqi
 * @create 2019/1/7
 * @since 1.0.0
 */
public class MtStack01<T> {

	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
		executor.execute(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (true){
					i = i+1;
					System.out.println("当前执行顺序:"+ i);
				}

			}
		});


	}
}
