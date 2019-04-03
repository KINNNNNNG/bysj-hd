package com.example.Gson;

import java.util.List;

public class SjGson {
    private String name;
    private List<DxtGson> xzt;
    private List<DuoxtGson> dxt;
    private List<TktGson> tkt;
    private List<PdtGson> pdt;
    private List<JdtGson> jdt;
    private List<ZhtGson> zht;
    private float allfz;

    public List<DxtGson> getXzt() {
        return xzt;
    }

    public void setXzt(List<DxtGson> xzt) {
        this.xzt = xzt;
    }

    public List<DuoxtGson> getDxt() {
        return dxt;
    }

    public void setDxt(List<DuoxtGson> dxt) {
        this.dxt = dxt;
    }

    public List<TktGson> getTkt() {
        return tkt;
    }

    public void setTkt(List<TktGson> tkt) {
        this.tkt = tkt;
    }

    public List<PdtGson> getPdt() {
        return pdt;
    }

    public void setPdt(List<PdtGson> pdt) {
        this.pdt = pdt;
    }

    public List<JdtGson> getJdt() {
        return jdt;
    }

    public void setJdt(List<JdtGson> jdt) {
        this.jdt = jdt;
    }

    public List<ZhtGson> getZht() {
        return zht;
    }

    public void setZht(List<ZhtGson> zht) {
        this.zht = zht;
    }

    public float getAllfz() {
        return allfz;
    }

    public void setAllfz(float allfz) {
        this.allfz = allfz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
