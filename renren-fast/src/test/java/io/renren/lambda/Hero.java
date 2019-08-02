package io.renren.lambda;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/10
 * @since 1.0.0
 */
public class Hero implements Comparable<Hero>{

	public String name;
	public float hp;
	public int damage;

	public Hero(){

	}
	public Hero(String name){
		this.name = name;
	}
	public Hero(String name,float hp,int damage){
		this.name = name;
		this.hp = hp;
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getHp() {
		return hp;
	}

	public void setHp(float hp) {
		this.hp = hp;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public int compareTo(Hero anotherHero) {
		if (damage < anotherHero.damage) {
			return 1;
		}
		return -1;
	}

	@Override
	public String toString() {
		return "Hero{" +
				"name='" + name + '\'' +
				", hp=" + hp +
				", damage=" + damage +
				'}';
	}
}
