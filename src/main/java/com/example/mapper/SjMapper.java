package com.example.mapper;

import com.example.pojo.Sj;
import com.example.utils.MyMapper;

public interface SjMapper extends MyMapper<Sj> {
    public void saveSj(Sj sj);
}