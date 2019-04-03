package com.example.mapper;

import com.example.pojo.Panduanti;
import com.example.utils.MyMapper;

import java.util.List;

public interface PanduantiMapper extends MyMapper<Panduanti> {
    public void insertPdt(Panduanti panduanti);
    public List<Panduanti> queryPdtAll();
    public List<Panduanti> queryPdtByUid();
    public void deletePdtById(int id);
    public void updataById(Panduanti panduanti);
    public void updataUid(Panduanti panduanti);
    public Panduanti selectById(int id);
    public int selectNum();
}