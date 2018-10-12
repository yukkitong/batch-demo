package com.jason.example.batchdemo.rest;

import java.util.Date;

import com.jason.example.batchdemo.batch.mapper.ImageCountMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchLauncher {

  private static final Logger logger = LoggerFactory.getLogger(BatchLauncher.class);

  @Autowired
  JobLauncher jobLauncher;

  @Autowired
  @Qualifier("singleItemJob")
  Job job;

  @GetMapping("/execute")
  public String execute() {
    JobParametersBuilder builder = new JobParametersBuilder();
    builder.addLong("time", new Date().getTime());
    JobParameters jobParameters = builder.toJobParameters();

    try {
        jobLauncher.run(job, jobParameters);
    } catch (Exception e) {
      logger.error("ERROR", e);
      return "FAILED";
    }
    
    return "DONE " + job.getName();
  }

  // --------- MyBatis Examples ----------
  @Autowired
  private ImageCountMapper mapper;

  @GetMapping("/mapper-test")
  public String mapperTest() {
    // TODO: Add Service Layer!!
    return mapper.readCount().toString();
  }
}