package com.el_rangxanh.test_11.cauhoi;

import java.io.Serializable;

public class CauHoi implements Serializable {
    private int _id;
    private String cauhoi;
    private String a;
    private String b;
    private String c;
    private String d;
    private String ketqua;
    private int sttktra;
    private String mon;
    private String anh;
    private String traloi = "";
    public int choiceId = -1; // biến giúp xử lí thao tác chọn ở radioGroup (phần checkedid)

    public String getTraloi() {
        return traloi;
    }

    public void setTraloi(String traloi) {
        this.traloi = traloi;
    }

    public CauHoi(int _id, String cauhoi, String b, String a, String c, String d, String ketqua, int sttktra, String mon, String anh, String traloi) {
        this._id = _id;
        this.cauhoi = cauhoi;
        this.b = b;
        this.a = a;
        this.c = c;
        this.d = d;
        this.ketqua = ketqua;
        this.sttktra = sttktra;
        this.mon = mon;
        this.anh = anh;
        this.traloi = traloi;
    }

    public CauHoi() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getKetqua() {
        return ketqua;
    }

    public void setKetqua(String ketqua) {
        this.ketqua = ketqua;
    }

    public int getSttktra() {
        return sttktra;
    }

    public void setSttktra(int sttktra) {
        this.sttktra = sttktra;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}