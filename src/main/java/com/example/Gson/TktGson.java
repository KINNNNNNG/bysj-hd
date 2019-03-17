package com.example.Gson;

import java.util.List;

public class TktGson {
    private String tigan;
    private String nyd;
    private List<TktDaGson> da;

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
}
