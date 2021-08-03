package com.alibaba.openfeign.test.api.impl;

import com.alibaba.openfeign.test.openfeign.MemberServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author recheard
 * @description:
 * @date 2021/8/216:16
 */
@RestController
public class OrderService {

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    /**
     * 测试调用member服务
     * @return
     */
    @GetMapping("testMember")
    public String testOrder() {
        String result = memberServiceFeign.hello(234);
        return result;
    }

}
