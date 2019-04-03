package com.example.mapper;

import com.example.pojo.Zongheti;
import com.example.utils.MyMapper;

import java.util.List;

public interface ZonghetiMapper extends MyMapper<Zongheti> {
    public void insertZht(Zongheti zongheti);
    public List<Zongheti> queryZhtAll();
    public List<Zongheti> queryZhtByUid();
    public void deleteZhtById(int id);
    public void updataById(Zongheti zongheti);
    public void updataUid(Zongheti zongheti);
    public Zongheti selectById(int id);
    public int selectNum();
}