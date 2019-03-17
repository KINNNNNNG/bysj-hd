package com.example.service.impl;

import com.example.mapper.ZonghetiMapper;
import com.example.pojo.Zongheti;
import com.example.service.ZhtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZhtServiceImpl implements ZhtService {
    @Autowired
    ZonghetiMapper zonghetiMapper;
    @Override
    public void saveZongheti(Zongheti zongheti) {
        zonghetiMapper.insertZht(zongheti);
    }
}
