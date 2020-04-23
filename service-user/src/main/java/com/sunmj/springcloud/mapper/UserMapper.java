package com.sunmj.springcloud.mapper;

import com.sunmj.springcloud.user.model.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select * from user where id=#{id}")
    User getUserById(Integer id);


}
