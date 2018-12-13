package com.rabbit.kaiyan.util;

import android.support.v7.util.DiffUtil;

import com.rabbit.kaiyan.model.beans.ItemListBean;

import java.util.List;

public class DiffUtilCallBack extends DiffUtil.Callback {

    private List<ItemListBean> mOldDatas, mNewDatas;

    public DiffUtilCallBack(List<ItemListBean> OldDatas, List<ItemListBean> NewDatas) {
        this.mOldDatas = OldDatas;
        this.mNewDatas = NewDatas;
    }

    public DiffUtilCallBack() {
    }

    @Override
    public int getOldListSize() {
        return mOldDatas != null ? mOldDatas.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewDatas != null ? mNewDatas.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//        return mOldDatas.get(mOldDatas.size() - 1).getData().getHeader().getTitle().toString().equals(mNewDatas.get(mNewDatas.size() - 1).getData().getHeader().getTitle().toString());
        return mOldDatas.size() != mNewDatas.size();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//        ItemListBean oldItem = moldDatas.get(oldItemPosition);
//        ItemListBean newItem = mNewDatas.get(newItemPosition);
//        if (moldDatas.get(oldItemPosition).getType().equals("textHeader") || mNewDatas.get(newItemPosition).getType().equals("textHeader")) {
//            if (moldDatas.get(oldItemPosition).getType().equals(mNewDatas.get(newItemPosition).getType())) {
//                return moldDatas.get(oldItemPosition).getData().getText().equals(mNewDatas.get(newItemPosition).getData().getText());
//            } else {
//                return false;
//            }
//        } else if(moldDatas.get(moldDatas.size() - 1).getData().getPlayUrl().equals(mNewDatas.get(mNewDatas.size() - 1).getData().getPlayUrl())){
//            return true;
//        }else {
//            return true;
//        }
        return mOldDatas.size() != mNewDatas.size();
    }
}
