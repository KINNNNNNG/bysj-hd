package com.example.pojo;

import java.util.Date;
import javax.persistence.*;

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

    /**
     * @return sjID
     */
    public Integer getSjid() {
        return sjid;
    }

    /**
     * @param sjid
     */
    public void setSjid(Integer sjid) {
        this.sjid = sjid;
    }

    /**
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
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
     * @return fs
     */
    public String getFs() {
        return fs;
    }

    /**
     * @param fs
     */
    public void setFs(String fs) {
        this.fs = fs;
    }

    /**
     * @return createtime
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getMf() {
        return mf;
    }

    public void setMf(String mf) {
        this.mf = mf;
    }
}