package com.zmu.pojo;

import java.util.Objects;

public class Course {
    private String cno;

    private String cname;

    private String cpno;

    private Short ccredit;

    private String cpname;

    public Course() {
    }

    public String getCpname() {
        return cpname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno == null ? null : cno.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getCpno() {
        return cpno;
    }

    public void setCpno(String cpno) {
        this.cpno = cpno == null ? null : cpno.trim();
    }

    public Short getCcredit() {
        return ccredit;
    }

    public void setCcredit(Short ccredit) {
        this.ccredit = ccredit;
    }

    @Override
    public boolean equals(Object obj) {
        //首先检查两个对象是否是同一个对象
        if (this == obj) {
            return true;
        }
        //然后检查传入的对象是否为 null 或者它们的类是否不同
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Course course = (Course) obj;
        //最后，比较两个课程对象的课程编号是否相等
        return Objects.equals(cno, course.cno);
    }

    @Override
    //重写equals方法时，也应该重写 hashCode 方法
    //以确保两个相等的对象具有相同的哈希码。
    public int hashCode() {
        return Objects.hash(cno);
    }
}