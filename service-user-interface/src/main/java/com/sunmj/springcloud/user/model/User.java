package com.sunmj.springcloud.user.model;

import lombok.Data;

import javax.print.DocFlavor;
import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    private String username;

}
