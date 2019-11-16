package com.example.service;

import com.example.pojo.Ksda;

import java.util.List;

public interface KsdaService {
    public void saveKsda(Ksda ksda);

    public List<Ksda> slectById(int id);
}
