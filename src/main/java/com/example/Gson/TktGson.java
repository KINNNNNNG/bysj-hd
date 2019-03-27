package com.example.Gson;

import java.util.List;

public class TktGson {
    private String tigan;
    private String nyd;
    private List<TktDaGson> da;
    private int id;
    private int uid;
    private String createTime;
    private Integer fz;
    public String getTigan() {
        return tigan;
    }

    public void setTigan(String tigan) {
        this.tigan = tigan;
    }

    public String getNyd() {
        return nyd;
    }

    public void setNyd(String nyd) {
        this.nyd = nyd;
    }

    public List<TktDaGson> getDa() {
        return da;
    }

    public void setDa(List<TktDaGson> da) {
        this.da = da;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFz() {
        return fz;
    }

    public void setFz(Integer fz) {
        this.fz = fz;
    }
}
