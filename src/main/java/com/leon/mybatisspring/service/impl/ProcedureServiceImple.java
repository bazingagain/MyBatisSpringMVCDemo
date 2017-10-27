package com.leon.mybatisspring.service.impl;

import com.leon.mybatisspring.dao.ProcedureDao;
import com.leon.mybatisspring.pojo.ProcedureBean;
import com.leon.mybatisspring.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 27/10/2017.
 *
 * @author Xiaolei-Peng
 */
@Service
public class ProcedureServiceImple implements ProcedureService {

    @Autowired
    private ProcedureDao procedureDao;

    public void count(ProcedureBean procedureBean) {
        procedureDao.count(procedureBean);
    }
}
