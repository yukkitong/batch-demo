package com.jason.example.batchdemo.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value="com.jason.example.batch.mapper", sqlSessionFactoryRef="mariadbSessionFactory")
public class KTODataSourceConfiguration {

  @Bean(name="mariadbDataSource")
  @ConfigurationProperties(prefix="kto.datasource")
  public DataSource getMariadbDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name="mariadbSessionFactory")
  public SqlSessionFactory getSessionFactory(@Qualifier("mariadbDataSource") DataSource dataSource, ApplicationContext context) throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath:mybatis/mapper/**/*.xml"));
    return sqlSessionFactoryBean.getObject();
  }

  @Bean(name="mariadbSessionTemplate")
  public SqlSessionTemplate getSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}