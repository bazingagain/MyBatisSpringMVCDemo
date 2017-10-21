package com.leon.mybatisspring.pojo;

/**
 * Created on 21/10/2017.
 *
 * @author Xiaolei-Peng
 */
public class TestParams extends PageParams {
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    private String roleName;

}
