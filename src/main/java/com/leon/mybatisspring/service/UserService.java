package com.leon.mybatisspring.service;

import com.leon.mybatisspring.pojo.UserBean;

import java.util.List;

/**
 * Created on 17/10/2017.
 *
 * @author Xiaolei-Peng
 */
public interface UserService {
    UserBean getUser(Integer id);
    List<UserBean> findUsers(String userName, int start, int limit);
    int insertUser(UserBean userBean);
    int updateUser(UserBean userBean);
    int deleteUser(Integer id);

}
