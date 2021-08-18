package com.alibaba.openfeign.test.sentinel;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author recheard
 * @description: 初始化Sentinel限流规则
 * @date 2021/8/1523:54
 */
@Component
//实现ApplicationRunner接口后会自动实现run方法，该方法会在项目启动的时候加载，效果和postContract注解相同
public class SentinelConfig implements ApplicationRunner {

    /**
     * 手动初始化限流规则，和nacos中的配置会有冲突，覆盖掉nacos中配置额规则
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) {

        //初始化服务降级
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule("testSentinelFusing2")
                .setGrade(CircuitBreakerStrategy.SLOW_REQUEST_RATIO.getType())
                // Max allowed response time
                .setCount(50)
                // Retry timeout (in second)
                .setTimeWindow(10)
                // Circuit breaker opens when slow request ratio > 60%
                .setSlowRatioThreshold(0.6)
                .setMinRequestAmount(100)
                .setStatIntervalMs(20000);
        rules.add(rule);

        DegradeRuleManager.loadRules(rules);
        //初始化服务限流
        /*List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        //设置限流规则名称 ，可以为接口名
        rule.setResource("testSentinel");
        //设置每秒QPS数量
        rule.setCount(1);
        //设置限流类型为QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
        System.out.println("初始化成功");*/
    }
}
