package com.example.ruan.blueboothprint.Moudle.Util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.ruan.blueboothprint.Moudle.Moudle.ListViewItemData;
import com.example.ruan.blueboothprint.Moudle.Util.System.DensityUtil;


/**
 * Created by Administrator on 2016/4/12.
 */
public class AdapterData {

    /**
     * 这个布局是listview布局
     * <p/>
     * 拥有一个左边的图标，左边图标隔壁加上文字，之后最右边有一个图标
     *
     * @param ItemText
     * @param ItemImage
     * @return
     */
    public static ListViewItemData getMap(int height , String ItemText, Drawable ItemImage) {
        ListViewItemData listViewItemData = new ListViewItemData();

        listViewItemData.setItemText(ItemText);
        listViewItemData.setItemImage(ItemImage);
        listViewItemData.setItemHeight(height);

        return listViewItemData;
    }

    public static ListViewItemData getMap(String ItemText , int height){
        ListViewItemData listViewItemData = new ListViewItemData();

        listViewItemData.setItemText(ItemText);
        listViewItemData.setItemHeight(height);

        return listViewItemData;
    }


    public static ListViewItemData getMap(String ItemName , String ItemPhone , String ItemAddress){
        ListViewItemData listViewItemData = new ListViewItemData();

        listViewItemData.setItemName(ItemName);
        listViewItemData.setItemAddress(ItemAddress);
        listViewItemData.setItemPhone(ItemPhone);

        return listViewItemData;
    }

    /**
     * 这个是listview的布局里面拥有文字和右边的点击按钮
     * @param ItemText  文字
     * @param itemRightImage     是否拥有单选按钮
     * @return
     */
    public static ListViewItemData getMap(Context context , String ItemText, Drawable itemRightImage) {
        ListViewItemData listViewItemData = new ListViewItemData();

        listViewItemData.setItemText(ItemText);
        listViewItemData.setItemRightImage(itemRightImage);
        listViewItemData.setItemHeight(DensityUtil.dip2px(context, 50));

        return listViewItemData;
    }

    /**
     * 这个布局是gridView布局
     * <p/>
     * 在布局中间有图标和文件上下布局居中
     * 可以设置item高度
     *
     * @param ItemCenterText
     * @param ItemCenterImage
     * @return
     */
    public static ListViewItemData getMap(String ItemCenterText, Drawable ItemCenterImage, int height) {
        ListViewItemData listViewItemData = new ListViewItemData();

        listViewItemData.setItemCenterText(ItemCenterText);
        listViewItemData.setItemCenterImage(ItemCenterImage);
        listViewItemData.setItemHeight(height);

        return listViewItemData;
    }
}
