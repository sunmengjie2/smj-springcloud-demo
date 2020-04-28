package com.sunmj.springcloud.demo.controller;

import com.sunmj.springcloud.demo.dao.OrderRepository;
import com.sunmj.springcloud.demo.dao.RoleRepository;
import com.sunmj.springcloud.demo.entity.Order;
import com.sunmj.springcloud.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("jpa")
public class JpaController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("order")
    public List<Order> list(){
        return orderRepository.findAll();
    }


    @RequestMapping("role")
    public List<Role> getRole(){

        return roleRepository.findAll();
    }

}
