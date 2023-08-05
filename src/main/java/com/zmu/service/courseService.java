package com.zmu.service;

import com.zmu.pojo.*;

import java.util.List;

public interface courseService {
    List<CourseStudent> selectedStudent(String cno);

    List<StudentCourse> selectedCourse(String sno);

    List<SC> getSC(String sno, String cno);

    float getAvg(String cno);

    int[] getRank(String cno);

    int notPass(String cno);

    int handleRetake(SC sc);

    int handleTake(SC sc);

    List<Student> takeCourse(String cno);

    List<Student> retakeCourse(String cno);
}
