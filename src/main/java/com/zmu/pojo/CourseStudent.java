package com.zmu.pojo;

public class CourseStudent {
    private String sdept;
    private String sname;
    private Short grade;
    private String sno;

    public CourseStudent(String sno,String sdept, String sname, Short grade) {
        this.sno = sno;
        this.sdept = sdept;
        this.sname = sname;
        this.grade = grade;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }

    public Short getGrade() {
        return grade;
    }
}
