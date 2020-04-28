package com.sunmj.springcloud.demo.entity;

import lombok.Data;

import javax.persistence.*;

//用户角色实体类
@Data
@Entity
@Table(name = "t_user_role_relation")
public class UserRoleRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    private Long role_id;
}
