package io.renren.modules.sys.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.rabbitmq.config.Sender;
import io.renren.modules.sys.entity.SupplierInfoEntity;
import io.renren.modules.sys.service.SupplierInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 〈〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/16
 * @since 1.0.0
 */
@RestController
@RequestMapping("generator/supplierInfo")
public class SupplierInfoController {

	@Autowired
	SupplierInfoService supplierInfoService;
	@Autowired
	Sender sender;
	public static final String QUEUE_ROUTING_KEY = "supplierQueue";


	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresPermissions("generator:supplierInfo:list")
	public R list(@RequestParam Map<String,Object> params){
		PageUtils pageUtils = supplierInfoService.queryPage(params );
		return R.ok().put("page",pageUtils);
	}

	/**
	 * 信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions(("generator:supplierInfo:info"))
	public R info(@PathVariable("id") long id){
		SupplierInfoEntity supplierInfoEntity = supplierInfoService.selectById(id);
		return R.ok().put("supplierInfoEntity",supplierInfoEntity);
	}

	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	@RequestMapping("/save")
	@RequiresPermissions("generator:supplierInfo:save")
	public R save(@RequestBody SupplierInfoEntity entity){
		boolean isTrue = supplierInfoService.insert(entity);
		if (isTrue) {
			sender.send(QUEUE_ROUTING_KEY,entity.getId().toString());
		}
		return R.ok();
	}

	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:supplierInfo:update")
	public R update(@RequestBody SupplierInfoEntity entity){
		supplierInfoService.updateById(entity);
		return R.ok();
	}








}
