package com.zmu.service;

import com.zmu.pojo.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface adminService {
    List<SC> getSC(String sno, String cno);

    Course getCourse(String cno);

    List<Student> studentList();

    List<StudentBI> studentBasicInfo();

    List<Course> courseList();

    List<CourseBI> courseBasicInfo();

    int delete(String PrimaryKey);

    int deleteSC(SCDelete scDelete);

    int addStudent(Student student);

    int addCourse(Course course);

    int updateStudent(Student student);

    int updateCourse(Course course);

    int updateSC(SC sc);

    List<StudentCourse> studentCourse(String sno);
}
