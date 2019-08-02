package io.renren.modules.job.utils;

import io.renren.common.utils.DateUtils;
import io.renren.common.utils.QuartzResultEnum;
import io.renren.modules.job.entity.QuartzInfo;
import io.renren.modules.job.entity.QuartzLog;
import io.renren.modules.job.service.QuartzInfoService;
import io.renren.modules.job.service.QuartzLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wangjiaqi
 * @descripition 定时任务添加日志工具类
 * @create 2018-01-23 18:30
 **/
@Component
public class QuartzLogUtils {

    @Autowired
    private QuartzInfoService quartzInfoService;
    @Autowired
    private QuartzLogService quartzLogService;

    public void saveOrUpdateQuartzLogInfo(Date startDate , String result, QuartzInfoEnum quartzInfoEnum,String remark) {
        //获取定时任务信息
        QuartzInfo quartzInfo = quartzInfoService.findByCode(quartzInfoEnum.getCode());
//        QuartzInfoModel quartzInfoModel = new QuartzInfoModel();
//        quartzInfoModel.setId(quartzInfo.getId());
        //记录日志
        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setQuartzId(quartzInfo.getId());
        quartzLog.setTime((int)(DateUtils.getNow().getTime() - startDate.getTime()));
        quartzLog.setResult(result);
        quartzLog.setStartTime(startDate);

        //执行失败
        if (result.equals(QuartzResultEnum.FAILURE.getValue())) {
            quartzInfo.setFail(quartzInfo.getFail() + 1);
        }else{
            quartzInfo.setSucceed(quartzInfo.getSucceed() + 1);
        }
        if(StringUtils.isBlank(remark)){
            remark = result.equals(QuartzResultEnum.FAILURE.getValue()) ? QuartzResultEnum.FAILURE.getDesc()
                    : QuartzResultEnum.SUCCESS.getDesc();
        }
        quartzLog.setRemark(remark);
        quartzLogService.save(quartzLog);
        quartzInfoService.update(quartzInfo);
    }

    public void saveOrUpdateQuartzLogInfo(Date startDate , String result, QuartzInfoEnum quartzInfoEnum) {
        saveOrUpdateQuartzLogInfo(startDate, result, quartzInfoEnum, null);
    }
}
