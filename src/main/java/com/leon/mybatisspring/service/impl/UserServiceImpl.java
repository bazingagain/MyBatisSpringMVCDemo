package com.leon.mybatisspring.service.impl;

import com.leon.mybatisspring.dao.UserDao;
import com.leon.mybatisspring.pojo.UserBean;
import com.leon.mybatisspring.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 17/10/2017.
 *
 * @author Xiaolei-Peng
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public UserBean getUser(Integer id) {
        return userDao.getUser(id);
    }

    public List<UserBean> findUsers(String userName, int start, int limit) {
        return userDao.findUsers(userName, new RowBounds(start, limit));
    }

    public int insertUser(UserBean userBean) {
        return userDao.insertUser(userBean);
    }

    public int updateUser(UserBean userBean) {
        return userDao.updateUser(userBean);
    }

    public int deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }
}
