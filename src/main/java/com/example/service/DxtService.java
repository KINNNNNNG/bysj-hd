package com.example.service;


import com.example.pojo.Danxuanti;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DxtService {
    public void saveDanxuanti(Danxuanti danxuanti) throws Exception;

    public void updateDanxuanti(Danxuanti danxuanti);

    public void updataUid(Danxuanti danxuanti);

    public void deleteDanxuanti(int danxuantiId);

    public List<Danxuanti> selectByUid();

    public Danxuanti queryDanxuantiById(String danxuantiId);

    public List<Danxuanti> queryDanxuantiAll();

    public List<Danxuanti> queryDanxuantiList(Danxuanti danxuanti);

    public List<Danxuanti> queryDanxuantiListPaged(Danxuanti danxuanti, Integer page, Integer pageSize);

    public Danxuanti queryDanxuantiByIdCustom(String danxuantiId);

    public void saveUserTransactional(Danxuanti danxuanti);
    public Danxuanti selectById(int id);
    public int selectNum();
}
