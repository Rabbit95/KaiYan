package com.rabbit.kaiyan.model.beans;

public class UserInfoBean {
    private String username;
    private Integer loginstatus;

    public String getUsername() {
        return username;
    }

    public Integer getLoginstatus() {
        return loginstatus;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginstatus(Integer loginstatus) {
        this.loginstatus = loginstatus;
    }

    @Override
    public String toString() {
        return "username:"+username;
    }
}
