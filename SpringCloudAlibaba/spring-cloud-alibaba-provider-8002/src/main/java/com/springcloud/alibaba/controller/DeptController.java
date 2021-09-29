package com.springcloud.alibaba.controller;

import java.util.List;

import com.springcloud.alibaba.entity.CommonResult;
import com.springcloud.alibaba.entity.Dept;
import com.springcloud.alibaba.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DeptController {

	@Value("${server.port}")
	private String serverPort;

	@Autowired
	private DeptService service;

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public CommonResult add(@RequestBody Dept dept) {
		int result =  service.add(dept);
		log.info("*****插入操作返回结果:" + result);

		if (result > 0) {
			return new CommonResult(200, "插入数据库成功，端口号：" + serverPort, result);
		} else {
			return new CommonResult(444, "插入数据库失败，端口号：" + serverPort, null);
		}
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public CommonResult<Dept> get(@PathVariable("id") Long id) {
		Dept dept = service.get(id);
		log.info("*****查询结果:{}", dept);
		if (dept != null) {
			return new CommonResult(200, "查询成功，端口号：" + serverPort, dept);
		} else {
			return new CommonResult(444, "没有对应记录,查询ID: " + id +"，端口号：" + serverPort, null);
		}
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public CommonResult<List<Dept>> list() {
		List<Dept> list = service.list();
		log.info("*****查询结果:{}", list);
		if (list != null) {
			return new CommonResult(200, "查询成功，端口号：" + serverPort, list);
		} else {
			return new CommonResult(444, "没有对应记录,端口号：" + serverPort, null);
		}
	}

	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public CommonResult<Object> discovery() {
		List<String> list = discoveryClient.getServices();
		System.out.println(list);
		List<ServiceInstance> instanceList = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
		for(ServiceInstance instance : instanceList) {
			System.out.println(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
		}
		return new CommonResult(200, "查询成功，端口号：" + serverPort, discoveryClient);
	}

}
