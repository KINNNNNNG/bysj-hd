package com.example.mapper;

import com.example.pojo.Jiandati;
import com.example.utils.MyMapper;

import java.util.List;

public interface JiandatiMapper extends MyMapper<Jiandati> {
    public void insertJdt(Jiandati jiandati);
    public List<Jiandati> queryJdtAll();
    public List<Jiandati> queryJdtByUid();
    public void deleteJdtById(int id);
    public void updataById(Jiandati jiandati);
    public void updataUid(Jiandati jiandati);
}