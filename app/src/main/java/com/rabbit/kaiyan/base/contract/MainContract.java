package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
/**
     * @type
     * @explain MainMVP约束接口
     * @author Rabbit.
     * @creat time 2018/11/27 15:53.
**/
public interface MainContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{
        void checkPremission();
        void getSearchSuggestions();
    }
}
