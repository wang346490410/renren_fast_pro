package io.renren.modules.job.utils;

/**
 * @author wangjiaqi
 * @descripition 跑批枚举
 * @create 2018-01-29 16:19
 **/
public enum QuartzInfoEnum {

    LOAN_RESULT("loanResult", "放款查询"),
    OPE_AUTHING_USER("opeAuthingUser", "处理运营商认证中的用户的状态"),
    EMAIL_AUTHING_USER("emailAuthingUser", "处理信用卡邮箱认证中的用户状态"),
    TAOBAO_AUTHING_USER("taobaoAuthingUser", "处理淘宝认证中的用户的状态"),
    DO_LATE("doLate", "逾期计算"),
    SYSTEM_DUE_TIME_DEDUCTIONS("systemDueTimeDeductions", "还款日代扣"),
    SYSTEM_OVER_DUE_DEDUCTIONS("systemOverDueDeductions", "逾期代扣"),
    SIGN_PDF("signPDF", "协议盖章生成"),
    PLAN_SHIFT("planShift", "当期切换"),
    QUERY_REPAYMENT_RESULT("queryRepaymentResult","还款查询"),
    REPAYMENT_SMS_INFORM("repaymentSmsInform","还款短信提醒"),
    QUARTZ_DATA_LOG("dataLog", "埋点消费"),
    WEIXIN_DATA_PUSH("weixinDataPush","微信数据推送"),
    SYSTEM_DEDUCT_FAIL_SMS("systemDeductFailSms", "代扣失败短信提醒"),
    ORDER_LOAN_PROCESS("orderLoanProcess", "风控跑批"),
    QUARTZ_TEST("quartzTest","测试定时任务"),
    STATE_EXPIRE("stateExpire", "认证状态自动过期"),
    THE_NEXT_DAY_AUTO_AUDIT("theNextDayAutoAudit","第二天自动进入渠道进行审核"),
    CHANNEL_DISCOUNT("chnannelDiscount","创建明天渠道折扣跑批"),
    CHANNEL_DATA("channelData","渠道数据跑批"),
    ADD_GOODS("quartzAddGoods","自动添加商品信息"),
	CHECK_ACCOUNT("check_account","昨日的对账数据跑批");
    private String code;
    private String desc;
    QuartzInfoEnum(String code, String desc){
        this.code=code;
        this.desc=desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


}
