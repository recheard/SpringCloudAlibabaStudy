package com.alibaba.openfeign.test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author recheard
 * @description:
 * @date 2021/8/315:50
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "student")
public class TestStudentProperties {

    private String name;

    private Integer age;

}
