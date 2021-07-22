package com.alibaba.openfeign.test.api.impl;

import com.alibaba.openfeign.test.api.MemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author recheard
 * @description:
 * @date 2021/7/2218:06
 */
@RestController
public class MemberServiceImpl implements MemberService {

    @Value("${server.port}")
    private String port;


    @Override
    public String hello() {
        return "member-service port : " + port;
    }
}
