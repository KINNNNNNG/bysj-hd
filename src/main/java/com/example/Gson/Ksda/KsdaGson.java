package com.example.Gson.Ksda;

import lombok.Data;

import java.util.List;

@Data
public class KsdaGson {
    private String name;
    private List<KsdaIdFzGson> xzt;
    private List<KsdaIdFzGson> dxt;
    private List<KsdaIdFzGson> tkt;
    private List<KsdaIdFzGson> jdt;
    private List<KsdaIdFzGson> pdt;
    private List<KsdaIdFzGson> zht;
    private float fs;
    private float mf;

    @Data
    public class KsdaIdFzGson {
        private int id;
        private float fz;
    }
}
