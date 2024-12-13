package com.example.controller;

import com.example.config.ExampleProperties;
import com.example.utils.ProgramUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ouyangcm
 * create 2024/12/13 10:28
 */
@RestController
@RequestMapping("${example.path:/example}")
public class ExampleController {

    private static final Log log = LogFactory.getLog(ExampleController.class);
    private ExampleProperties exampleProperties;

    public ExampleController(ExampleProperties exampleProperties) {
        this.exampleProperties = exampleProperties;
    }

    @GetMapping("/ip")
    public String ip() {
        log.info(exampleProperties.getIp().getAddress());
        log.info(exampleProperties.getIp().getHostName());
        log.info(exampleProperties.getIp().getCanonicalHostName());
        return exampleProperties.getIp().getHostAddress();
    }

    @GetMapping("/pid")
    public String pid() {
        return ProgramUtils.getPID();
    }
}
