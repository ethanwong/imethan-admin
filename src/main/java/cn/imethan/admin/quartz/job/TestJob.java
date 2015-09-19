package cn.imethan.admin.quartz.job;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.imethan.common.utils.DateUtil;

/**
 * TestJob.java
 *
 * @author Ethan Wong
 * @time 2015年9月19日下午3:36:40
 */
@DisallowConcurrentExecution
public class TestJob extends QuartzJobBean {
	
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
    	
    	System.out.println("[TestJob]Sleep 10s begin date:"+DateUtil.getDatetimeStr(DateUtil.DATE_PATTERN_01));
    	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	System.out.println("[TestJob]Sleep 10s end   date:"+DateUtil.getDatetimeStr(DateUtil.DATE_PATTERN_01));
    }
    
}
