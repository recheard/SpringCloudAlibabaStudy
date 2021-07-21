package com.alibaba.test.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author recheard
 * @description:
 * @date 2021/6/2515:26
 */

@RestController
@RequestMapping("test")
public class TestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/test1")
    public String test1 () {
        return "member-service port : " + port;
    }

}
