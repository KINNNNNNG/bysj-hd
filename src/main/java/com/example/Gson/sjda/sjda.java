package com.example.Gson.sjda;

import lombok.Data;

import java.util.List;

@Data
public class sjda {
    private int ksid;
    private String user;
    private List<xztdaGson> xzt;
    private List<dxtdaGson> dxt;
    private List<tktdaGson> tkt;
    private List<pdtdaGson> pdt;
    private List<jdtdaGson> jdt;
    private List<zhtdaGson> zht;
}
