package com.alibaba.openfeign.test.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author recheard
 * @description:
 * @date 2021/7/2218:04
 */
@RequestMapping("test")
public interface MemberService {

    /**
     * 测试openfeign
     * @return
     */
    @GetMapping("test1")
    String hello();

}
