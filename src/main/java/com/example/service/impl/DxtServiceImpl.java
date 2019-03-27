package com.example.service.impl;

import com.example.mapper.DanxuantiMapper;
import com.example.pojo.Danxuanti;
import com.example.service.DxtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DxtServiceImpl implements DxtService {
    @Autowired
    DanxuantiMapper danxuantiMapper;
    @Override
    public void saveDanxuanti(Danxuanti danxuanti) throws Exception {
        danxuantiMapper.insertXzt(danxuanti);
    }

    @Override
    public void updateDanxuanti(Danxuanti danxuanti) {
        danxuantiMapper.updataById(danxuanti);
    }

    @Override
    public void updataUid(Danxuanti danxuanti) {
        danxuantiMapper.updataUid(danxuanti);
    }

    @Override
    public void deleteDanxuanti(int danxuantiId) {
        danxuantiMapper.deleteXztById(danxuantiId);
    }

    @Override
    public List<Danxuanti> selectByUid() {
        return danxuantiMapper.selectByUid();
    }

    @Override
    public Danxuanti queryDanxuantiById(String danxuantiId) {
        return null;
    }

    @Override
    public List<Danxuanti> queryDanxuantiAll() {
        List<Danxuanti> danxuanti = danxuantiMapper.selectXztAll();
        return danxuanti ;
    }

    @Override
    public List<Danxuanti> queryDanxuantiList(Danxuanti danxuanti) {
        return null;
    }

    @Override
    public List<Danxuanti> queryDanxuantiListPaged(Danxuanti danxuanti, Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public Danxuanti queryDanxuantiByIdCustom(String danxuantiId) {
        return null;
    }

    @Override
    public void saveUserTransactional(Danxuanti danxuanti) {

    }
}
