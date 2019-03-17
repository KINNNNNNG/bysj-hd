package com.example.service.impl;

import com.example.mapper.PanduantiMapper;
import com.example.pojo.Panduanti;
import com.example.service.PdtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdtServiceImpl implements PdtService {
    @Autowired
    PanduantiMapper panduantiMapper;
    @Override
    public void savePanduanti(Panduanti panduanti) {
        panduantiMapper.insertPdt(panduanti);
    }
}
