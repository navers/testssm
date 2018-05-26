package test.service;

import com.bean.Grade;
import com.bean.Student;
import com.service.GradeService;
import com.service.StudentService;
import com.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:resources/spring-mybatis.xml")
public class TestStudentService {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GradeService gradeService;


    @Test
    public void testadd(){
        /*Grade grade = new Grade();
        grade.setName("一三班");
        gradeService.addGrade(grade);*/
        for (int i=0;i<10;i++) {
            Student stu = new Student();
            //stu.setId(3);
            stu.setName("柳中惠"+i);
            stu.setAge(16);
            stu.setSex("male");
            stu.setGid(1);
            Calendar c = Calendar.getInstance();
            c.set(1995,6,11+i);
            Date date = c.getTime();
            stu.setBirthday(DateUtil.d2t(date));
            studentService.addStudent(stu);
        }
        //System.out.println(stu.getId());

        /*List<Student> students = studentService.findAllStudent();
        System.out.println(students);*/
    }

}
