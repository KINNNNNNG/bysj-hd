package com.example.service;

import com.example.pojo.Sj;
import org.springframework.stereotype.Service;

@Service
public interface SjService {
    public int saveSj(Sj sj);
    public Sj selectById(int id);
}
