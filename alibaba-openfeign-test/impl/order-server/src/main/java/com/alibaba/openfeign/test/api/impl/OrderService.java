package com.alibaba.openfeign.test.api.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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
     *
     * @return
     */
    @GetMapping("testMember")
    public String testOrder() {
        return memberServiceFeign.hello(234);
    }

    /**
     * 测试调用限流服务
     * SentinelResource
     * value  //限流模块名就是rule.setResource("testSentinel");中的值，该值必须保持完全一致，
     * blockHandler 限流、熔断、出现异常时执行的方法 注解的方法需要与
     * blockHandler中方法的参数和返回结果保持一致，其中的BlockException e 参数除外
     * fallback 服务降级时执行的方法
     *
     * @return
     */
    @GetMapping("testSentinel")
    @SentinelResource(value = "testSentinel", blockHandler = "getOrderQpsException")
    public String testSentinel() {
        return memberServiceFeign.hello(234);
    }


    @GetMapping("testSentinelConsole")
    @SentinelResource(value = "testSentinelConsole", blockHandler = "getOrderQpsException")
    public String testSentinelConsole() {
        return memberServiceFeign.hello(234);
    }

    @GetMapping("testSentinelFusing")
    @SentinelResource(value = "testSentinelFusing", blockHandler = "getOrderQpsException",
            fallback = "getOrderFusingException")
    public String testSentinelConsole2() {
        return memberServiceFeign.hello(234);
    }

    public String getOrderFusingException(BlockException e) {
        e.printStackTrace();
        return "该接口已熔断降级";
    }

    public String getOrderQpsException(BlockException e) {
        e.printStackTrace();
        return "该接口已限流";
    }

}
