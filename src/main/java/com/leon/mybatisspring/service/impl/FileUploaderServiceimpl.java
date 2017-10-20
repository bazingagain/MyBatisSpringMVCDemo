package com.leon.mybatisspring.service.impl;

import com.leon.mybatisspring.dao.FileDao;
import com.leon.mybatisspring.pojo.FileBean;
import com.leon.mybatisspring.service.FileUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
    public boolean insertFile(MultipartFile imgFile, FileBean file) {
        fileDao.insertFile(file);
        this.uploadFile(imgFile, file.getFilePath());
        return true;
    }

    private void uploadFile(MultipartFile file, String filePath) {
        FileOutputStream os = null;
        FileInputStream in = null;
        try {
            os = new FileOutputStream(filePath);
            in = (FileInputStream) file.getInputStream();
            byte[] b = new byte[1024];
            while (in.read(b) != -1) {
                os.write(b);
            }
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
