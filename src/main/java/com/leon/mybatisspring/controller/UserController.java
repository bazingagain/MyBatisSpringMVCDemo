package com.leon.mybatisspring.controller;

import com.leon.mybatisspring.pojo.UserBean;
import com.leon.mybatisspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created on 17/10/2017.
 *
 * @author Xiaolei-Peng
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public UserBean getUser(@RequestParam("id") int id) {
        return userService.getUser(id);
    }

    @RequestMapping("findUsers")
    @ResponseBody
    public List<UserBean> findUsers(@RequestParam("userName") String userName, @RequestParam("start") int start, @RequestParam("limit") int limit) {
        return userService.findUsers(userName, start, limit);
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public int updateUser(UserBean user) {
        return userService.updateUser(user);
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public int insertUser(UserBean user) {
        return userService.insertUser(user);
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public int deleteUser(@RequestParam("id") int id) {
        return userService.deleteUser(id);
    }


}
