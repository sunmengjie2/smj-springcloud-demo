package com.sunmj.springcloud.order.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfig {

    /**
     * restTemplate发送请求
     * LoadBalanced注解可以负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

    /**
     * 配置负载均衡算法
     * @return
     */
    @Bean
    public IRule iRule(){
        //随机算法
        //return new RandomRule();
        //轮训方式
        //return new RoundRobinRule();

        //如果一个服务挂掉会尝试几次，如过几次访问失败，下次就会跳过失败的服务
        return  new RetryRule();
    }
}
