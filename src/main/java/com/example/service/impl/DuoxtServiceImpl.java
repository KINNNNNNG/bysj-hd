package com.example.service.impl;

import com.example.mapper.DuoxuantiMapper;
import com.example.pojo.Duoxuanti;
import com.example.service.DuoxtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuoxtServiceImpl implements DuoxtService {
    @Autowired
    DuoxuantiMapper duoxuantiMapper;

    @Override
    public void saveDuoxuanti(Duoxuanti duoxuanti) throws Exception {
        duoxuantiMapper.insertDuoxt(duoxuanti);
    }

    @Override
    public List<Duoxuanti> queryDxtAll() {
        return duoxuantiMapper.queryDxtAll();
    }

    @Override
    public void deleteDxtById(int Id) {
        duoxuantiMapper.deleteDxtById(Id);
    }

    @Override
    public void updataById(Duoxuanti duoxuanti) {
        duoxuantiMapper.updataById(duoxuanti);
    }

    @Override
    public void updataUid(Duoxuanti duoxuanti) {
        duoxuantiMapper.updataUid(duoxuanti);
    }

    @Override
    public List<Duoxuanti> queryDxtByUid() {
        return duoxuantiMapper.queryDxtByUid();
    }

    @Override
    public Duoxuanti selectById(int id) {
        return duoxuantiMapper.selectById(id);
    }

    @Override
    public int selectNum() {
        return duoxuantiMapper.selectNum();
    }
}
