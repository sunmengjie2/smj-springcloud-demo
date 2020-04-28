package com.sunmj.springcloud.demo.dao;


import com.sunmj.springcloud.demo.entity.Order;
import com.sunmj.springcloud.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {





}
