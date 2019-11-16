package com.example.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Danxuanti {
    @Id
    @Column(name = "ID")
    private Integer id;

    private String tigan;

    private String xx;

    private String nyd;

    @Column(name = "UID")
    private Integer uid;

    private String da;

    @Column(name = "createTime")
    private String createTime;

    private String text;
}