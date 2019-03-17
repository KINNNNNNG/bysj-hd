package com.example.service.impl;

import com.example.mapper.DuoxuantiMapper;
import com.example.pojo.Duoxuanti;
import com.example.service.DuoxtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DuoxtServiceImpl implements DuoxtService {
    @Autowired
    DuoxuantiMapper duoxuantiMapper;
    @Override
    public void saveDuoxuanti(Duoxuanti duoxuanti) throws Exception {
        duoxuantiMapper.insertDuoxt(duoxuanti);
    }
}
