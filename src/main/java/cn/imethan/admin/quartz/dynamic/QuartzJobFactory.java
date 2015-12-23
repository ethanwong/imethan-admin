package cn.imethan.admin.quartz.dynamic;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * QuartzJobFactory.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月21日下午5:36:08
 */
@DisallowConcurrentExecution
public class QuartzJobFactory  implements Job,Serializable{

	private static final long serialVersionUID = 3352853281096701664L;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("★★★★★★★★★★★QuartzJob★★★★★★★★★★★:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));    
        System.out.println("QuartzJob Name = [" + context.getJobDetail().getKey() + "]");
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
