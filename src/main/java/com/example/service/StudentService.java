package com.example.service;

import com.example.pojo.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    public List<Student> queryAll();

    public Student queryByUser(String user);

    public void update(Student student);

    public void delete(int id);

    public void save(Student student);
}
