package com.sunmj.springcloud.order.controller;

import com.sunmj.springcloud.order.model.Order;
import com.sunmj.springcloud.order.service.OrderService;
import com.sunmj.springcloud.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    @RequestMapping("getOrderById")
    public Order getOrderById(@RequestParam("id") Integer id){
        Order order = orderService.getOrderById(id);

        return order;
    }


}
