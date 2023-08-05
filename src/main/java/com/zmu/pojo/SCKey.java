package com.zmu.pojo;

public class SCKey {
    private String sno;

    private String cno;

    public SCKey() {
    }

    public SCKey(String sno, String cno) {
        this.sno = sno;
        this.cno = cno;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno == null ? null : cno.trim();
    }
}