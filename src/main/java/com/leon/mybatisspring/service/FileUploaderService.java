package com.leon.mybatisspring.service;

import com.leon.mybatisspring.pojo.FileBean;

/**
 * Created on 20/10/2017.
 *
 * @author Xiaolei-Peng
 */
public interface FileUploaderService {
    boolean insertFile(FileBean file);
}
