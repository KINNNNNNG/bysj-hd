package com.example.mapper;

import com.example.pojo.Sj;
import com.example.utils.MyMapper;

public interface SjMapper extends MyMapper<Sj> {
    int saveSj(Sj sj);
    Sj selectById(int id);
}