package com.sunmj.springcloud.order.service;


import com.sunmj.springcloud.order.mapper.OrderMapper;
import com.sunmj.springcloud.user.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;
    /**
     * 根据Id 查询order
     * @param id
     * @return
     */
    public Order getOrderById(Integer id){

        return orderMapper.getOrderById(id);
    }


}
