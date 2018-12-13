package com.rabbit.kaiyan.base;

/**
     * @type    
     * @explain  基础Presenter
     * @author Rabbit.
     * @creat time 2018/11/19 21:27.
**/
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
