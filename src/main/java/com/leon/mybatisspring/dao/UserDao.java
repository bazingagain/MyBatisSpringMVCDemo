package com.leon.mybatisspring.dao;

import com.leon.mybatisspring.pojo.UserBean;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 17/10/2017.
 *
 * @author Xiaolei-Peng
 */
@Repository
public interface UserDao {
    int insertUser(UserBean role);
    int updateUser(UserBean role);
    int deleteUser(Integer id);
    UserBean getUser(Integer id);
    List<UserBean> findUsers(String roleName, RowBounds rowBounds);
    List<UserBean> findUserByRoleId(Integer roleId);
}
