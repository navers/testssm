package com.service;

import com.bean.Grade;

import java.util.List;

public interface GradeService {

    void addGrade(Grade grade);
    void updateGrade(Grade grade);
    void deleteGrade(int id);
    Grade findGradeById(int id);
    List<Grade> findAllGrade();

}
