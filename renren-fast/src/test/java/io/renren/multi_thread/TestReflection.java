package io.renren.multi_thread;

import java.lang.reflect.Constructor;

/**
 * 〈一句话功能简述〉<br>
 * 〈反射练习〉
 *
 * @author wangjiaqi
 * @create 2019/1/4
 * @since 1.0.0
 */
public class TestReflection {
	public static void main(String[] args) {
		Hero gareen = new Hero();
		gareen.name = "盖伦";
		System.out.println(gareen);

		try {
			Class pClass = Class.forName("io.renren.multi_thread.Hero");
			Constructor constructor = pClass.getConstructor();
			Hero hero = (Hero) constructor.newInstance();
			hero.name = "提莫";
			System.out.println(hero);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
