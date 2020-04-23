package com.sunmj.springcloud.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sunmj.springcloud.order.feign.UserFeignClient;
import com.sunmj.springcloud.order.service.OrderService;
import com.sunmj.springcloud.user.model.Order;
import com.sunmj.springcloud.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order/")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserFeignClient userFeignClient;

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    //当前方法如果出现服务调用问题，使用Hystrix逻辑来处理
    @RequestMapping("getOrderById")
    @HystrixCommand(fallbackMethod = "getUserByIdFallback")
    public Order getOrderById(@RequestParam("id") Integer id){
        Order order = orderService.getOrderById(id);
        //service-user是被调用服务名称，spring.application.name的名称
        //User user = restTemplate.getForObject("http://service-user/user/getUserById?id="+order.getUserId(), User.class);
       // User user = userFeignClient.getUserById(id);
        /*User entity = new User();
        entity.setId(1);*/
       //User user = userFeignClient.getUserByUser(entity);
        User user = userFeignClient.getUserById(id);
        log.info("user:{}",user+"**********************");
      /*  try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        order.setUsername(user.getUsername());
        return order;
    }

    /**
     *getUserByIdFallback方法参数要和userFeignClient.getUserById参数一致
     * @param id
     * @return
     */
    public Order getUserByIdFallback(Integer id){
        Order order = orderService.getOrderById(id);
        order.setUsername("固定滴值");
        return order;
    }
}
