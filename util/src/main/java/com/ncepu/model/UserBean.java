package com.ncepu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wengym
 * @version 1.0
 * @desc TODO
 * @date 2021/11/17 9:28
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserBean extends ReqDTO {
    private Boolean bb;
    private boolean bb1;
    private Byte by;
    private byte by1;
    private Character cc;
    private char cc1;
    private Short sh;
    private short sh1;
    private Integer in;
    private int in1;
    private Long lg;
    private long lg1;
    private Float ft;
    private float ft1;
    private Double db;
    private double db1;

    private String userName;
    private String password;

    @Override
    public String toString() {
        return "UserBean{" +
                "bb=" + bb +
                ", bb1=" + bb1 +
                ", by=" + by +
                ", by1=" + by1 +
                ", cc=" + cc +
                ", cc1=" + cc1 +
                ", sh=" + sh +
                ", sh1=" + sh1 +
                ", in=" + in +
                ", in1=" + in1 +
                ", lg=" + lg +
                ", lg1=" + lg1 +
                ", ft=" + ft +
                ", ft1=" + ft1 +
                ", db=" + db +
                ", db1=" + db1 +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Boolean getBb() {
        return bb;
    }

    public void setBb(Boolean bb) {
        this.bb = bb;
    }

    public boolean isBb1() {
        return bb1;
    }

    public void setBb1(boolean bb1) {
        this.bb1 = bb1;
    }

    public Byte getBy() {
        return by;
    }

    public void setBy(Byte by) {
        this.by = by;
    }

    public byte getBy1() {
        return by1;
    }

    public void setBy1(byte by1) {
        this.by1 = by1;
    }

    public Character getCc() {
        return cc;
    }

    public void setCc(Character cc) {
        this.cc = cc;
    }

    public char getCc1() {
        return cc1;
    }

    public void setCc1(char cc1) {
        this.cc1 = cc1;
    }

    public Short getSh() {
        return sh;
    }

    public void setSh(Short sh) {
        this.sh = sh;
    }

    public short getSh1() {
        return sh1;
    }

    public void setSh1(short sh1) {
        this.sh1 = sh1;
    }

    public Integer getIn() {
        return in;
    }

    public void setIn(Integer in) {
        this.in = in;
    }

    public int getIn1() {
        return in1;
    }

    public void setIn1(int in1) {
        this.in1 = in1;
    }

    public Long getLg() {
        return lg;
    }

    public void setLg(Long lg) {
        this.lg = lg;
    }

    public long getLg1() {
        return lg1;
    }

    public void setLg1(long lg1) {
        this.lg1 = lg1;
    }

    public Float getFt() {
        return ft;
    }

    public void setFt(Float ft) {
        this.ft = ft;
    }

    public float getFt1() {
        return ft1;
    }

    public void setFt1(float ft1) {
        this.ft1 = ft1;
    }

    public Double getDb() {
        return db;
    }

    public void setDb(Double db) {
        this.db = db;
    }

    public double getDb1() {
        return db1;
    }

    public void setDb1(double db1) {
        this.db1 = db1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
