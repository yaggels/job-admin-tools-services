package com.sapient.jat.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
 * <p>
 * A simple job configured in Spring.
 * Just prints details about the job and when is the next schedule.  
 * </p>
 * @author Johnson Chow
 *
 */
@Component
public class SimpleJob implements Job {
	
	private static Logger logger = LoggerFactory.getLogger(SimpleJob.class);

	@Override
	public void execute(JobExecutionContext jobExecutionCntxt) throws JobExecutionException {
		logger.info("Job ** {} ** Trigger ** {} ** fired @ {}", jobExecutionCntxt.getJobDetail().getKey().getName(), jobExecutionCntxt.getTrigger().getKey().getName(), jobExecutionCntxt.getFireTime());
		logger.info("Next job scheduled @ {}", jobExecutionCntxt.getNextFireTime());
	}

}
