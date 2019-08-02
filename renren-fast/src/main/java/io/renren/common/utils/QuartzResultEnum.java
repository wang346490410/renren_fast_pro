package io.renren.common.utils;

/**
 * @Discription
 * @Date Create in 2018/1/11 17:23
 * @Author wdl
 * @Modified by:
 */
public enum QuartzResultEnum {

    SUCCESS("10", "执行成功"),
    FAILURE("20", "执行失败"),
    ;

    private String value;

    private String desc;

    QuartzResultEnum(String value, String desc) {
        this.value=value;
        this.desc=desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value=value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc=desc;
    }
}
