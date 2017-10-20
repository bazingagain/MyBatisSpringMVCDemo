package com.leon.mybatisspring.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created on 20/10/2017.
 *
 * @author Xiaolei-Peng
 */
public class FileHelper {

    public static void uploadFile(MultipartFile file, String filePath) {
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
