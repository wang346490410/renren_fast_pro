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

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.2.0 2016-11-28
 */
@TableName("quartz_info")
public class QuartzInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 任务调度参数key
	 */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";
	
	/**
	 * 任务id
	 */
	@TableId
	private Long id;

	/**
	 * spring bean名称
	 */
	@NotBlank(message="定时任务名称不能为空")
	private String name;
	
	/**
	 * 方法名
	 */
	@NotBlank(message="定时任务code标识不能为空")
	private String code;
	
	/**
	 * 定时任务执行类
	 */
	private String className;

	/**
	 * cron表达式
	 */
	@NotBlank(message="cron表达式不能为空")
	private String cycle;

	/**
	 * 成功执行次数
	 */
	private Integer succeed;
	/**
	 * 失败执行次数
	 */
	private Integer fail;
	/**
	 * 是否启用 10-启用 20-禁用
	 */
	private String state;


	/**
	 * 创建时间
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public Integer getSucceed() {
		return succeed;
	}

	public void setSucceed(Integer succeed) {
		this.succeed = succeed;
	}

	public Integer getFail() {
		return fail;
	}

	public void setFail(Integer fail) {
		this.fail = fail;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
