package com.example.service;

import com.example.pojo.Sj;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SjService {
    public int saveSj(Sj sj);

    public Sj selectById(int id);

    public List<Sj> selectAll();

    public void deleteById(int id);
}
