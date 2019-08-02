package io.renren.modules.job.task;

import io.renren.common.utils.QuartzResultEnum;
import io.renren.modules.job.utils.QuartzInfoEnum;
import io.renren.modules.sys.entity.GoodsEntity;
import io.renren.modules.sys.service.GoodsService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 〈新增商品定时任务〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/17
 * @since 1.0.0
 */
@Component
public class QuartzAddGoods extends BaseJobBean{
	@Override
	public String job() {
		GoodsService goodsService = context.getBean(GoodsService.class);
		GoodsEntity entity = new GoodsEntity();
		String goodsName = randomGoodsName();
		if (goodsName.equals("三星") || goodsName.equals("苹果")) {
			entity.setIntro("进口手机");
		}else{
			entity.setIntro("国产手机");
		}
		entity.setName(randomGoodsName());
		entity.setNum((int)(Math.random()*100));
		entity.setPrice(new BigDecimal("9999.22"));
		entity.setPicUrl(randomPicUrl());
		goodsService.insert(entity);
		return QuartzResultEnum.SUCCESS.getDesc();
	}

	public String randomPicUrl() {
		String[] strs = {
				"https://wangjiaqibucket.oss-cn-beijing.aliyuncs.com/test/20190114/0be8e31dc5104f12ad0eec72da2d5bd4.jpg",
				"https://wangjiaqibucket.oss-cn-beijing.aliyuncs.com/test/20190114/fd9fe776368d4d5e91a8cd17abc2d6b0.jpg",
				"https://wangjiaqibucket.oss-cn-beijing.aliyuncs.com/test/20190114/e07b7ddb388a4871b512758630dcfc70.jpg"
		};
		int index = (int) (Math.random()*strs.length);
		return strs[index];
	}
	public String randomGoodsName() {
		String[] strs = {
				"三星","华为","苹果","小米","荣耀"
		};
		int index = (int) (Math.random()*strs.length);
		return strs[index];
	}

	@Override
	public QuartzInfoEnum setQuartzInfoEnum() {
		return QuartzInfoEnum.ADD_GOODS;
	}
}
