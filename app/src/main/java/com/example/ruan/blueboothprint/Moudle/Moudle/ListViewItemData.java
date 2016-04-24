package com.example.ruan.blueboothprint.Moudle.Moudle;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/4/11.
 */
public class ListViewItemData {

    private Drawable itemImage = null;
    private String itemText = null;
    private Drawable itemRightImage = null;
    private Drawable itemCenterImage = null;
    private String itemCenterText = null;
    private String itemName = null;
    private String itemPhone = null;

    public String getItemAddress() {
        return itemAddress;
    }

    public void setItemAddress(String itemAddress) {
        this.itemAddress = itemAddress;
    }

    public String getItemPhone() {
        return itemPhone;
    }

    public void setItemPhone(String itemPhone) {
        this.itemPhone = itemPhone;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    private String itemAddress = null;


    public int getItemHeight() {
        return itemHeight;
    }

    public void setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
    }

    private int itemHeight = 0;

    public Drawable getItemCenterImage() {
        return itemCenterImage;
    }

    public void setItemCenterImage(Drawable itemCenterImage) {
        this.itemCenterImage = itemCenterImage;
    }

    public String getItemCenterText() {
        return itemCenterText;
    }

    public void setItemCenterText(String itemCenterText) {
        this.itemCenterText = itemCenterText;
    }

    public Drawable getItemImage() {
        return itemImage;
    }

    public void setItemImage(Drawable itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public Drawable getItemRightImage() {
        return itemRightImage;
    }

    public void setItemRightImage(Drawable itemRightImage) {
        this.itemRightImage = itemRightImage;
    }
}
