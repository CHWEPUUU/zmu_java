package com.zmu.service;

import com.zmu.pojo.*;

import java.util.List;

/**
 * @Author:PU
 * @ClassName:studentService
 */
public interface studentService {
    List<Student> studentList();

    List<Course> courseList();

    List<SC> getSC(String sno, String cno);

    int delete(String sno);

    int save(Student student);

    int update(Student student);

    List<StudentCourse> selectedCourse(String sno);

    List<CourseStudent> selectedStudent(String cno);

    float getGPA(String sno);

    int[] getRank(String sno);

    List<StudentCourse> notPass(String sno);

    int retake(String sno, String cno);

    List<Course> notSelect(String sno);

    int take(String sno, String cno);
}
