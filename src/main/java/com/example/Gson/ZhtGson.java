package com.example.Gson;

import java.util.List;

public class ZhtGson {
    private String tigan;
    private List<ZhtTmGson> da;
    private String nyd;

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
}
