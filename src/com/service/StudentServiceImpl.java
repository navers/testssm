package com.service;

import com.bean.Student;
import com.dao.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void addStudent(Student student) {
        studentMapper.addStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentMapper.deleteStudent(id);
    }

    @Override
    public Student findStudentById(int id) {
        return studentMapper.findStudentById(id);
    }

    @Override
    public List<Student> findLimitStudent(int start,int count) {
        return studentMapper.findLimitStudent(start,count);
    }

    @Override
    public List<Student> findAllStudent() {
        return findLimitStudent(0,Short.MAX_VALUE);
    }

    @Override
    public int findTotal() {
        return studentMapper.findTotal();
    }
}
