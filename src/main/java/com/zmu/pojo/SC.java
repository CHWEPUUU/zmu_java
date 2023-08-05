package com.zmu.pojo;

public class SC extends SCKey {
    private Short grade;

    private String status;

    public SC() {
        super();
    }

    public SC(Short grade, String status) {
        this.grade = grade;
        this.status = status;
    }

    public Short getGrade() {
        return grade;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}