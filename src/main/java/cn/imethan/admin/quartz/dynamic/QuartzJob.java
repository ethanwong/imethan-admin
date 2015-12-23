package cn.imethan.admin.quartz.dynamic;

import java.io.Serializable;

/**
 * ScheduleJob.java
 * 
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月22日下午3:42:38
 */
public class QuartzJob implements Serializable {
	
	//需要实现序列化接口，否则没办法持久化到数据库
	private static final long serialVersionUID = -1800494926172862932L;
	
    private String jobId;//任务id
    private String jobName;//任务名称
    private String jobGroup;//任务分组
    private String jobStatus;//任务状态 0禁用 1启用 2删除
    private String cronExpression;//任务运行时间表达式
    private String description;//任务描述
    
    
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
