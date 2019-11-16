package com.example.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Sj {
    @Id
    @Column(name = "ID")
    private Integer id;

    private String name;

    private String xzt;

    private String tkt;

    private String dxt;

    private String pdt;

    private String jdt;

    private String zht;

    private String kl;

    private String ks;

    private String type;

    private String ksxz;

    private String zf;
}