package com.sunmj.springcloud.consul.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sunmj.springcloud.consul.config.ConsulConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@RefreshScope动态刷新配置
@RefreshScope
@RestController
public class TestController {
    @Value("${config.info}")

    private String configInfo;
    @RequestMapping("getConfig")
    public Object getConfig(){
        return configInfo;
    }

    @Autowired
    private ConsulConfigInfo consulConfigInfo;

    @RequestMapping("gainConfig")
    public Object gainConfig(){
        return consulConfigInfo;
    }
}
