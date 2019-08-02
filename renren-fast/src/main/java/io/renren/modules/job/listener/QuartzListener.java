package io.renren.modules.job.listener;

import io.renren.common.utils.QuartzStateEnum;
import io.renren.modules.job.entity.QuartzInfoModel;
import io.renren.modules.job.service.QuartzInfoService;
import io.renren.modules.job.utils.QuartzManager;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.List;

public class QuartzListener extends ContextLoaderListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        QuartzInfoService quartzInfoService = (QuartzInfoService) webApplicationContext.getBean("quartzInfoService");

        // 查询启用状态的定时任务信息
        QuartzInfoModel condition = new QuartzInfoModel();
        condition.setState(QuartzStateEnum.ENABLED.getState());
        List<QuartzInfoModel> list = quartzInfoService.listSelective(condition);
        System.err.println("监听器启动，初始化定时任务==========");
        System.err.println("当前定时任务条数=========="+list.size());


        // 循环添加任务
        for (QuartzInfoModel quartzInfo : list) {
            try {
                String clName = quartzInfo.getClassName();
                Object cl = Class.forName(clName).newInstance();
                QuartzManager.addJob(quartzInfo.getCode(), cl.getClass(), quartzInfo.getCycle());
            } catch (Exception e) {
            	e.printStackTrace();
                continue;
            }
        }
        // 启动所有定时任务
        QuartzManager.startJobs();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }

}
