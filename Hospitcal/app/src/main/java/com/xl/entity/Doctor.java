package com.xl.entity;

/**
 * Created by Administrator on 2017/11/10 0010.
 */

public class Doctor {
    private int did;
    private String dname;
    private String droom;
    private  String dtitle;
    private String dhead;
    private String img;

    public Doctor(int did, String dname, String droom, String dtitle, String dhead, String img) {
        this.did = did;
        this.dname = dname;
        this.droom = droom;
        this.dtitle = dtitle;
        this.dhead = dhead;
        this.img = img;
    }
    public Doctor() {

    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDroom() {
        return droom;
    }

    public void setDroom(String droom) {
        this.droom = droom;
    }

    public String getDtitle() {
        return dtitle;
    }

    public void setDtitle(String dtitle) {
        this.dtitle = dtitle;
    }

    public String getDhead() {
        return dhead;
    }

    public void setDhead(String dhead) {
        this.dhead = dhead;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                ", droom='" + droom + '\'' +
                ", dtitle='" + dtitle + '\'' +
                ", dhead='" + dhead + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
