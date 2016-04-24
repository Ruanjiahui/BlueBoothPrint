package com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint;

import android.content.Context;

import com.example.administrator.boothprint.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/13.
 */
public class Form {

    //    private String Goodsname = null;
    private String GoodsnameContent = null;
    //    private String Goodspack = null;
    private String GoodspackContent = null;
    //    private String Goodsweight = null;
    private String GoodsweightContent = null;
    //    private String Goodsvolume = null;
    private String GoodsvolumeContent = null;
    //    private String GoodsTypename = null;
    private String GoodsTypeContent = null;
    //    private String Costservice = null;
    private String CostserviceContent = null;
    //    private String CostDelivery = null;
    private String CostDeliveryContent = null;
    //    private String Costtransfer = null;
    private String CosttransferContent = null;
    //    private String CostLoading = null;
    private String CostLoadingContent = null;
    //    private String Costtake = null;
    private String CosttakeContent = null;
    //    private String CostOther = null;
    private String CostOtherContent = null;
    //    private String PickType = null;
    private String PickTypename = null;
    //    private String StateType = null;
    private String StateTypename = null;
    //    private String PayType = null;
    private String PayTypename = null;
    //    private String PayStateType = null;
    private String PayStateTypename = null;

    private String consigneeToname = null;

    private String consigneeTophone = null;

    private String consigneeToaddress = null;

    private String consigneeFromname = null;

    private String consigneeFromphone = null;

    private String consigneeFromaddress = null;

    private String consigneeToConvider = null;

    private String consigneeToCity = null;

    private String consigneeFromConvider = null;

    private String consigneeFromCity = null;


    public String getConsigneeToConvider() {
        return consigneeToConvider;
    }

    public void setConsigneeToConvider(String consigneeToConvider) {
        this.consigneeToConvider = consigneeToConvider;
    }

    public String getConsigneeToCity() {
        return consigneeToCity;
    }

    public void setConsigneeToCity(String consigneeToCity) {
        this.consigneeToCity = consigneeToCity;
    }

    public String getConsigneeFromConvider() {
        return consigneeFromConvider;
    }

    public void setConsigneeFromConvider(String consigneeFromConvider) {
        this.consigneeFromConvider = consigneeFromConvider;
    }

    public String getConsigneeFromCity() {
        return consigneeFromCity;
    }

    public void setConsigneeFromCity(String consigneeFromCity) {
        this.consigneeFromCity = consigneeFromCity;
    }

    public String getConsigneeFromaddress() {
        return consigneeFromaddress;
    }

    public void setConsigneeFromaddress(String consigneeFromaddress) {
        this.consigneeFromaddress = consigneeFromaddress;
    }

    public String getConsigneeFromphone() {
        return consigneeFromphone;
    }

    public void setConsigneeFromphone(String consigneeFromphone) {
        this.consigneeFromphone = consigneeFromphone;
    }

    public String getConsigneeFromname() {
        return consigneeFromname;
    }

    public void setConsigneeFromname(String consigneeFromname) {
        this.consigneeFromname = consigneeFromname;
    }

    public String getConsigneeToname() {
        return consigneeToname;
    }

    public void setConsigneeToname(String consigneeToname) {
        this.consigneeToname = consigneeToname;
    }

    public String getConsigneeTophone() {
        return consigneeTophone;
    }

    public void setConsigneeTophone(String consigneeTophone) {
        this.consigneeTophone = consigneeTophone;
    }

    public String getConsigneeToaddress() {
        return consigneeToaddress;
    }

    public void setConsigneeToaddress(String consigneeToaddress) {
        this.consigneeToaddress = consigneeToaddress;
    }


    private Context context = null;
    private ArrayList<String> Content = null;

    public Form(Context context) {
        this.context = context;
    }


    public String getGoodsnameContent() {
        return GoodsnameContent;
    }

    public void setGoodsnameContent(String goodsnameContent) {
        GoodsnameContent = goodsnameContent;
    }

    public String getGoodspackContent() {
        return GoodspackContent;
    }

    public void setGoodspackContent(String goodspackContent) {
        GoodspackContent = goodspackContent;
    }

    public String getGoodsweightContent() {
        return GoodsweightContent;
    }

    public void setGoodsweightContent(String goodsweightContent) {
        GoodsweightContent = goodsweightContent;
    }

    public String getGoodsvolumeContent() {
        return GoodsvolumeContent;
    }

    public void setGoodsvolumeContent(String goodsvolumeContent) {
        GoodsvolumeContent = goodsvolumeContent;
    }

    public String getGoodsTypeContent() {
        return GoodsTypeContent;
    }

    public void setGoodsTypeContent(String goodsTypeContent) {
        GoodsTypeContent = goodsTypeContent;
    }

    public String getCostserviceContent() {
        return CostserviceContent;
    }

    public void setCostserviceContent(String costserviceContent) {
        CostserviceContent = costserviceContent;
    }

    public String getCostDeliveryContent() {
        return CostDeliveryContent;
    }

    public void setCostDeliveryContent(String costDeliveryContent) {
        CostDeliveryContent = costDeliveryContent;
    }

    public String getCosttransferContent() {
        return CosttransferContent;
    }

    public void setCosttransferContent(String costtransferContent) {
        CosttransferContent = costtransferContent;
    }

    public String getCostLoadingContent() {
        return CostLoadingContent;
    }

    public void setCostLoadingContent(String costLoadingContent) {
        CostLoadingContent = costLoadingContent;
    }

    public String getCosttakeContent() {
        return CosttakeContent;
    }

    public void setCosttakeContent(String costtakeContent) {
        CosttakeContent = costtakeContent;
    }

    public String getCostOtherContent() {
        return CostOtherContent;
    }

    public void setCostOtherContent(String costOtherContent) {
        CostOtherContent = costOtherContent;
    }

    public String getPickTypename() {
        return PickTypename;
    }

    public void setPickTypename(String pickTypename) {
        PickTypename = pickTypename;
    }

    public String getStateTypename() {
        return StateTypename;
    }

    public void setStateTypename(String stateTypename) {
        StateTypename = stateTypename;
    }

    public String getPayTypename() {
        return PayTypename;
    }

    public void setPayTypename(String payTypename) {
        PayTypename = payTypename;
    }

    public String getPayStateTypename() {
        return PayStateTypename;
    }

    public void setPayStateTypename(String payStateTypename) {
        PayStateTypename = payStateTypename;
    }


    /**
     * 格式化表单
     */
    public void setForm() {

        Content = new ArrayList<>();
        Content.add(context.getResources().getString(R.string.consigneeGoodsname));
        Content.add(getGoodsnameContent());
        Content.add(context.getResources().getString(R.string.consigneeGoodspack));
        Content.add(getGoodspackContent());
        Content.add(context.getResources().getString(R.string.consigneeGoodsweight));
        Content.add(getGoodsweightContent());
        Content.add(context.getResources().getString(R.string.consigneeGoodsvolume));
        Content.add(getGoodsvolumeContent());
        Content.add(context.getResources().getString(R.string.consigneeCostservice));
        Content.add(getCostserviceContent());
        Content.add(context.getResources().getString(R.string.consigneeCostDelivery));
        Content.add(getCostDeliveryContent());
        Content.add(context.getResources().getString(R.string.consigneeCosttransfer));
        Content.add(getCosttransferContent());
        Content.add(context.getResources().getString(R.string.consigneeCostLoading));
        Content.add(getCostLoadingContent());
        Content.add(context.getResources().getString(R.string.consigneeCosttake));
        Content.add(getCosttakeContent());
        Content.add(context.getResources().getString(R.string.consigneeCostOther));
        Content.add(getCostOtherContent());
        Content.add(context.getResources().getString(R.string.consigneeGoodsType));
        Content.add(getGoodsTypeContent());
        Content.add(context.getResources().getString(R.string.consigneePickType));
        Content.add(getPickTypename());
        Content.add(context.getResources().getString(R.string.consigneeStateType));
        Content.add(getStateTypename());
        Content.add(context.getResources().getString(R.string.consigneePayType));
        Content.add(getPayTypename());
        Content.add(context.getResources().getString(R.string.consigneePayStateType));
        Content.add(getPayStateTypename());


//        Content = context.getResources().getString(R.string.consigneeGoodsname) + " : " + getGoodsnameContent() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneeGoodspack) + " : " + getGoodspackContent() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneeGoodsweight) + " : " + getGoodsweightContent() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneeGoodsvolume) + " : " + getGoodsvolumeContent() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneeCostservice) + " : " + getCostserviceContent() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneeCostDelivery) + " : " + getCostDeliveryContent() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneeCosttransfer) + " : " + getCosttransferContent() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneeCostLoading) + " : " + getCostLoadingContent() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneeCosttake) + " : " + getCosttakeContent() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneeCostOther) + " : " + getCostOtherContent() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneeGoodsType) + " : " + getGoodsTypeContent() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneePickType) + " : " + getPickTypename() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneeStateType) + " : " + getStateTypename() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneePayType) + " : " + getPayTypename() + System.getProperty("line.separator")
//                + context.getResources().getString(R.string.consigneePayStateType) + " : " + getPayStateTypename();

    }

    public ArrayList<String> toArrayString() {
        return Content;
    }
}
