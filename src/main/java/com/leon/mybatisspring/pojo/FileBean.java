package com.leon.mybatisspring.pojo;

import org.springframework.stereotype.Repository;

/**
 * Created on 20/10/2017.
 *
 * @author Xiaolei-Peng
 */
@Repository
public class FileBean {
    private Integer id;
    private String title;
    private String filePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
