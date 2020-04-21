package com.sunmj.springcloud.order.feign;

import com.sunmj.springcloud.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 指定服务名称：service-user
 */
@FeignClient(name = "service-user")
public interface UserFeignClient {

    @RequestMapping("user/getUserById")
    public User getUserById(@RequestParam("id") Integer id);

    @RequestMapping("user/getUserByUser")
    public User getUserByUser(@RequestBody User user);

    /**
     * 根据Id，查询用户名称
     * @param id
     * @return
     */
    @RequestMapping("/user/getUsernameById")
    public String getUsernameById(@RequestParam("id") Integer id);
}
