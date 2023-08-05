package com.zmu.pojo;

public class StudentBI {
    private String sno;
    private String sname;
    private String ssex;
    private Short sage;
    private String sdept;

    private int rank[];
    private float gpa;
    private int selectedCourse;
    private int totalCourse;
    private int notPassCourse;

    public StudentBI(String sno, String sname, String ssex, Short sage, String sdept, int[] rank, float gpa, int selectedCourse, int totalCourse, int notPassCourse) {
        this.sno = sno;
        this.sname = sname;
        this.ssex = ssex;
        this.sage = sage;
        this.sdept = sdept;
        this.rank = rank;
        this.gpa = gpa;
        this.selectedCourse = selectedCourse;
        this.totalCourse = totalCourse;
        this.notPassCourse = notPassCourse;
    }

    public StudentBI() {
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public Short getSage() {
        return sage;
    }

    public void setSage(Short sage) {
        this.sage = sage;
    }

    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }

    public int[] getRank() {
        return rank;
    }

    public void setRank(int[] rank) {
        this.rank = rank;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public int getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(int selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public int getTotalCourse() {
        return totalCourse;
    }

    public void setTotalCourse(int totalCourse) {
        this.totalCourse = totalCourse;
    }

    public int getNotPassCourse() {
        return notPassCourse;
    }

    public void setNotPassCourse(int notPassCourse) {
        this.notPassCourse = notPassCourse;
    }
}
