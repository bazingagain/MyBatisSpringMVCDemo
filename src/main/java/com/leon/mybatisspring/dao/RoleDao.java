package com.leon.mybatisspring.dao;

import com.leon.mybatisspring.pojo.RoleBean;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 17/10/2017.
 *
 * @author Xiaolei-Peng
 */

@Repository
public interface RoleDao {
    int insertRole(RoleBean role);
    int updateRole(RoleBean role);
    int deleteRole(Integer id);
    RoleBean getRole(Integer id);
    List<RoleBean> findRoles(String roleName, RowBounds rowBounds);
}
