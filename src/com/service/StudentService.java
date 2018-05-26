package com.service;

import com.bean.Student;

import java.util.List;

public interface StudentService {

    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int id);
    Student findStudentById(int id);
    List<Student> findLimitStudent(int start,int count);
    List<Student> findAllStudent();
    int findTotal();
}
