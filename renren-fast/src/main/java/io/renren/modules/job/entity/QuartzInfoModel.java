package io.renren.modules.job.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Discription
 * @Date Create in 2017/12/18 14:09
 * @Author wdl
 * @Modified by:
 */
public class QuartzInfoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 定时任务名称
     */
    private String name;

    /**
     * 定时任务标识
     */
    private String code;

    /**
     * 定时任务执行周期
     */
    private String cycle;

    /**
     * 定时任务执行类
     */
    private String className;

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

    /**
     * 任务状态描述
     */
    private String stateStr;

    /**
     * 上次执行时间
     */
    private Date lastStartTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code=code;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle=cycle;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className=className;
    }

    public Integer getSucceed() {
        return succeed;
    }

    public void setSucceed(Integer succeed) {
        this.succeed=succeed;
    }

    public Integer getFail() {
        return fail;
    }

    public void setFail(Integer fail) {
        this.fail=fail;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state=state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime=createTime;
    }

    public String getStateStr() {
        return stateStr;
    }

    /**
     * 设置任务状态描述
     * @param stateStr
     */
    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }

    /**
     * 获取上次执行时间
     *
     * @return lastStartTime
     */
    public Date getLastStartTime() {
        return lastStartTime;
    }

    /**
     * 设置上次执行时间
     *
     * @param lastStartTime
     */
    public void setLastStartTime(Date lastStartTime) {
        this.lastStartTime = lastStartTime;
    }

    /**
     * 状态中文转换
     * @return
     */
//    public void stateConvert() {
//        if (QuartzStateEnum.ENABLED.getState().equals(state)) {
//            stateStr = "启用";
//        } else {
//            stateStr = "禁用";
//        }
//    }
}
