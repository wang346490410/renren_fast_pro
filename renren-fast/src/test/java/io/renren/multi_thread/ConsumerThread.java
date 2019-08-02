package io.renren.multi_thread;

import io.renren.multi_thread.MyStack;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/4
 * @since 1.0.0
 */
public class ConsumerThread extends Thread{

	private MyStack<Character> stack;

	public ConsumerThread(MyStack<Character> stack,String name){
		super(name);
		this.stack = stack;
	}

	@Override
	public void run() {
		while (true){
			char c = stack.pull();
			System.out.println(this.getName() +"弹出"+c);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


	}
}
