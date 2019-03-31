package com.example.mapper;

import com.example.pojo.Danxuanti;
import com.example.pojo.User;
import com.example.utils.MyMapper;

import java.util.List;

public interface DanxuantiMapper extends MyMapper<Danxuanti> {
    public void insertXzt(Danxuanti danxuanti);
    public List<Danxuanti> selectXztAll();
    public void deleteXztById(int id);
    public void updataById(Danxuanti danxuanti);
    public void updataUid(Danxuanti danxuanti);
    public List<Danxuanti> selectByUid();
    public Danxuanti selectById(int id);
}