package io.renren.multi_thread;

/**
 * 〈一句话功能简述〉<br>
 * 〈生产者〉
 *
 * @author wangjiaqi
 * @create 2019/1/4
 * @since 1.0.0
 */
public class ProducerThread extends Thread{
	private MyStack<Character> stack ;

	public ProducerThread(MyStack<Character> stack, String name){
		super(name);
		this.stack = stack;
	}

	@Override
	public void run() {
		while(true){
			char c = randomChar();
			System.out.println(this.getName() + "压入"+c);
			stack.push(c);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public char randomChar() {
		return (char) (Math.random()*('Z'+1-'A') + 'A');
	}

	public static void main(String[] args) {
		MyStack<Character> stack = new MyStack<>();
		new ProducerThread(stack,"producer1").start();
		new ProducerThread(stack,"producer2").start();
		new ConsumerThread(stack,"consumer1").start();
		new ConsumerThread(stack,"consumer2").start();
		new ConsumerThread(stack,"consumer3").start();
	}

}
