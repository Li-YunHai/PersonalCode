package com.springcloud.alibaba.service;
 
import com.springcloud.alibaba.entity.Dept;

import java.util.List;

public interface DeptService {
  public Integer add(Dept dept);

  public Dept get(Long id);

  public List<Dept> list();
}
 

