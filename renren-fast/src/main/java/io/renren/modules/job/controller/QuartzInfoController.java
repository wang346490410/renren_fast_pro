/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.renren.modules.job.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.QuartzStateEnum;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.job.entity.QuartzInfo;
import io.renren.modules.job.service.QuartzInfoService;
import io.renren.modules.job.utils.QuartzManager;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.2.0 2016-11-28
 */
@RestController
@RequestMapping("/sys/schedule")
public class QuartzInfoController {
	@Autowired
	private QuartzInfoService quartzInfoService;
	private static final Logger logger = LoggerFactory.getLogger(QuartzInfoController.class);
	
	/**
	 * 定时任务列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:schedule:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = quartzInfoService.queryPage(params);

		return R.ok().put("page", page);
	}
	
	/**
	 * 定时任务信息
	 */
	@GetMapping("/info/{jobId}")
	@RequiresPermissions("sys:schedule:info")
	public R info(@PathVariable("jobId") Long jobId){
		QuartzInfo schedule = quartzInfoService.selectById(jobId);
		
		return R.ok().put("schedule", schedule);
	}
	
	/**
	 * 保存定时任务
	 */
	@SysLog("保存定时任务")
	@PostMapping("/save")
	@RequiresPermissions("sys:schedule:save")
	public R save(@RequestBody QuartzInfo scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);

		quartzInfoService.save(scheduleJob);
		
		return R.ok();
	}
	
	/**
	 * 修改定时任务
	 */
	@SysLog("修改定时任务")
	@PostMapping("/update")
	@RequiresPermissions("sys:schedule:update")
	public R update(@RequestBody QuartzInfo scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);

		quartzInfoService.update(scheduleJob);
		
		return R.ok();
	}
	
	/**
	 * 删除定时任务
	 */
	@SysLog("删除定时任务")
	@PostMapping("/delete")
	@RequiresPermissions("sys:schedule:delete")
	public R delete(@RequestBody Long[] jobIds){
		quartzInfoService.deleteBatch(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@PostMapping("/run")
	@RequiresPermissions("sys:schedule:run")
	public R run(@RequestBody Long[] jobIds){
		boolean flag=true;
		Object cl=null;

		QuartzInfo quartzInfo = quartzInfoService.selectById(jobIds[0]);
		if (null == quartzInfo || StringUtils.isBlank(quartzInfo.getClassName())) {
			flag=false;
		}
		// 任务执行类实例化
		if (flag) {
			try {
				cl=Class.forName(quartzInfo.getClassName()).newInstance();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				logger.info("定时任务启动异常...", e);
				flag=false;
			}
		}
		if (flag) {
			// 任务添加
			QuartzManager.triggerJob(quartzInfo.getCode());
			// 数据库状态更新
			QuartzInfo quartzInfo1 = new QuartzInfo();
			quartzInfo1.setId(quartzInfo.getId());
			quartzInfo1.setState(QuartzStateEnum.ENABLED.getState());
			flag=quartzInfoService.update(quartzInfo,new EntityWrapper<>());
		}
		if (flag) {
			return R.ok();
		} else {
			return R.error("任务执行失败");
		}
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@PostMapping("/pause")
	@RequiresPermissions("sys:schedule:pause")
	public R pause(@RequestBody Long[] jobIds){
		quartzInfoService.pause(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@PostMapping("/resume")
	@RequiresPermissions("sys:schedule:resume")
	public R resume(@RequestBody Long[] jobIds){
		quartzInfoService.resume(jobIds);
		
		return R.ok();
	}

	public static void main(String[] args) {
		String ids = "3";
		String[] scheduleNos = ids.split(",");
		for (String scheduleId : scheduleNos) {
			System.out.println(scheduleId);

		}
	}

}
