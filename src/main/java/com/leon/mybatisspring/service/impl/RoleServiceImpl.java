package com.leon.mybatisspring.service.impl;

import com.leon.mybatisspring.dao.RoleDao;
import com.leon.mybatisspring.pojo.RoleBean;
import com.leon.mybatisspring.service.RoleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created on 17/10/2017.
 *
 * @author Xiaolei-Peng
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public RoleBean getRole(Integer id) {
        return roleDao.getRole(id);
    }

    public List<RoleBean> findRoles(String roleName, int start, int limit) {
        return roleDao.findRoles(roleName, new RowBounds(start, limit));
    }

    public List<RoleBean> selectRoleByMap(Map<String, Object> params) {
        return roleDao.selectRoleByMap(params);
    }


    public int insertRole(RoleBean roleBean) {
        return roleDao.insertRole(roleBean);
    }

    public int updateRole(RoleBean roleBean) {
        return roleDao.updateRole(roleBean);
    }

    public int deleteRole(Integer id) {
        return roleDao.deleteRole(id);
    }
}
