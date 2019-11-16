package com.example.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class User {
    @Column(name = "ID")
    @Id
    private String id;

    private String name;

    private String pass;

    @Column(name = "createTime")
    private String createtime;
}