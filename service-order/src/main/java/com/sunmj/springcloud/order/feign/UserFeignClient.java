package com.sunmj.springcloud.order.feign;

import com.sunmj.springcloud.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 指定服务名称：service-user
 * feign客户端，是调用用户服务接口的
 * order服务调用了用户服务，如果其他的微服务也需要调用用户服务，
 * 那么它也要创建feignClient相关接口，其实我们把UserFeignClient放到接口工程中。
 * 这样其它微服务在调用用户服务的时候，就不要再重写UserFeignClient了。
 */
@FeignClient(name = "service-user",fallback = UserFeignFallback.class)
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
