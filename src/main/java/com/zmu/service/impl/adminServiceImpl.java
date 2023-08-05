package com.zmu.service.impl;

import com.zmu.mapper.CourseMapper;
import com.zmu.mapper.SCMapper;
import com.zmu.mapper.StudentMapper;
import com.zmu.pojo.*;
import com.zmu.service.adminService;
import com.zmu.service.courseService;
import com.zmu.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class adminServiceImpl implements adminService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private SCMapper scMapper;
    @Resource
    private CourseMapper courseMapper;
    //使用其他类的方法用注入来声明类
    @Resource
    private studentService studentService;
    @Resource
    private courseService courseService;

    @Override
    @Transactional(readOnly = true)
    //根据sc两个外键进行多表连接
    public List<SC> getSC(String sno, String cno) {
        SCExample scExample = new SCExample();
        //根据sno获取sc信息
        if (sno != null) {
            scExample.createCriteria()
                    .andSnoEqualTo(sno);
        }
        //根据cno获取sc信息
        else if (cno != null) {
            scExample.createCriteria()
                    .andCnoEqualTo(cno);
        }

        return scMapper.selectByExample(scExample);
    }

    @Override
    @Transactional(readOnly = true)
    public Course getCourse(String cno) {
        return courseMapper.selectByPrimaryKey(cno);
    }

    @Override
    public List<Student> studentList() {
        return studentMapper.selectByExample(null);
    }

    @Override
    public List<StudentBI> studentBasicInfo() {
        List<StudentBI> studentBIList = new ArrayList<>();

        String sno;
        String sname;
        String ssex;
        Short sage;
        String sdept;

        int rank[];
        float gpa;
        int selectedCourse;
        int totalCourse = courseMapper.selectByExample(null).size();
        int notPassCourse;

        List<Student> students = studentMapper.selectByExample(null);
        //获取所有学生的基本信息
        for (Student student : students) {
            sno = student.getSno();
            sname = student.getSname();
            ssex = student.getSsex();
            sage = student.getSage();
            sdept = student.getSdept();

            rank = studentService.getRank(student.getSno());
            gpa = studentService.getGPA(student.getSno());
            selectedCourse = studentService.selectedCourse(student.getSno()).size();
            notPassCourse = studentService.notPass(student.getSno()).size();

            studentBIList.add(new StudentBI(sno, sname, ssex, sage, sdept, rank, gpa, selectedCourse, totalCourse, notPassCourse));
        }

        return studentBIList;
    }

    @Override
    public List<Course> courseList() {
        return courseMapper.selectByExample(null);
    }

    @Override
    public List<CourseBI> courseBasicInfo() {
        List<CourseBI> courseBIList = new ArrayList<>();

        String cno;
        String cname;
        String cpno;
        Short ccredit;
        String cpname;

        int rank[];
        float avg;
        int selectedStudent;
        int totalCourse = studentMapper.selectByExample(null).size();
        int notPassStudent;

        List<Course> courses = courseMapper.selectByExample(null);

        for (Course cours : courses) {
            cno = cours.getCno();
            cname = cours.getCname();
            cpno = cours.getCpno();
            ccredit = cours.getCcredit();
            cpname = cours.getCpname();

            rank = courseService.getRank(cours.getCno());
            avg = courseService.getAvg(cours.getCno());
            selectedStudent = courseService.selectedStudent(cours.getCno()).size();
            notPassStudent = courseService.notPass(cours.getCno());

            courseBIList.add(new CourseBI(cno, cname, cpno, ccredit, cpname, rank, avg, selectedStudent, totalCourse, notPassStudent));

        }

        return courseBIList;
    }

    @Override
    public int delete(String PrimaryKey) {
        return studentMapper.deleteByPrimaryKey(PrimaryKey) | courseMapper.deleteByPrimaryKey(PrimaryKey);
    }

    @Override
    public int deleteSC(SCDelete scDelete) {
        List<Student> students = scDelete.getStudentList();
        List<Course> courses = scDelete.getCourseList();
        int i = 0;

        for (Student stu : students) {
            for (Course cours : courses) {
                i += scMapper.deleteByPrimaryKey(new SCKey(stu.getSno(),cours.getCno()));
            }
        }
        return i;
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public int addCourse(Course course) {
        return courseMapper.insert(course);
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public int updateCourse(Course course) {
        return courseMapper.updateByPrimaryKeySelective(course);
    }

    @Override
    public int updateSC(SC sc) {
        return scMapper.updateByPrimaryKey(sc);
    }

    @Override
    public List<StudentCourse> studentCourse(String sno) {
        List<StudentCourse> studentCourse = new ArrayList<>();

        //根据sno获取sc和course表信息
        List<SC> scs = getSC(sno, null);
        List<Course> courses = new ArrayList<>();
        for (SC sc : scs) {
            courses.add(getCourse(sc.getCno()));
        }

        //获取选课信息
        for (int i = 0; i < scs.size(); i++) {
            String cno = courses.get(i).getCno();
            String cname = courses.get(i).getCname();
            Short grade = scs.get(i).getGrade();
            Short ccredit = courses.get(i).getCcredit();

            studentCourse.add(new StudentCourse(cno, cname, grade, ccredit));
        }

        return studentCourse;
    }
}
