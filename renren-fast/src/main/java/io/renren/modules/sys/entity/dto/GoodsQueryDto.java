package io.renren.modules.sys.entity.dto;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/22
 * @since 1.0.0
 */
public class GoodsQueryDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
