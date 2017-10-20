package com.leon.mybatisspring.common;

/**
 * Created on 20/10/2017.
 *
 * @author Xiaolei-Peng
 */
public class Message {
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private boolean success = false;
    private String info = null;

}
