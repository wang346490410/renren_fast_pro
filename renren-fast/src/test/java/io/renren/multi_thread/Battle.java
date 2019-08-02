package io.renren.multi_thread;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/3
 * @since 1.0.0
 */
public class Battle implements Runnable{

	private Hero h1;
	private Hero h2;

	public Battle(Hero h1, Hero h2) {
		this.h1 = h1;
		this.h2 = h2;
	}

	@Override
	public void run() {
		while (!h2.isDead()){
			h1.attackHero(h2);
		}

	}
}
