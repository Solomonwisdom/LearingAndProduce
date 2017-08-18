package com.whg.web.action.admin;

import com.whg.web.entity.Item;
import com.whg.web.impl.JdbcItemDAO;

import java.util.List;

/**
 * Created by wanghaogang on 2017/7/11.
 */
public class ItemListAction {
    private List<Item> itemList;
    public String execute() throws Exception {
        itemList = new JdbcItemDAO().showAll();
        return "success";
    }
    public List<Item> getItemList() {
        return itemList;
    }
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
