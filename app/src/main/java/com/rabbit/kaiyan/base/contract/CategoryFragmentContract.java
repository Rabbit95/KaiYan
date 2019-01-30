package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.CategoryBean;

public interface CategoryFragmentContract {
    interface View extends BaseView {
        void setCategoryData(CategoryBean categoryData);
    }
    interface Presenter extends BasePresenter<CategoryFragmentContract.View> {
        void getCategoryDataByID(int categoryID);
    }
}
