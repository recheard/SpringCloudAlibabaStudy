package com.alibaba.openfeign.test.api.impl;

import com.alibaba.openfeign.test.api.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author recheard
 * @description:
 * @date 2021/7/2218:06
 */
@RestController
@RefreshScope
public class MemberServiceImpl implements MemberService {

    @Value("${server.port}")
    private String port;

    @Value("${test}")
    private String test;


    @Override
    public String hello(Integer userId) {
        return "用户id" + userId + "访问服务： member-service port : " + port;
    }

    @Override
    public String testConfig() {
        return test;
    }
}
