package com.springcloud.eureka.dao;
 
import com.springcloud.eureka.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptDao {

  public Integer addDept(Dept dept);
 
  public Dept findById(Long id);
 
  public List<Dept> findAll();
}
 

