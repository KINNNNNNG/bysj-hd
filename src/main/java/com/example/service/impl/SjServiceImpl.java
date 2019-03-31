package com.example.service.impl;

import com.example.mapper.SjMapper;
import com.example.pojo.Sj;
import com.example.service.SjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SjServiceImpl implements SjService {
    @Autowired
    SjMapper sjMapper;
    @Override
    public int saveSj(Sj sj) {
        int sj1 = 0;
        sj1 = sjMapper.saveSj(sj);
        return sj.getId();
    }

    @Override
    public Sj selectById(int id) {
        return sjMapper.selectById(id);
    }
}
