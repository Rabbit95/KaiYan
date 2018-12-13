package com.rabbit.kaiyan.presenter;

import com.rabbit.kaiyan.base.BasePresenter;
import com.rabbit.kaiyan.base.BaseView;
import com.rabbit.kaiyan.model.DataManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
/**
     * @type
     * @explain RxJava的Presenter
     * @author Rabbit.
     * @creat time 2018/11/27 16:16.
**/
public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;
    protected CompositeDisposable mCompositeDisposable;
    protected DataManager mDataManager;
    /**
    * @explain 添加订阅关系
    **/
    protected void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
    /**
    * @explain 解除订阅关系
    **/
    protected void clearSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }


    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        clearSubscribe();
    }
}
