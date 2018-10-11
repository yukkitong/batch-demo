package com.jason.example.batchdemo.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;

@Configuration
@EnableBatchProcessing
@ImportResource("classpath:batch.xml")
public class BatchConfiguration {

  @Autowired
  private Environment environment;

  @Bean(destroyMethod="close")
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(environment.getProperty("batch.datasource.driver-class-name"));
    dataSource.setUrl(environment.getProperty("batch.datasource.jdbc-url"));
    dataSource.setUsername(environment.getProperty("batch.datasource.username"));
    dataSource.setPassword(environment.getProperty("batch.datasource.password"));
    return dataSource;
  }
}