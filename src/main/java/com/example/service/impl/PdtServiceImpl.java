package com.example.service.impl;

import com.example.mapper.PanduantiMapper;
import com.example.pojo.Panduanti;
import com.example.service.PdtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdtServiceImpl implements PdtService {
    @Autowired
    PanduantiMapper panduantiMapper;
    @Override
    public void savePanduanti(Panduanti panduanti) {
        panduantiMapper.insertPdt(panduanti);
    }

    @Override
    public List<Panduanti> queryPdtAll() {
        return panduantiMapper.queryPdtAll();
    }

    @Override
    public List<Panduanti> queryPdtByUid() {
        return panduantiMapper.queryPdtByUid();
    }

    @Override
    public void deletePdtById(int Id) {
        panduantiMapper.deletePdtById(Id);
    }

    @Override
    public void updataById(Panduanti panduanti) {
        panduantiMapper.updataById(panduanti);
    }

    @Override
    public void updataUid(Panduanti panduanti) {
        panduantiMapper.updataUid(panduanti);
    }

    @Override
    public Panduanti selectById(int id) {
        return panduantiMapper.selectById(id);
    }

    @Override
    public int selectNum() {
        return panduantiMapper.selectNum();
    }

}
