package cn.imethan.admin.quartz.dynamic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * DynamicQuartzJobFactory.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月21日下午5:32:50
 */
@Component
@Transactional//必须添加事务注释，否则任务没办法持久化到数据库
public class DynamicQuartzJobFactory {
	
	@Autowired
	private SchedulerFactoryBean quartzScheduler;//将配置文件的注册的bean注入
	
	/**
	 * 获取任务调度
	 * @return
	 *
	 * @author Ethan Wong
	 * @datetime 2015年12月23日上午11:23:49
	 */
	private Scheduler getScheduler(){
		return quartzScheduler.getScheduler();
	}
	
	/**
	 * 准备任务数据
	 * @return
	 *
	 * @author Ethan Wong
	 * @datetime 2015年12月23日上午11:23:41
	 */
	private List<QuartzJob> getAllJob(){
		List<QuartzJob> list = new ArrayList<QuartzJob>();
		for (int i = 0; i < 5; i++) {
			QuartzJob job = new QuartzJob();
			job.setJobId("10001" + i);
			job.setJobName("TESTJOB" + i);
			job.setJobGroup("DEFAULT");
			job.setJobStatus("1");
			job.setCronExpression("0/5 * * * * ?");
			job.setDescription("测试任务");
			list.add(job);
		}
		return list;
	}
	
	/**
	 * 测试入口
	 *
	 * @author Ethan Wong
	 * @datetime 2015年12月23日上午11:24:11
	 */
	public void testOne(){
		try {
			List<QuartzJob> jobList = getAllJob();//这里获取任务信息数据
			for (QuartzJob job : jobList) {
				this.addJob(job.getJobName(), job.getJobGroup(), job.getCronExpression());
//				this.deleteJob(job.getJobName(), job.getJobGroup());
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取计划中的任务列表
	 * @return
	 * @throws SchedulerException
	 *
	 * @author Ethan Wong
	 * @datetime 2015年12月23日上午10:55:44
	 */
	public List<QuartzJob> getTriggersOfJob() throws SchedulerException{
		Scheduler scheduler = this.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<QuartzJob> jobList = new ArrayList<QuartzJob>();
		for (JobKey jobKey : jobKeys) {
		    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
		    for (Trigger trigger : triggers) {
		    	QuartzJob job = new QuartzJob();
		        job.setJobName(jobKey.getName());
		        job.setJobGroup(jobKey.getGroup());
		        job.setDescription("触发器:" + trigger.getKey());
		        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
		        job.setJobStatus(triggerState.name());
		        if (trigger instanceof CronTrigger) {
		            CronTrigger cronTrigger = (CronTrigger) trigger;
		            String cronExpression = cronTrigger.getCronExpression();
		            job.setCronExpression(cronExpression);
		        }
		        jobList.add(job);
		    }
		}
		
		return jobList;
	}
	

	/**
	 * 获取正在执行的任务列表
	 * @return
	 * @throws SchedulerException
	 *
	 * @author Ethan Wong
	 * @datetime 2015年12月23日上午10:56:02
	 */
	public List<QuartzJob> getCurrentlyExecutingJobs() throws SchedulerException{
		Scheduler scheduler = this.getScheduler();
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<QuartzJob> jobList = new ArrayList<QuartzJob>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			QuartzJob job = new QuartzJob();
		    JobDetail jobDetail = executingJob.getJobDetail();
		    JobKey jobKey = jobDetail.getKey();
		    Trigger trigger = executingJob.getTrigger();
		    job.setJobName(jobKey.getName());
		    job.setJobGroup(jobKey.getGroup());
		    job.setDescription("触发器:" + trigger.getKey());
		    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
		    job.setJobStatus(triggerState.name());
		    if (trigger instanceof CronTrigger) {
		        CronTrigger cronTrigger = (CronTrigger) trigger;
		        String cronExpression = cronTrigger.getCronExpression();
		        job.setCronExpression(cronExpression);
		    }
		    jobList.add(job);
		}
		return jobList;
	}
	
	/**
	 * 添加任务
	 * @param jobName
	 * @param jobGroup
	 * @param timeExpression
	 * @return
	 * @throws SchedulerException
	 *
	 * @author Ethan Wong
	 * @datetime 2015年12月23日上午10:03:02
	 */
	public boolean addJob(String jobName,String jobGroup,String timeExpression) throws SchedulerException{
		Scheduler scheduler = this.getScheduler();
		
		//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		
		
		if (trigger == null) {
			//不存在，创建一个
			
			JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(jobName, jobGroup).storeDurably().build();
			jobDetail.getJobDataMap().put("scheduleJob", "job");
			scheduler.addJob(jobDetail, true);
			
			//表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(timeExpression);
			Date triggerStartTime = new Date();//开始执行时间
			Date triggerEndTime = null;//任务失效时间，默认为null
			//按新的cronExpression表达式构建一个新的trigger
			int triggerPriority = Trigger.DEFAULT_PRIORITY;//优先级越高越快执行
			trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).withPriority(triggerPriority)
					.startAt(triggerStartTime).endAt(triggerEndTime).forJob(jobDetail)
					.withSchedule(scheduleBuilder)
					.build();
			
			boolean isExists = scheduler.checkExists(jobDetail.getKey());
			if(!isExists){
				scheduler.scheduleJob(jobDetail, trigger);
			}else{
				
			}
			
			scheduler.scheduleJob(trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			//表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(timeExpression);
			//按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			//按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
		return false;
	}
	
	/**
	 * 暂停任务
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 *
	 * @author Ethan Wong
	 * @datetime 2015年12月23日上午10:17:00
	 */
	public void pauseJob(String jobName,String jobGroup) throws SchedulerException{
		Scheduler scheduler = this.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobName);
		scheduler.pauseJob(jobKey);
	}
	
	/**
	 * 恢复任务
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 *
	 * @author Ethan Wong
	 * @datetime 2015年12月23日上午10:17:41
	 */
	public void resumeJob(String jobName,String jobGroup) throws SchedulerException{
		Scheduler scheduler = this.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		scheduler.resumeJob(jobKey);
	}
	
	/**
	 * 删除任务
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 *
	 * @author Ethan Wong
	 * @datetime 2015年12月23日上午10:18:38
	 */
	public void  deleteJob(String jobName,String jobGroup) throws SchedulerException{
		Scheduler scheduler = this.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		scheduler.deleteJob(jobKey);
	}
	
	
	/**
	 * 触发任务
	 * @param jobName
	 * @param jobGroup
	 * @throws SchedulerException
	 *
	 * @author Ethan Wong
	 * @datetime 2015年12月23日上午10:19:49
	 */
	public void  triggerJob(String jobName,String jobGroup) throws SchedulerException{
		Scheduler scheduler = this.getScheduler();
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		scheduler.triggerJob(jobKey);
	}
	
	/**
	 * 更新任务触发器时间
	 * @param jobName
	 * @param jobGroup
	 * @param timeExpression
	 * @throws SchedulerException
	 *
	 * @author Ethan Wong
	 * @datetime 2015年12月23日上午10:21:55
	 */
	public void modifyTimeExpression(String jobName,String jobGroup,String timeExpression) throws SchedulerException{
		Scheduler scheduler = this.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		//表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(timeExpression);
		//按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		//按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);
	}

}
