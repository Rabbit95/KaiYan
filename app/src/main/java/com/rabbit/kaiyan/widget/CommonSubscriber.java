package com.rabbit.kaiyan.widget;

import android.text.TextUtils;

import com.rabbit.kaiyan.base.BaseView;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;
/**
     * @type
     * @explain 统一观察者
     * @author Rabbit.
     * @creat time 2018/11/29 14:49.
**/
public abstract class CommonSubscriber<T> extends ResourceSubscriber<T>{
    String ErrorMsg;
    private BaseView mBaseView;

    protected CommonSubscriber(BaseView baseView, String ErrorMsg) {
        this.mBaseView = baseView;
        this.ErrorMsg = ErrorMsg;
    }

    protected CommonSubscriber(BaseView baseView) {
        this.mBaseView = baseView;
    }

    @Override
    public void onError(Throwable t) {
        if (mBaseView == null) {
            return;
        }
        if (ErrorMsg != null && !TextUtils.isEmpty(ErrorMsg)) {
            mBaseView.showErrorMsg(ErrorMsg);
        } else if (t instanceof HttpException) {
            mBaseView.showErrorMsg("数据加载失败~");
        } else {
            mBaseView.showErrorMsg("未知错误= = ！");
        }
    }

    @Override
    public void onComplete() {

    }
}
