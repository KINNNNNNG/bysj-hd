package com.example.pojo;

import java.util.Date;
import javax.persistence.*;

public class Panduanti {
    @Id
    @Column(name = "ID")
    private Integer id;

    private String tigan;

    private String da;


    private String nyd;


    private String createTime;

    @Column(name = "UID")
    private Integer uid;
    private String text;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}