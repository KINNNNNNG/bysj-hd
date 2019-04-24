package com.example.mapper;

import com.example.pojo.Ksda;
import com.example.utils.MyMapper;

import java.util.List;

public interface KsdaMapper extends MyMapper<Ksda> {
    public List<Ksda> selectById(int id);
}