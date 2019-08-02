package io.renren.modules.job.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.QuartzStateEnum;
import io.renren.common.utils.Query;
import io.renren.modules.job.dao.QuartzInfoMapper;
import io.renren.modules.job.entity.QuartzInfo;
import io.renren.modules.job.entity.QuartzInfoModel;
import io.renren.modules.job.service.QuartzInfoService;
import io.renren.modules.job.utils.QuartzManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

@Service("quartzInfoService")
public class QuartzInfoServiceImpl extends ServiceImpl<QuartzInfoMapper, QuartzInfo> implements QuartzInfoService {
//	@Autowired
//    private Scheduler scheduler;
	@Autowired
	private QuartzInfoMapper quartzInfoMapper;
	@Autowired
	private QuartzManager quartzManager;

	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		List<QuartzInfo> scheduleJobList = this.selectList(
				new EntityWrapper<QuartzInfo>().eq("state",QuartzStateEnum.ENABLED.getState())
		);
		System.err.println("监听器启动，初始化定时任务==========");
		System.err.println("当前定时任务条数=========="+scheduleJobList.size());
			for(QuartzInfo quartzInfo : scheduleJobList){
				String clName = quartzInfo.getClassName();
				Object cl = null;
				try {
					cl = Class.forName(clName).newInstance();
					QuartzManager.addJob(quartzInfo.getCode(), cl.getClass(), quartzInfo.getCycle());
				}  catch (Exception e) {
					e.printStackTrace();
				}
			}
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String beanName = (String)params.get("name");

		Page<QuartzInfo> page = this.selectPage(
				new Query<QuartzInfo>(params).getPage(),
				new EntityWrapper<QuartzInfo>().like(StringUtils.isNotBlank(beanName),"name", beanName)
		);

		return new PageUtils(page);
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(QuartzInfo quartzInfo) {
		quartzInfo.setCreateTime(new Date());
		quartzInfo.setState(QuartzStateEnum.ENABLED.getState());
        this.insert(quartzInfo);
    }
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(QuartzInfo scheduleJob) {
		QuartzInfo quartzInfo = selectById(scheduleJob.getId());
		String className = scheduleJob.getClassName();
		Object cl = null;
		try {
			cl = Class.forName(className).newInstance();
		}  catch (Exception e) {
			e.printStackTrace();
		}
		quartzManager.modifyJob(quartzInfo.getName(),scheduleJob.getName(),cl.getClass(),scheduleJob.getCycle());
                
        this.updateById(scheduleJob);
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
    	for(Long jobId : jobIds){
//    		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
			QuartzInfo quartzInfo = selectById(jobId);
			quartzManager.removeJob(quartzInfo.getCode());
    	}
    	
    	//删除数据
    	this.deleteBatchIds(Arrays.asList(jobIds));
	}

	@Override
    public int updateBatch(Long[] jobIds, int status){
    	Map<String, Object> map = new HashMap<>();
    	map.put("list", jobIds);
    	map.put("status", status);
    	return baseMapper.updateBatch(map);
    }
    
	@Override
	@Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
    	for(Long jobId : jobIds){
			QuartzInfo quartzInfo = selectById(jobId);
			quartzManager.triggerJob(quartzInfo.getCode());
    	}
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
			QuartzInfo quartzInfo = selectById(jobId);
			quartzManager.pauseJob(quartzInfo.getCode());
    	}
        
    	updateBatch(jobIds, Constant.ScheduleStatus.PAUSE.getValue());
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
    	for(Long jobId : jobIds){
			QuartzInfo quartzInfo = selectById(jobId);
			quartzManager.resumeJob(quartzInfo.getCode());
		}

    	updateBatch(jobIds, Constant.ScheduleStatus.NORMAL.getValue());
    }

	@Override
	public QuartzInfo getById(Long id) {
		return selectById(id);
	}

	@Override
	public List<QuartzInfoModel> listSelective(QuartzInfoModel condition) {
		List<QuartzInfoModel> quartzList = quartzInfoMapper.listSelective(condition);
		return quartzList;
	}

	@Override
	public QuartzInfo findByCode(String code) {
		QuartzInfo quartzInfo = selectOne(new EntityWrapper<QuartzInfo>().eq("code", code));
		return quartzInfo;
	}

}
