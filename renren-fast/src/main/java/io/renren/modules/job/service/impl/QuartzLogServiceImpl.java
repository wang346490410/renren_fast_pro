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

package io.renren.modules.job.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.job.dao.QuartzLogDao;
import io.renren.modules.job.entity.QuartzLog;
import io.renren.modules.job.service.QuartzLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("scheduleJobLogService")
public class QuartzLogServiceImpl extends ServiceImpl<QuartzLogDao, QuartzLog> implements QuartzLogService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String jobId = (String)params.get("quartzId");

		Page<QuartzLog> page = this.selectPage(
				new Query<QuartzLog>(params).getPage(),
				new EntityWrapper<QuartzLog>().like(StringUtils.isNotBlank(jobId),"quartz_id", jobId)
		);

		return new PageUtils(page);
	}

	@Override
	public void save(QuartzLog quartzLog) {
		insert(quartzLog);
	}

}
