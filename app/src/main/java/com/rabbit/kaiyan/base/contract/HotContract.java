package com.rabbit.kaiyan.base.contract;


import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;

/**
 * Created by hzj on 2017/12/26.
 */

public interface HotContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
