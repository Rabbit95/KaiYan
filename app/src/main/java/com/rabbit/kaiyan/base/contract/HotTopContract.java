package com.rabbit.kaiyan.base.contract;


import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.ItemListBean;

import java.util.List;

/**
 * Created by hzj on 2017/12/26.
 */

public interface HotTopContract {
    interface View extends BaseView {
        void showContents(List<ItemListBean> listBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getHotData(String type);
    }
}
