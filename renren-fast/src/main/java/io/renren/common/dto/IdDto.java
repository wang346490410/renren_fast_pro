package io.renren.common.dto;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author wangjiaqi
 * @create 2019/1/17
 * @since 1.0.0
 */
public class IdDto implements Serializable{

	private static final long serialVersionUID = -1L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
