package com.example.service;

import com.example.pojo.Duoxuanti;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DuoxtService {
    public void saveDuoxuanti(Duoxuanti duoxuanti) throws Exception;

    public List<Duoxuanti> queryDxtAll();

    public void deleteDxtById(int Id);

    public void updataById(Duoxuanti duoxuanti);

    public void updataUid(Duoxuanti duoxuanti);

    public List<Duoxuanti> queryDxtByUid();

    public Duoxuanti selectById(int id);

    public int selectNum();
}
