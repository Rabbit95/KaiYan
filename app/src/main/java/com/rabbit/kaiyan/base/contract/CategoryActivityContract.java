package com.rabbit.kaiyan.base.contract;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.beans.CategoryBean;
import com.rabbit.kaiyan.model.beans.CategoryInfo;

public interface CategoryActivityContract {
    interface View extends BaseView {
        void showCategoryInfo(CategoryInfo categoryInfo);
        void setPageData(CategoryBean categoryBean);
    }
    interface Presenter extends BasePresenter<CategoryActivityContract.View> {
        void getCategoryInfoByID(int categoryID);
        void getCategoryPageDataByID(int categoryID);
    }
}
