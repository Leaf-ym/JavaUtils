package com.ncepu.model;

import com.ncepu.anotation.DatabaseField;

import java.util.Date;

/**
 * @author wengym
 * @version 1.0
 * @desc TODO
 * @date 2021/11/17 9:28
 */
public class NewUserBean extends ReqDTO {
    @DatabaseField
    private Integer id;
    @DatabaseField
    private int mlId;
    @DatabaseField
    private String userName;
    @DatabaseField
    private String password;
    @DatabaseField
    private String fillDate;
    @DatabaseField
    private Date updateDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMlId() {
        return mlId;
    }

    public void setMlId(int mlId) {
        this.mlId = mlId;
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

    public String getFillDate() {
        return fillDate;
    }

    public void setFillDate(String fillDate) {
        this.fillDate = fillDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
