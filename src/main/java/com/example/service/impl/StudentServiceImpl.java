package com.example.service.impl;

import com.example.mapper.StudentMapper;
import com.example.pojo.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<Student> queryAll() {
        return studentMapper.queryAll();
    }

    @Override
    public Student queryByUser(String user) {
        return studentMapper.queryByUser(user);
    }

    @Override
    public void update(Student student) {
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public void delete(int id) {
        studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(Student student) {
        studentMapper.insertUseGeneratedKeys(student);
    }
}
