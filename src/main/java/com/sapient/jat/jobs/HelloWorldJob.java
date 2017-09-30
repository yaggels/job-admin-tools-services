package com.sapient.jat.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Simple job that says the classic Hello World!
 * </p>
 * 
 * @author Johnson Chow
 *
 */
public class HelloWorldJob implements Job {
	
	private static Logger logger = LoggerFactory.getLogger(HelloWorldJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("Hello there!");
	}

}
