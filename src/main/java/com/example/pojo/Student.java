package com.example.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Student {
    @Id
    @Column(name = "ID")
    private Integer id;

    private String user;

    private String pass;

    private String name;
}