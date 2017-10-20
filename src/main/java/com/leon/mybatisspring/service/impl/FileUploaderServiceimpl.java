package com.leon.mybatisspring.service.impl;

import com.leon.mybatisspring.dao.FileDao;
import com.leon.mybatisspring.pojo.FileBean;
import com.leon.mybatisspring.service.FileUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 20/10/2017.
 *
 * @author Xiaolei-Peng
 */
@Service
public class FileUploaderServiceimpl implements FileUploaderService {

    @Autowired
    private FileDao fileDao;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public boolean insertFile(FileBean file) {
        return fileDao.insertFile(file) == 1;
    }


}
