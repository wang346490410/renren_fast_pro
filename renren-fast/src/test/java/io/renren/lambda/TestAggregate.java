package io.renren.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/10
 * @since 1.0.0
 */
public class TestAggregate {

	public static void main(String[] args) {
		Random random = new Random();
		List<Hero> list = new ArrayList<>();
		for (int i=0;i<10;i++) {
			list.add(new Hero("hero "+i,random.nextInt(1000),random.nextInt(100)));
		}
//		list.add(list.get(0));
		System.out.println("初始化后的集合(最后一个重复)");
		System.out.println(list);
		System.out.println("满足条件hp>100&&damage<50的数据");
		list.stream().filter(
				hero -> hero.hp>100&&hero.damage<50
		).forEach(
				hero -> System.out.println(hero)
		);
		System.out.println("返回一个数组");
		Object[] hs = list.stream().toArray();
		System.out.println(Arrays.toString(hs));

		System.out.println("返回伤害最低的那个英雄");
		Hero minhero = list.stream().min(
				(h1, h2) -> h1.damage - h2.damage
		).get();
		System.out.println(minhero);

		System.out.println("返回伤害最高的那个英雄");
		Hero maxHero = list.stream().max(
				(h1, h2) -> h1.damage - h2.damage
		).get();
		System.out.println(maxHero);

		System.out.println("流中数据的总数");
		long count = list.stream().count();
		System.out.println(count);
		System.out.println("第一个英雄");

		Hero hero = list.stream().findFirst().get();
		System.out.println(hero);


//		System.out.println("去除重复的数据，去除标准是看equals");
//		list.stream().distinct().forEach(
//				hero -> System.out.println(hero)
//		);
//		System.out.println("按照血量排序");
//		list.stream().sorted(
//				(h1,h2)-> h1.hp>h2.hp?1:-1
//		).forEach(
//				hero -> System.out.println(hero)
//		);
//
//		System.out.println("保留三个");
//		list.stream().limit(3).forEach(
//				hero -> System.out.println(hero)
//		);
//		System.out.println("忽略前3个");
//		list.stream().skip(3).forEach(
//				hero -> System.out.println(hero)
//		);
//		System.out.println("转换为double的Stream");
//		list.stream().mapToDouble(
//				Hero::getHp
//		).forEach(h -> System.out.println(h)
//		);
//		System.out.println("转换任意类型的Stream");
//		list.stream().map(
//				hero -> hero.name+" - "+hero.hp+" - "+hero.damage
//		).forEach(
//				h -> System.out.println(h)
//		);





	}

}
