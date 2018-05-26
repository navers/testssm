package com.controller;

import com.bean.Grade;
import com.bean.Student;
import com.service.GradeService;
import com.service.StudentService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class ShowStudentController {

    @Autowired
    private StudentService stuService;
    @Autowired
    private GradeService gradeService;

    @RequestMapping("/listAllStudent.do")
    public ModelAndView listAllStudent(@RequestParam(value="pn",required = false,defaultValue = "1") int pn){
        ModelAndView mv = new ModelAndView();
        Page page  = new Page((pn-1)*5,5);
        page.setTotal(stuService.findTotal());
        List<Student> stus = stuService.findLimitStudent(page.getStart(),5);
        List<Grade> grades = gradeService.findAllGrade();
        mv.addObject("grades",grades);
        mv.addObject("stus",stus);
        mv.addObject("page",page);
        mv.setViewName("listAllStudent");
        return mv;
    }

    @RequestMapping("/editStudent.do")
    public String editStudent(Student student){
        if (null==student.getName()||""==student.getName().trim()) {
            return "/WEB-INF/jsp/error.jsp?message=姓名不能为空";
        }
        if (0==student.getGid()) {
            return "/WEB-INF/jsp/error.jsp?message=请选择班级";
        }
        stuService.updateStudent(student);
        return "redirect:listAllStudent.do";
    }

    @RequestMapping("/addStudent.do")
    public String addStudent(Student student){
        if (null==student.getName()||""==student.getName().trim()) {
            return "/WEB-INF/jsp/error.jsp?message=姓名不能为空";
        }
        if (0==student.getGid()) {
            return "/WEB-INF/jsp/error.jsp?message=请选择班级";
        }
        stuService.addStudent(student);
        return "redirect:listAllStudent.do";
    }

    @RequestMapping("/deleteStudent.do")
    public void deleteStudent(HttpServletResponse response,int id){
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stuService.deleteStudent(id);
        out.write("success");
        out.close();
    }
}
