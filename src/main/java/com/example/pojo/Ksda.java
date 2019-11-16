package com.example.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Ksda {
    @Id
    @Column(name = "sjID")
    private Integer sjid;

    private String user;

    private String name;

    private String xzt;

    private String dxt;

    private String tkt;

    private String pdt;

    private String jdt;

    private String zht;

    private String fs;

    private String createtime;

    private String mf;
}