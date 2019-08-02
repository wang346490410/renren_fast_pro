package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SupplierInfoDao;
import io.renren.modules.sys.entity.SupplierInfoEntity;
import io.renren.modules.sys.service.SupplierInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/16
 * @since 1.0.0
 */
@Service("supplierInfoService")
public class SupplierInfoServiceImpl extends ServiceImpl<SupplierInfoDao,SupplierInfoEntity> implements SupplierInfoService{
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<SupplierInfoEntity> page = selectPage(new Query<SupplierInfoEntity>(params).getPage(),new EntityWrapper<SupplierInfoEntity>());
		return new PageUtils(page);
	}
}
