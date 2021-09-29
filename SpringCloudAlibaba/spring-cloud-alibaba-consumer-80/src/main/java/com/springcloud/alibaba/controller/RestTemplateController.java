package com.springcloud.alibaba.controller;

import com.springcloud.alibaba.entity.CommonResult;
import com.springcloud.alibaba.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateController {

    //单机模式
    //public static final String PROVIDER_URL = "http://localhost:cloud-nacos-provider";
    //集群模式
    public static final String PROVIDER_URL = "http://cloud-nacos-provider";

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/rest/template/dept/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody Dept dept) {
        return restTemplate.postForObject(PROVIDER_URL + "/dept/add", dept, CommonResult.class);
    }

    @RequestMapping(value = "/rest/template/dept/get/{id}", method = RequestMethod.GET)
    public CommonResult<Dept> get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PROVIDER_URL + "/dept/get/" + id, CommonResult.class, id);
    }

    @RequestMapping(value = "/rest/template/dept/list", method = RequestMethod.GET)
    public CommonResult list() {
        return restTemplate.getForObject(PROVIDER_URL + "/dept/list/", CommonResult.class);
    }

    @GetMapping(value = "/rest/template/dept/discovery")
    public CommonResult<Object> discovery(){
        return restTemplate.getForObject(PROVIDER_URL + "/dept/discovery/", CommonResult.class);
    }
}
