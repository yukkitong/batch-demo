package com.jason.example.batchdemo.batch.job.step;

import java.util.Map;

import com.jason.example.batchdemo.batch.client.TourRestClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class FetchStepTasklet implements Tasklet {

  private final Logger logger = LoggerFactory.getLogger(FetchStepTasklet.class);

  @Autowired
  private TourRestClient restClient;

  private int currentPage = 1;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    Map<String, Object> params = chunkContext.getStepContext().getJobParameters();
    logger.info("FetchStepTasklet start with:");
    logger.info("\t>> parameters {}", params.toString());

    String response = restClient.fetchAreaBasedList(currentPage);
    logger.info("Fetch data response: {}", response);
    return ++currentPage <= 5 ? RepeatStatus.CONTINUABLE : RepeatStatus.FINISHED;
  }
}