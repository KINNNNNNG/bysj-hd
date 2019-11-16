package com.example.Gson;

import lombok.Data;

import java.util.List;

@Data
public class ZhtGson {
    private String tigan;
    private List<ZhtTmGson> da;
    private String nyd;
    private int id;
    private int uid;
    private String createTime;
    private Integer fz;
    private String text;
}
