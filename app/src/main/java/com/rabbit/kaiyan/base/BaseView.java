package com.rabbit.kaiyan.base;
/**
     * @type
     * @explain 基础View
     * @author Rabbit.
     * @creat time 2018/11/19 21:29.
**/
public interface BaseView {
    void showErrorMsg(String msg);
    //---state---
    void stateError();
    void stateStart();
    void stateLoading();
}
