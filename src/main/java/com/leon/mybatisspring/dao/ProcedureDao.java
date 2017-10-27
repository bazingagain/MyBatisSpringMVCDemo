package com.leon.mybatisspring.dao;

import com.leon.mybatisspring.pojo.ProcedureBean;
import org.springframework.stereotype.Repository;

/**
 * Created on 27/10/2017.
 *
 * @author Xiaolei-Peng
 */
@Repository
public interface ProcedureDao {
    void count(ProcedureBean procedureBean);
}
