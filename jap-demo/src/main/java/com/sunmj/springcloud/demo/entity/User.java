package com.sunmj.springcloud.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//Entity 数据库实体  Table指定表名
@Data
@Entity
@Table(name = "t_user")
public class User implements Serializable {

    //主键生成策略
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//没有被注解的属性，在建表也会生成对应的字段
    private String username;
    private  String password;

    private String phone;
    private Date createTime;

    /*//忽略Order的user属性
    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "userId")
    private List<Order> orderList;*/

    //忽略Order的user属性
    /*@JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "userId")
    @JoinColumn(name = "userId",insertable = false,updatable = false)
    private List<Order> orderList;*/

    //多对多关系
    //JoinTable name是关系表的表名 joinColumns是主表在关系表对应的字段
    //          inverseJoinColumns是从表对应的字段
    @ManyToMany
    @JoinTable(name = "t_user_role_relation",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList;

}
