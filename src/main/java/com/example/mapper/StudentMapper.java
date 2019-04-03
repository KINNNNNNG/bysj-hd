package com.example.mapper;

import com.example.pojo.Student;
import com.example.utils.MyMapper;

import java.util.List;

public interface StudentMapper extends MyMapper<Student> {
    public List<Student> queryAll();
    public Student queryByUser(String user);
}