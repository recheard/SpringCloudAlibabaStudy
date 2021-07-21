package com.alibaba.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author recheard
 * @description:
 * @date 2021/6/2517:56
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("test")
    public String test () {
        //获取指定服务的节点列表
        List<ServiceInstance> memberServiceList = discoveryClient.getInstances("member-service");
        //获取第一个节点 uri内容为节点ip加端口
        URI uri = memberServiceList.get(0).getUri();
        //通过restTemplate访问
        String result = restTemplate.getForObject(uri + "/test/test1", String.class);
        return "order service get result for member-service:" + result;
    }

}
