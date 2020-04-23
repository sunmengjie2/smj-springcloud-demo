package com.sunmj.springcloud.service;

import com.sunmj.springcloud.mapper.UserMapper;
import com.sunmj.springcloud.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    /**
     * 根据Id 查询user
     * @param id
     * @return
     */
    public User getUserById(Integer id){

        return userMapper.getUserById(id);
    }

    /**
     * 根据Id，查询用户名称
     * @param id
     * @return
     */
    public String getUsernameById(Integer id){
        User user = getUserById(id);
        return user.getUsername();
    }
}
