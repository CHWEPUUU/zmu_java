package com.zmu.pojo;

public class StudentCourse {
    private String cno;
    private String cname;
    private Short grade;
    private Short ccredit;

    public StudentCourse(String cno,String cname, Short grade, Short ccredit) {
        this.cno = cno;
        this.cname = cname;
        this.grade = grade;
        this.ccredit = ccredit;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Short getGrade() {
        return grade;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }

    public Short getCcredit() {
        return ccredit;
    }

    public void setCcredit(Short ccredit) {
        this.ccredit = ccredit;
    }
}
