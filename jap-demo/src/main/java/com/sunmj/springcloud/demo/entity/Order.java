package com.sunmj.springcloud.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.net.InetAddress;
import java.util.Date;

@Data
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //订单编号
    private String orderNo;

    //用户id
    private Integer userId;

    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //一对一关系，JoinColumn name指定关联的字段 referencedColumnName是当前实体主键，则可省略
    //insertable  updatable=false 新增或修改时限制更新关联表，用JoinColumn是指定为false
    /*@OneToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "userId",referencedColumnName = "id",insertable = false,updatable = false)
    private User user;*/

    /*@ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "userId",insertable = false,updatable = false)
    private User user;*/

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "userId",insertable = false,updatable = false)
    private User user;

}
