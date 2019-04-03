package com.example.pojo;

import javax.persistence.*;

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

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return xzt
     */
    public String getXzt() {
        return xzt;
    }

    /**
     * @param xzt
     */
    public void setXzt(String xzt) {
        this.xzt = xzt;
    }

    /**
     * @return tkt
     */
    public String getTkt() {
        return tkt;
    }

    /**
     * @param tkt
     */
    public void setTkt(String tkt) {
        this.tkt = tkt;
    }

    /**
     * @return dxt
     */
    public String getDxt() {
        return dxt;
    }

    /**
     * @param dxt
     */
    public void setDxt(String dxt) {
        this.dxt = dxt;
    }

    /**
     * @return pdt
     */
    public String getPdt() {
        return pdt;
    }

    /**
     * @param pdt
     */
    public void setPdt(String pdt) {
        this.pdt = pdt;
    }

    /**
     * @return jdt
     */
    public String getJdt() {
        return jdt;
    }

    /**
     * @param jdt
     */
    public void setJdt(String jdt) {
        this.jdt = jdt;
    }

    /**
     * @return zht
     */
    public String getZht() {
        return zht;
    }

    /**
     * @param zht
     */
    public void setZht(String zht) {
        this.zht = zht;
    }

    /**
     * @return kl
     */
    public String getKl() {
        return kl;
    }

    /**
     * @param kl
     */
    public void setKl(String kl) {
        this.kl = kl;
    }

    /**
     * @return ks
     */
    public String getKs() {
        return ks;
    }

    /**
     * @param ks
     */
    public void setKs(String ks) {
        this.ks = ks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKsxz() {
        return ksxz;
    }

    public void setKsxz(String ksxz) {
        this.ksxz = ksxz;
    }

    public String getZf() {
        return zf;
    }

    public void setZf(String zf) {
        this.zf = zf;
    }
}