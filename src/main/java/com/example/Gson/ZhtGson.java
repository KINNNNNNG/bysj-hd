package com.example.Gson;

import java.util.List;

public class ZhtGson {
    private String tigan;
    private List<ZhtTmGson> da;
    private String nyd;
    private int id;
    private int uid;
    private String createTime;
    private Integer fz;
    private String text;
    public String getNyd() {
        return nyd;
    }

    public void setNyd(String nyd) {
        this.nyd = nyd;
    }

    public List<ZhtTmGson> getDa() {
        return da;
    }

    public void setDa(List<ZhtTmGson> da) {
        this.da = da;
    }

    public String getTigan() {
        return tigan;
    }

    public void setTigan(String tigan) {
        this.tigan = tigan;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
