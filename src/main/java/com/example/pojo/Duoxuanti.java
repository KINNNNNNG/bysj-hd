package com.example.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Duoxuanti {
    @Id
    @Column(name = "ID")
    private Integer id;

    private String tigan;

    private String xx;

    private String da;

    private String nyd;

    @Column(name = "UID")
    private Integer uid;

    @Column(name = "createTime")
    private String createtime;

    private String text;
}