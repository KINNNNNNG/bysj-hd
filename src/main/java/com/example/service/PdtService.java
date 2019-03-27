package com.example.service;

import com.example.pojo.Panduanti;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PdtService {
    public void savePanduanti(Panduanti panduanti);
    public List<Panduanti> queryPdtAll();
    public List<Panduanti> queryPdtByUid();
    public void deletePdtById(int Id);
    public void updataById(Panduanti panduanti);
    public void updataUid(Panduanti panduanti);
}
