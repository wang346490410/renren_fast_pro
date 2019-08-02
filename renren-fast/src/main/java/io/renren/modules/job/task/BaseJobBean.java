package io.renren.modules.job.task;

import io.renren.common.utils.DateUtils;
import io.renren.common.utils.QuartzResultEnum;
import io.renren.modules.job.utils.QuartzInfoEnum;
import io.renren.modules.job.utils.QuartzLogUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务抽象类
 * @author wangjiaqi
 * @Date 2018-01-29 16:03
 */
@Component
public abstract class BaseJobBean implements Job,ApplicationContextAware {
    public static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    /**
     * @description: 执行任务
     * @param:
     * @author:  wangjiaqi
     * @date 2018/1/29 0029 17:09
     */
    public abstract String job();

    /**
     * @description: 任务枚举
     * @param:
     * @author:  wangjiaqi
     * @date 2018/1/29 0029 17:12
     */
    public abstract QuartzInfoEnum setQuartzInfoEnum();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        QuartzInfoEnum quartzInfoEnum = setQuartzInfoEnum();
//        String desc = quartzInfoEnum.getDesc();
        Date startDate = DateUtils.getNow();
        String result = QuartzResultEnum.SUCCESS.getValue();
//        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        QuartzLogUtils quartzLogUtils = context.getBean(QuartzLogUtils.class);

        String remark = null;
        try {
             remark = job();
        } catch (Exception e) {
            e.printStackTrace();
            result = QuartzResultEnum.FAILURE.getValue();
        } finally {
            quartzLogUtils.saveOrUpdateQuartzLogInfo(startDate, result, quartzInfoEnum,remark);
        }
    }

}
