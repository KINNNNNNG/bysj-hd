package com.example.service;

import com.example.pojo.Zongheti;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ZhtService {
    public void saveZongheti(Zongheti zongheti);
    public List<Zongheti> queryZhtAll();
    public List<Zongheti> queryZhtByUid();
    public void deleteZhtById(int Id);
    public void updataById(Zongheti zongheti);
    public void updataUid(Zongheti zongheti);
    public Zongheti selectById(int id);
}
