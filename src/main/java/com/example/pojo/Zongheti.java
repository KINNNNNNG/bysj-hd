package com.example.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Zongheti {
    @Id
    @Column(name = "ID")
    private Integer id;

    private String tigan;

    private String da;

    private String tm;

    private String nyd;

    @Column(name = "UID")
    private Integer uid;

    private String createTime;
    private String text;
}