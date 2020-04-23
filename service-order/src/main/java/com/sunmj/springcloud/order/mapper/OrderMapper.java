package com.sunmj.springcloud.order.mapper;

import com.sunmj.springcloud.user.model.Order;
import org.apache.ibatis.annotations.Select;

public interface OrderMapper {

    @Select("select * from `order` where id=#{id}")
    Order getOrderById(Integer id);
}
