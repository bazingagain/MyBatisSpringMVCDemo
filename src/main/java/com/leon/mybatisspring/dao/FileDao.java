package com.leon.mybatisspring.dao;

import com.leon.mybatisspring.pojo.FileBean;
import org.springframework.stereotype.Repository;

/**
 * Created on 20/10/2017.
 *
 * @author Xiaolei-Peng
 */
@Repository
public interface FileDao {
    int insertFile(FileBean file);
}
