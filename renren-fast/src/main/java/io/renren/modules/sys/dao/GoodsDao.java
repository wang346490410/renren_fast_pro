package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.entity.GoodsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-01-03 10:56:57
 */
@Mapper
public interface GoodsDao extends BaseMapper<GoodsEntity> {
	
}
