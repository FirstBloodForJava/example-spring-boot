package com.example.autoconfigure;

import com.example.config.ExampleProperties;
import com.example.controller.ExampleController;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

/**
 * @author ouyangcm
 * create 2024/12/13 10:28
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
@AutoConfigureAfter(ErrorMvcAutoConfiguration.class)
@EnableConfigurationProperties(ExampleProperties.class)
public class ExampleWebAutoConfiguration {

    @Bean
    public ExampleController exampleController(ExampleProperties exampleProperties) {
        return new ExampleController(exampleProperties);
    }
}
