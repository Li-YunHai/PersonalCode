package com.springcloud.alibaba.controller;

import com.springcloud.alibaba.entity.CommonResult;
import com.springcloud.alibaba.entity.Dept;
import com.springcloud.alibaba.service.DeptFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class FeignController {

    @Autowired
    DeptFeignService deptFeignService;

    /**
     * 测试路径：http://localhost/feign/dept/add
     * @return
     */
    @RequestMapping(value = "/feign/dept/add", method = RequestMethod.POST)
    public CommonResult add(@RequestBody Dept dept) {
        return deptFeignService.add(dept);
    }

    /**
     * 测试路径：http://localhost/feign/dept/get/1
     * @return
     */
    @RequestMapping(value = "/feign/dept/get/{id}", method = RequestMethod.GET)
    public CommonResult<Dept> get(@PathVariable("id") Long id) {
        return deptFeignService.get(id);
    }

    /**
     * 测试路径：http://localhost/feign/dept/list
     * @return
     */
    @RequestMapping(value = "/feign/dept/list", method = RequestMethod.GET)
    public CommonResult list() {
        return deptFeignService.list();
    }

    /**
     * 测试路径：http://localhost/feign/dept/discovery
     * @return
     */
    @GetMapping(value = "/feign/dept/discovery")
    public CommonResult<Object> discovery(){
        return deptFeignService.discovery();
    }
}
