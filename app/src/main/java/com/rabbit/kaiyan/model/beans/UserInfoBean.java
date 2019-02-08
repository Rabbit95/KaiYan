package com.rabbit.kaiyan.model.beans;

public class UserInfoBean {
    private String username;
    private Integer msgCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(Integer msgCode) {
        this.msgCode = msgCode;
    }

    @Override
    public String toString() {
        return "username:"+username;
    }
}
