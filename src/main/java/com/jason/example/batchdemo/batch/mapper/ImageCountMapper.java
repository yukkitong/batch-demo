package com.jason.example.batchdemo.batch.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageCountMapper {
  public Integer readCount();
}