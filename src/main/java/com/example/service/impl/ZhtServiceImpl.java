package com.example.service.impl;

import com.example.mapper.ZonghetiMapper;
import com.example.pojo.Zongheti;
import com.example.service.ZhtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZhtServiceImpl implements ZhtService {
    @Autowired
    ZonghetiMapper zonghetiMapper;
    @Override
    public void saveZongheti(Zongheti zongheti) {
        zonghetiMapper.insertZht(zongheti);
    }

    @Override
    public List<Zongheti> queryZhtAll() {
        return zonghetiMapper.queryZhtAll();
    }

    @Override
    public List<Zongheti> queryZhtByUid() {
        return zonghetiMapper.queryZhtByUid();
    }

    @Override
    public void deleteZhtById(int Id) {
        zonghetiMapper.deleteZhtById(Id);
    }

    @Override
    public void updataById(Zongheti zongheti) {
        zonghetiMapper.updataById(zongheti);
    }

    @Override
    public void updataUid(Zongheti zongheti) {
        zonghetiMapper.updataUid(zongheti);
    }

}
