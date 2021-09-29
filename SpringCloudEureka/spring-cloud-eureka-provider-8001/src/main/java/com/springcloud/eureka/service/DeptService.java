package com.springcloud.eureka.service;
 
import com.springcloud.eureka.entity.Dept;

import java.util.List;

public interface DeptService {
  public Integer add(Dept dept);

  public Dept get(Long id);

  public List<Dept> list();
}
 

