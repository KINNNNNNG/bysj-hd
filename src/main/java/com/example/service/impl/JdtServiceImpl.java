package com.example.service.impl;

import com.example.mapper.JiandatiMapper;
import com.example.pojo.Jiandati;
import com.example.service.JdtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JdtServiceImpl implements JdtService {
    @Autowired
    JiandatiMapper jiandatiMapper;
    @Override
    public void saveJandati(Jiandati jiandati) {
        jiandatiMapper.insertJdt(jiandati);
    }
}
