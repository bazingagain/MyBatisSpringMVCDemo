package com.leon.mybatisspring.controller;

import com.leon.mybatisspring.common.Message;
import com.leon.mybatisspring.pojo.FileBean;
import com.leon.mybatisspring.service.FileUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * Created on 20/10/2017.
 *
 * @author Xiaolei-Peng
 */
@Controller
@RequestMapping("/file")
public class FileUploaderController {

    @Autowired
    FileUploaderService uploaderService;

    @RequestMapping("/upload")
    @ResponseBody
    private Message uploadFile(@RequestParam("title") String title, HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpRequest = (MultipartHttpServletRequest) request;
        MultipartFile imgFile = multipartHttpRequest.getFile("imageFile");
        FileBean fileBean = new FileBean();
        fileBean.setTitle(title);
        String path = request.getServletContext().getRealPath("/") + "upload";
        String filepath = path + File.separator + new Date().getTime() + imgFile.getOriginalFilename();
        File uploadDir = new File(path);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        fileBean.setFilePath(filepath);
        Message msg = new Message();
        if (uploaderService.insertFile(imgFile, fileBean)) {
            msg.setSuccess(true);
            msg.setInfo("插入文件成功");
        } else {
            msg.setSuccess(false);
            msg.setInfo("插入文件失败");
        }
        return msg;
    }

}
