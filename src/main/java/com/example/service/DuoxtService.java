package com.example.service;

import com.example.pojo.Duoxuanti;
import org.springframework.stereotype.Service;

@Service
public interface DuoxtService {
    public void saveDuoxuanti(Duoxuanti duoxuanti) throws Exception;

}
