package com.leon.mybatisspring.service;

import com.leon.mybatisspring.pojo.RoleBean;

import java.util.List;

/**
 * Created on 17/10/2017.
 *
 * @author Xiaolei-Peng
 */
public interface RoleService {
    RoleBean getRole(Integer id);
    List<RoleBean> findRoles(String roleName, int start, int limit);
    int insertRole(RoleBean roleBean);
    int updateRole(RoleBean roleBean);
    int deleteRole(Integer id);
}
