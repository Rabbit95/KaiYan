package com.rabbit.kaiyan.model.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hzj on 2018/1/2.
 */

public class RelateBean implements Serializable {


    private int count;
    private Object nextPageUrl;
    private List<ItemListBean> itemList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(Object nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public List<ItemListBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBean> itemList) {
        this.itemList = itemList;
    }


}
