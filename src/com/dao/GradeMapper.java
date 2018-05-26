package com.dao;

import com.bean.Grade;

import java.util.List;

public interface GradeMapper {

    void addGrade(Grade grade);
    void updateGrade(Grade grade);
    void deleteGrade(int id);
    Grade findGradeById(int id);
    List<Grade> findAllGrade();
}
