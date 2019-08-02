package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SupplierInfoEntity;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/16
 * @since 1.0.0
 */
public interface SupplierInfoService extends IService<SupplierInfoEntity> {
	PageUtils queryPage(Map<String, Object> params);
}
