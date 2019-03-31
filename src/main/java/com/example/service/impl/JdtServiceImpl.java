package com.example.service.impl;

import com.example.mapper.JiandatiMapper;
import com.example.pojo.Jiandati;
import com.example.service.JdtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JdtServiceImpl implements JdtService {
    @Autowired
    JiandatiMapper jiandatiMapper;
    @Override
    public void saveJandati(Jiandati jiandati) {
        jiandatiMapper.insertJdt(jiandati);
    }

    @Override
    public List<Jiandati> queryJdtAll() {
        return jiandatiMapper.queryJdtAll();
    }

    @Override
    public List<Jiandati> queryJdtByUid() {
        return jiandatiMapper.queryJdtByUid();
    }

    @Override
    public void deleteJdtById(int Id) {
        jiandatiMapper.deleteJdtById(Id);
    }

    @Override
    public void updataById(Jiandati jiandati) {
        jiandatiMapper.updataById(jiandati);
    }

    @Override
    public void updataUid(Jiandati jiandati) {
        jiandatiMapper.updataUid(jiandati);
    }

    @Override
    public Jiandati selectById(int id) {
        return jiandatiMapper.selectById(id);
    }

}
