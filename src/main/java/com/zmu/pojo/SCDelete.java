package com.zmu.pojo;

import java.util.List;

public class SCDelete {
    List<Student> studentList;
    List<Course> courseList;

    public SCDelete(List<Student> studentList, List<Course> courseList) {
        this.studentList = studentList;
        this.courseList = courseList;
    }

    public SCDelete() {
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
