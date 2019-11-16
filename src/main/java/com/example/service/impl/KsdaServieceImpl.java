package com.example.service.impl;

import com.example.mapper.KsdaMapper;
import com.example.pojo.Ksda;
import com.example.service.KsdaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KsdaServieceImpl implements KsdaService {
    @Autowired
    KsdaMapper ksdaMapper;

    @Override
    public void saveKsda(Ksda ksda) {
        ksdaMapper.insert(ksda);
    }

    @Override
    public List<Ksda> slectById(int id) {
        return ksdaMapper.selectById(id);
    }

}
