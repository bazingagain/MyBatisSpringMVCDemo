package com.leon.mybatisspring.controller;

import com.leon.mybatisspring.pojo.PageParams;
import com.leon.mybatisspring.pojo.RoleBean;
import com.leon.mybatisspring.pojo.TestParams;
import com.leon.mybatisspring.pojo.UserBean;
import com.leon.mybatisspring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 17/10/2017.
 *
 * @author Xiaolei-Peng
 */
@Controller
@RequestMapping("/role")
public class RoleContoller {

    @Autowired RoleService roleService;

    @RequestMapping(value = "/getRole")
//    @ResponseBody
    public ModelAndView getRole(@RequestParam("id") int id) {
        RoleBean role = roleService.getRole(id);
        ModelAndView mv = new ModelAndView("role/role");
        List<UserBean> list = role.getUserList();
        for (UserBean user : list) {
            System.out.println(user.getUserName());
        }
        mv.addObject("role", role);
        return mv;
    }

    @RequestMapping(value = "/findRoles")
    @ResponseBody
    public List<RoleBean> findRoles(@RequestParam("roleName") String roleName, @RequestParam("start") int start, @RequestParam("limit") int limit) {
        return roleService.findRoles(roleName, start, limit);
    }

    @RequestMapping(value = "/selectRolesByMap")
    @ResponseBody
    public List<RoleBean> selectRolesByMap(@RequestParam("roleName") String roleName) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageParams pageParams = new PageParams();
        pageParams.setUseFlag(true);
        pageParams.setCheckFlag(false);
        pageParams.setPage(2);
        pageParams.setPageSize(5);
        map.put("$pageParams", pageParams);
        map.put("roleName", "teacher");
        List list = roleService.selectRoleByMap(map);
        System.out.println("total item number:" + pageParams.getTotal());
        System.out.println("total page number:" + pageParams.getTotalPage());
        return list;
    }
    @RequestMapping(value = "/selectRolesByPOJO")
    @ResponseBody
    public List<RoleBean> selectRolesByPOJO(@RequestParam("roleName") String roleName) {
        TestParams pageParams = new TestParams();
        pageParams.setUseFlag(true);
        pageParams.setCheckFlag(false);
        pageParams.setPage(2);
        pageParams.setPageSize(5);
        pageParams.setRoleName("teacher");
        List list = roleService.selectRoleByPOJO(pageParams);
        System.out.println("total item number:" + pageParams.getTotal());
        System.out.println("total page number:" + pageParams.getTotalPage());
        return list;
    }

    @RequestMapping(value = "/selectRolesByAnnotation")
    @ResponseBody
    public List<RoleBean> selectRolesByAnnotation(@RequestParam("roleName") String roleName) {
        PageParams pageParams = new PageParams();
        pageParams.setUseFlag(true);
        pageParams.setCheckFlag(false);
        pageParams.setPage(1);
        pageParams.setPageSize(5);
        List list = roleService.selectRolesByAnnotation("teacher", pageParams);
        System.out.println("total item number:" + pageParams.getTotal());
        System.out.println("total page number:" + pageParams.getTotalPage());
        return list;
    }


    @RequestMapping(value = "/updateRole")
    @ResponseBody
    public int updateRole(RoleBean role) {
        return roleService.updateRole(role);
    }

    @RequestMapping(value = "/insertRole")
    @ResponseBody
    public int insertRole(RoleBean role) {
        return roleService.insertRole(role);
    }

    @RequestMapping(value = "/deleteRole")
    @ResponseBody
    public int deleteRole(@RequestParam("id") int id) {
        return roleService.deleteRole(id);
    }

}
