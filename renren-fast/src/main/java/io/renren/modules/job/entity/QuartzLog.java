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

package io.renren.modules.job.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.2.0 2016-11-28
 */
@TableName("quartz_log")
public class QuartzLog implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 日志id
	 */
	@TableId
	private Long id;
	
	/**
	 * 任务id
	 */
	private Long quartzId;
	


	/**
	 * 执行是否成功 10-成功 20-失败
	 */
	private String result;
	/**
	 *备注信息
	 */
	private String remark;

	/**
	 * 耗时(单位：毫秒)
	 */
	private Integer time;
	
	/**
	 * 启动时间
	 */
	private Date startTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuartzId() {
		return quartzId;
	}

	public void setQuartzId(Long quartzId) {
		this.quartzId = quartzId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}
