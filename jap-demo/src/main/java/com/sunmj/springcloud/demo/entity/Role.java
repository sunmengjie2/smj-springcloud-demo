package com.sunmj.springcloud.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "t_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //角色名称
    private String name;

    //多对多关系
    //JoinTable name是关系表的表名 joinColumns是主表在关系表对应的字段
    //          inverseJoinColumns是从表对应的字段
    @JsonIgnoreProperties("roleList")
    @ManyToMany
    @JoinTable(name = "t_user_role_relation",joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userList;
}
