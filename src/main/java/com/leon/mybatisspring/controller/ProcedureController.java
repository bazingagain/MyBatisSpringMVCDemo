package com.leon.mybatisspring.controller;

import com.leon.mybatisspring.pojo.ProcedureBean;
import com.leon.mybatisspring.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;

/**
 * Created on 27/10/2017.
 *
 * @author Xiaolei-Peng
 */
@Controller
@RequestMapping("/procedure")
public class ProcedureController {

    @Autowired
    private ProcedureService procedureService;

    @RequestMapping("/count")
    @ResponseBody
    public void count(@RequestParam("roleName") String roleName) {
        ProcedureBean bean = new ProcedureBean();
        bean.setRoleName(roleName);
        procedureService.count(bean);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(bean.getRoleName() + " " + bean.getResult() + " " + format.format(bean.getExecDate()));
    }

}
