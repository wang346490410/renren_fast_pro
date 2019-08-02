package io.renren.multi_thread;

/**
 * 〈多线程练习〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/3
 * @since 1.0.0
 */
public class Hero {
	public String name;
	public float hp;
	public int damage;
	public void attackHero(Hero h){
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		h.hp -= damage;
		System.out.format("%s 正在攻击 %s, %s血量变成了 %.0f%n",name,h.name,h.name,h.hp);
		if (h.isDead()) {
			System.out.println(h.name + "死了");

		}


	}

	public synchronized void recover(){
		if (hp == 1000) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		hp = hp+1;
		System.out.println(name+"回血一点,"+name + "回血后的血量为"+hp);
		this.notify();
	}

	public synchronized void hurt(){
		if (hp == 1) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		hp = hp -1 ;
		System.out.println(name+"减血一点,"+name + "减血后的血量为"+hp);
	}


	public boolean isDead() {
		return 0>=hp?true:false;
	}

}
