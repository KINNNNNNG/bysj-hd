package com.example.mapper;

import com.example.pojo.Duoxuanti;
import com.example.utils.MyMapper;

import java.util.List;

public interface DuoxuantiMapper extends MyMapper<Duoxuanti> {
    public void insertDuoxt(Duoxuanti duoxuanti);
    public List<Duoxuanti> queryDxtAll();
    public void deleteDxtById(int id);
    public void updataById(Duoxuanti duoxuanti);
    public void updataUid(Duoxuanti duoxuanti);
    public List<Duoxuanti> queryDxtByUid();
}