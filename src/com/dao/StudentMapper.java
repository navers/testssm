package com.dao;

import com.bean.Student;

import java.util.List;


public interface StudentMapper {

    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int id);
    Student findStudentById(int id);
    List<Student> findLimitStudent(int start,int count);
    int findTotal();

}
