package com.example.Gson;

import java.util.List;

public class DuoxtGson {
    private Integer id;

    private String tigan;

    private List<DuoxtXxGson> xx;

    private String nyd;

    private Integer uid;
    private List<String> da;
    private String createTime;
    private Integer fz;
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTigan() {
        return tigan;
    }

    public void setTigan(String tigan) {
        this.tigan = tigan;
    }

    public List<DuoxtXxGson> getXx() {
        return xx;
    }

    public void setXx(List<DuoxtXxGson> xx) {
        this.xx = xx;
    }

    public String getNyd() {
        return nyd;
    }

    public void setNyd(String nyd) {
        this.nyd = nyd;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public List<String> getDa() {
        return da;
    }

    public void setDa(List<String> da) {
        this.da = da;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getFz() {
        return fz;
    }

    public void setFz(Integer fz) {
        this.fz = fz;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
