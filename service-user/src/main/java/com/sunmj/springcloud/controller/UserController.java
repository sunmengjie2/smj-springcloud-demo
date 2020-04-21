package com.sunmj.springcloud.controller;

import com.sunmj.springcloud.service.UserService;
import com.sunmj.springcloud.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @RequestMapping("getUserById")
    public User getUserById(@RequestParam("id") Integer id){

        return userService.getUserById(id);
    }

    /**
     * 根据id查询用户名称
     * @param id
     * @return
     */
    @RequestMapping("getUsernameById")
    public String getUsernameById(@RequestParam("id") Integer id){

        return userService.getUsernameById(id);
    }
}
