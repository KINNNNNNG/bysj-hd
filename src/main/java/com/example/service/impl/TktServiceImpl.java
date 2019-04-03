package com.example.service.impl;

import com.example.mapper.TiankongtiMapper;
import com.example.pojo.Tiankongti;
import com.example.service.TktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TktServiceImpl implements TktService {
    @Autowired
    TiankongtiMapper tiankongtiMapper;
    @Override
    public void saveTiankongti(Tiankongti tiankongti) {
        tiankongtiMapper.insertTkt(tiankongti);
    }

    @Override
    public List<Tiankongti> queryTktAll() {
        return tiankongtiMapper.queryTktAll();
    }

    @Override
    public List<Tiankongti> queryTktByUid() {
        return tiankongtiMapper.queryTktByUid();
    }

    @Override
    public void deleteTktById(int Id) {
        tiankongtiMapper.deleteTktById(Id);
    }

    @Override
    public void updataById(Tiankongti tiankongti) {
        tiankongtiMapper.updataById(tiankongti);
    }

    @Override
    public void updataUid(Tiankongti tiankongti) {
        tiankongtiMapper.updataUid(tiankongti);
    }

    @Override
    public Tiankongti selectById(int id) {
        return tiankongtiMapper.selectById(id);
    }

    @Override
    public int selectNum() {
        return tiankongtiMapper.selectNum();
    }

}
