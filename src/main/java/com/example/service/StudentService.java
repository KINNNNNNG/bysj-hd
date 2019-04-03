package com.example.service;

import com.example.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    public List<Student> queryAll();
    public Student queryByUser(String user);
}
