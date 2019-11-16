package com.example.Gson;

import lombok.Data;

import java.util.List;

@Data
public class SjGson {
    private String name;
    private List<DxtGson> xzt;
    private List<DuoxtGson> dxt;
    private List<TktGson> tkt;
    private List<PdtGson> pdt;
    private List<JdtGson> jdt;
    private List<ZhtGson> zht;
    private float allfz;
}
