package com.example.service;

import com.example.pojo.Jiandati;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JdtService {
    public void saveJandati(Jiandati jiandati);
    public List<Jiandati> queryJdtAll();
    public List<Jiandati> queryJdtByUid();
    public void deleteJdtById(int Id);
    public void updataById(Jiandati jiandati);
    public void updataUid(Jiandati jiandati);
}
