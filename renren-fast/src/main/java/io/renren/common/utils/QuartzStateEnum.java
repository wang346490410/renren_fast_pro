package io.renren.common.utils;


/**
 * @Discription
 * @Date Create in 2018/1/11 17:09
 * @Author wdl
 * @Modified by:
 */
public enum QuartzStateEnum {

    ENABLED("10", "启用"),
    DISABLED("20", "禁用"),
    ;

    private String state;

    private String msg;

    QuartzStateEnum(String state, String msg) {
        this.state=state;
        this.msg=msg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state=state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg=msg;
    }
}
