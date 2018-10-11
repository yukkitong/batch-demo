package com.jason.example.batchdemo.batch.job.step;

import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.repeat.RepeatStatus;

public class FetchSingleDetailsTasklet implements Tasklet {

  @Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    StepContext stepContext = chunkContext.getStepContext();
    Map<String, Object> parameters = stepContext.getJobParameters();


		return null;
  }
  
  private String getCotIdByContentId(String contentId) {
    JdbcCursorItemReader reader = new JdbcCursorItemReader();
    return "";
  }
}