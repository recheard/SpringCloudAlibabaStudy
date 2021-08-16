package com.alibaba.openfeign.test.sentinel;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
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
