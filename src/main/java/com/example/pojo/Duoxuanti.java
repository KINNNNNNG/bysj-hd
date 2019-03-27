package com.example.pojo;

import java.util.Date;
import javax.persistence.*;

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
     * @return tigan
     */
    public String getTigan() {
        return tigan;
    }

    /**
     * @param tigan
     */
    public void setTigan(String tigan) {
        this.tigan = tigan;
    }

    /**
     * @return xx
     */
    public String getXx() {
        return xx;
    }

    /**
     * @param xx
     */
    public void setXx(String xx) {
        this.xx = xx;
    }

    /**
     * @return da
     */
    public String getDa() {
        return da;
    }

    /**
     * @param da
     */
    public void setDa(String da) {
        this.da = da;
    }


    /**
     * @return nyd
     */
    public String getNyd() {
        return nyd;
    }

    /**
     * @param nyd
     */
    public void setNyd(String nyd) {
        this.nyd = nyd;
    }

    /**
     * @return UID
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}