package com.example.service;

import com.example.pojo.Tiankongti;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TktService {
    public void saveTiankongti(Tiankongti tiankongti);
    public List<Tiankongti> queryTktAll();
    public List<Tiankongti> queryTktByUid();
    public void deleteTktById(int Id);
    public void updataById(Tiankongti tiankongti);
    public void updataUid(Tiankongti tiankongti);
    public Tiankongti selectById(int id);
}
