package com.leon.mybatisspring.pojo;

import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created on 27/10/2017.
 *
 * @author Xiaolei-Peng
 */

public class ProcedureBean {
    private String roleName = null;
    private int result = 0;
    private Date execDate;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Date getExecDate() {
        return execDate;
    }

    public void setExecDate(Date execDate) {
        this.execDate = execDate;
    }
}
