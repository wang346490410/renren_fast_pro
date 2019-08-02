package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 供应商信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-01-16 09:25:25
 */
@TableName("t_supplier_info")
public class SupplierInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 供应商id
	 */
	@TableId
	private Long id;
	/**
	 * 供应商编码
	 */
	private String supplierCode;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 联系人
	 */
	private String linkman;
	/**
	 * 联系电话
	 */
	private String mobile;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 省
	 */
	private String provinceCode;
	/**
	 * 市
	 */
	private String cityCode;
	/**
	 * 区
	 */
	private String areaCode;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 状态(0,启用;1,停用;2,删除)
	 */
	private Integer enableFlag;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 创建人ID
	 */
	private Long creatorId;

	/**
	 * 设置：供应商id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：供应商id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：供应商编码
	 */
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	/**
	 * 获取：供应商编码
	 */
	public String getSupplierCode() {
		return supplierCode;
	}
	/**
	 * 设置：供应商名称
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	/**
	 * 获取：供应商名称
	 */
	public String getSupplierName() {
		return supplierName;
	}
	/**
	 * 设置：联系人
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	/**
	 * 获取：联系人
	 */
	public String getLinkman() {
		return linkman;
	}
	/**
	 * 设置：联系电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：联系电话
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：手机号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号码
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：省
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	/**
	 * 获取：省
	 */
	public String getProvinceCode() {
		return provinceCode;
	}
	/**
	 * 设置：市
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * 获取：市
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * 设置：区
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * 获取：区
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * 设置：详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：详细地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：状态(0,启用;1,停用;2,删除)
	 */
	public void setEnableFlag(Integer enableFlag) {
		this.enableFlag = enableFlag;
	}
	/**
	 * 获取：状态(0,启用;1,停用;2,删除)
	 */
	public Integer getEnableFlag() {
		return enableFlag;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：创建人ID
	 */
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 获取：创建人ID
	 */
	public Long getCreatorId() {
		return creatorId;
	}
}
