package com.springcloud.alibaba.controller;

import com.springcloud.alibaba.entity.CommonResult;
import com.springcloud.alibaba.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConfigClientController {

    //集群模式
    public static final String PROVIDER_URL = "http://cloud-nacos-provider";

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/rest/template/configInfo", method = RequestMethod.GET)
    public String configInfo() {
        String result = restTemplate.getForObject(PROVIDER_URL + "/configInfo/", String.class);
        return result;
    }
}
