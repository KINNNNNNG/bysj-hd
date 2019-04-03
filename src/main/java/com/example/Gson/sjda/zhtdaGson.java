package com.example.Gson.sjda;

import java.util.List;

public class zhtdaGson {
    private int fz;
    private int id;
    private List<zhtdadaGson> da;

    public List<zhtdadaGson> getDa() {
        return da;
    }

    public void setDa(List<zhtdadaGson> da) {
        this.da = da;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFz() {
        return fz;
    }

    public void setFz(int fz) {
        this.fz = fz;
    }
}
