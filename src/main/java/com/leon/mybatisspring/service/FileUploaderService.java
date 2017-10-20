package com.leon.mybatisspring.service;

import com.leon.mybatisspring.pojo.FileBean;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created on 20/10/2017.
 *
 * @author Xiaolei-Peng
 */
public interface FileUploaderService {
    boolean insertFile(MultipartFile multipartFile, FileBean file);
}
