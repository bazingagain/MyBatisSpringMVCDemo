package com.leon.mybatisspring.dao;

import com.leon.mybatisspring.pojo.PageParams;
import com.leon.mybatisspring.pojo.RoleBean;
import com.leon.mybatisspring.pojo.TestParams;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    List<RoleBean> findRoleByUserId(Integer userId);
    List<RoleBean> selectRoleByPOJO(TestParams params);
    List<RoleBean> selectRoleByMap(Map<String, Object> params);
    List<RoleBean> selectRolesByAnnotation(@Param("roleName") String roleName, @Param("$pageParams") PageParams params);
}
