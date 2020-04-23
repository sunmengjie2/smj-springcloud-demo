package com.sunmj.springcloud.order.feign;

import com.sunmj.springcloud.user.model.User;
import org.springframework.stereotype.Component;

/**
 * UserFeignFallback 来实现UserFeignClient接口
 * feignClient中指定fallback属性为UserFeignFallback
 */
@Component
public class UserFeignFallback implements UserFeignClient{

    @Override
    public User getUserById(Integer id) {
        return null;
    }

    @Override
    public User getUserByUser(User user) {
        User user1 = new User();
        user1.setUsername("服务调用失败");
        return user1;
    }

    @Override
    public String getUsernameById(Integer id) {
        return null;
    }
}
