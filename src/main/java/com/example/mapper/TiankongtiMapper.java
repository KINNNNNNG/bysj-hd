package com.example.mapper;

import com.example.pojo.Tiankongti;
import com.example.utils.MyMapper;

import java.util.List;

public interface TiankongtiMapper extends MyMapper<Tiankongti> {
    public void insertTkt(Tiankongti tiankongti);
    public List<Tiankongti> queryTktAll();
    public List<Tiankongti> queryTktByUid();
    public void deleteTktById(int id);
    public void updataById(Tiankongti tiankongti);
    public void updataUid(Tiankongti tiankongti);
    public Tiankongti selectById(int id);
}