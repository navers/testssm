package com.service;

import com.bean.Grade;
import com.dao.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public void addGrade(Grade grade) {
        gradeMapper.addGrade(grade);
    }

    @Override
    public void updateGrade(Grade grade) {
        gradeMapper.updateGrade(grade);
    }

    @Override
    public void deleteGrade(int id) {
        gradeMapper.deleteGrade(id);
    }

    @Override
    public Grade findGradeById(int id) {
        return gradeMapper.findGradeById(id);
    }

    @Override
    public List<Grade> findAllGrade() {
        return gradeMapper.findAllGrade();
    }
}
