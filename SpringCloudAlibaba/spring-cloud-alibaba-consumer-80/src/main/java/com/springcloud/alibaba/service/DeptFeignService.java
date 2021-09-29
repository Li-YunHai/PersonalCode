package com.springcloud.alibaba.service;

import com.springcloud.alibaba.entity.CommonResult;
import com.springcloud.alibaba.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "cloud-nacos-provider")
public interface DeptFeignService {

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody Dept dept);

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public CommonResult<Dept> get(@PathVariable("id") Long id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public CommonResult list();

    @GetMapping(value = "/dept/discovery")
    public CommonResult<Object> discovery();

}