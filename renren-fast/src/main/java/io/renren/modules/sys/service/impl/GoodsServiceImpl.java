package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.GoodsDao;
import io.renren.modules.sys.entity.GoodsEntity;
import io.renren.modules.sys.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsEntity> implements GoodsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GoodsEntity> page = this.selectPage(
                new Query<GoodsEntity>(params).getPage(),
                new EntityWrapper<GoodsEntity>()
        );

        return new PageUtils(page);
    }

}
