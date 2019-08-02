package io.renren.multi_thread;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/3
 * @since 1.0.0
 */
public class MyTest {

	public static void main(String[] args) {
		Hero gareen = new Hero();
		gareen.name = "盖伦";
		gareen.hp = 700;
		gareen.damage = 70;
		Hero teemo = new Hero();
		teemo.name = "提莫";
		teemo.hp = 650;
		teemo.damage = 60;
		Hero bf = new Hero();
		bf.name = "赏金猎人";
		bf.hp = 550;
		bf.damage = 65;
		Hero leesin = new Hero();
		leesin.name = "盲僧";
		leesin.hp = 550;
		leesin.damage = 70;

//		Battle battle = new Battle(gareen, teemo);

		Thread thread = new Thread(){
			@Override
			public void run() {
				while (!teemo.isDead()){
					gareen.attackHero(teemo);
				}
			}
		};


		Thread thread1 = new Thread(){
			@Override
			public void run() {
				while (!leesin.isDead()){
					bf.attackHero(leesin);
				}
			}
		};
		thread.setPriority(Thread.MAX_PRIORITY);
		thread1.setPriority(Thread.MAX_PRIORITY);
		thread.start();
		thread1.start();





	}
}
