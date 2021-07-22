package com.alibaba.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author recheard
 * @description:  rpc远程调用测试
 * @date 2021/6/2517:56
 */
@RestController
@RequestMapping("order")
public class RpcTestController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    int index = 0;

    @GetMapping("test")
    public String test () {
        //获取指定服务的节点列表
        List<ServiceInstance> memberServiceList = discoveryClient.getInstances("member-service");
        //轮询获取服务节点 uri内容为节点ip加端口
        URI uri = memberServiceList.get(index % memberServiceList.size()).getUri();
        index++;
        //通过restTemplate访问
        String result = restTemplate.getForObject(uri + "/test/test1", String.class);
        return "order service get result for member-service:" + result;
    }

    /**
     * 通过
     * @return
     */
    @GetMapping("test_ribbon")
    public String testRibbon () {
        //通过restTemplate访问
        String result = restTemplate.getForObject("http://member-service" + "/test/test1", String.class);
        return "order service get result for member-service:" + result;
    }


    /**
     * 通过负载均衡器访问其他服务
     * @return
     */
    @GetMapping("test_loadBalancer")
    public String testLoadBalancer () {
        //通过负载均衡器获取服务实例, 默认轮询
        ServiceInstance choose = loadBalancerClient.choose("member-service");
        URI uri = choose.getUri();
        //通过restTemplate访问
        // 特别注意：因为使用ribbon的时候resTemplate注解上会加上@LoadBalanced这回导致loadbalancer无法调用，所以需要重新new一个
        String result = new RestTemplate().getForObject(uri + "/test/test1", String.class);
        return "order service get result for member-service:" + result;
    }



}
