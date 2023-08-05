package com.zmu.pojo;

public class CourseBI {
    private String cno;
    private String cname;
    private String cpno;
    private Short ccredit;
    private String cpname;

    private int rank[];
    private float avg;
    private int selectedStudent;
    private int totalStudent;
    private int notPassStudent;

    public CourseBI() {
    }

    public CourseBI(String cno, String cname, String cpno, Short ccredit, String cpname, int[] rank, float avg, int selectedStudent, int totalStudent, int notPassStudent) {
        this.cno = cno;
        this.cname = cname;
        this.cpno = cpno;
        this.ccredit = ccredit;
        this.cpname = cpname;
        this.rank = rank;
        this.avg = avg;
        this.selectedStudent = selectedStudent;
        this.totalStudent = totalStudent;
        this.notPassStudent = notPassStudent;
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

    public String getCpno() {
        return cpno;
    }

    public void setCpno(String cpno) {
        this.cpno = cpno;
    }

    public Short getCcredit() {
        return ccredit;
    }

    public void setCcredit(Short ccredit) {
        this.ccredit = ccredit;
    }

    public String getCpname() {
        return cpname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname;
    }

    public int[] getRank() {
        return rank;
    }

    public void setRank(int[] rank) {
        this.rank = rank;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }

    public int getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(int selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public int getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(int totalStudent) {
        this.totalStudent = totalStudent;
    }

    public int getNotPassStudent() {
        return notPassStudent;
    }

    public void setNotPassStudent(int notPassStudent) {
        this.notPassStudent = notPassStudent;
    }
}
