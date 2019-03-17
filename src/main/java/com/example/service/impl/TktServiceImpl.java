package com.example.service.impl;

import com.example.mapper.TiankongtiMapper;
import com.example.pojo.Tiankongti;
import com.example.service.TktService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TktServiceImpl implements TktService {
    @Autowired
    TiankongtiMapper tiankongtiMapper;
    @Override
    public void saveTiankongti(Tiankongti tiankongti) {
        tiankongtiMapper.insertTkt(tiankongti);
    }
}
