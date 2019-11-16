package com.example.Gson;

import lombok.Data;

import java.util.List;

@Data
public class TktGson {
    private String tigan;
    private String nyd;
    private List<TktDaGson> da;
    private int id;
    private int uid;
    private String createTime;
    private Integer fz;
    private String text;
}
