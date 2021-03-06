package com.leon.mybatisspring.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created on 17/10/2017.
 *
 * @author Xiaolei-Peng
 */
public class UserBean {
    private Integer id;
    private String userName;
    private Date birthday;
    private String sex;
    private String email;
    private String note;
    private List<RoleBean> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<RoleBean> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleBean> roleList) {
        this.roleList = roleList;
    }
}
