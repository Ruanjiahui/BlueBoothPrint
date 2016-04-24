package com.example.ruan.blueboothprint.Moudle.Database;

import android.content.Context;

/**
 * Created by Administrator on 2016/3/17.
 */
public class CheckDatabase {

    public static void FirstCheckDatabase(Context context) {
        GetDatabaseData getDatabaseData = new GetDatabaseData();
        //如果没有用户表的话就直接创表
        if (!getDatabaseData.QueryTable(context, "BlueBooth", "User"))
            CreateTable.TABLE(context, "BlueBooth", "User", createUserTable(), "" , "");

        //如果没有用户登录表的话就直接创表
        if (!getDatabaseData.QueryTable(context, "BlueBooth", "Server"))
            CreateTable.TABLE(context, "BlueBooth", "Server", createServerTable(), "" , "");

    }

    public static void AddressCheckDatabase(Context context){
        GetDatabaseData getDatabaseData = new GetDatabaseData();
        //如果没有用户表的话就直接创表
        if (!getDatabaseData.QueryTable(context, "BlueBooth", "Address"))
            CreateTable.TABLE(context, "BlueBooth", "Address", createAddressTable(), "ids" , "ids");
    }

    /**
     * 创建地址表
     * @return
     */
    private static Establish createAddressTable(){
        Establish establish = new Establish();

        establish.put("ids" , "INTEGER");
        establish.put("names" , "varchar(25) not null");
        establish.put("phones" , "Integer NOT NULL default 0");
        establish.put("convider" , "varchar(25) not null");
        establish.put("city" , "varchar(25) not null");
        establish.put("addresss" , "varchar(255) not null");

        return establish;
    }

    /**
     * 创建用户表
     *
     * @return
     */
    private static Establish createUserTable() {
        Establish establish = new Establish();

        establish.put("userid", "varchar(25) not null");
        establish.put("nickname", "varchar(25) not null");
        establish.put("truename", "varchar(255) not null");
        establish.put("password", "varchar(50) not null");
        establish.put("company", "varchar(255) not null");
        establish.put("viplev", "smallint NOT NULL default 0");
        establish.put("sexy", "varchar(50) not null");
        establish.put("wbid", "varchar(50) not null");
        establish.put("qq", "varchar(50) not null");
        establish.put("wxid", "varchar(50) not null");
        establish.put("email", "varchar(50) not null");
        establish.put("icon", "varchar(50) not null");
        establish.put("picture", "varchar(50) not null");
        establish.put("d2code", "varchar(50) not null");
        establish.put("address", "varchar(50) not null");
        establish.put("postcode", "varchar(50) not null");
        establish.put("idcard", "varchar(50) not null");
        establish.put("note", "varchar(50) not null");
        establish.put("registerdate", "DATETIME NOT NULL DEFAULT (datetime('now', 'localtime'))");
        establish.put("bankaccount", "varchar(50) not null");
        establish.put("bankname", "varchar(50) not null");
        establish.put("balance", "decimal(10, 2) NULL");
        establish.put("available", "decimal(10, 2) NULL");
        establish.put("state", "TINYINT DEFAULT 0");
        establish.put("appversion", "varchar(50) not null");
        establish.put("Integral", "int NOT NULL default 0");
        establish.put("height", "varchar(50) not null");
        establish.put("birthday", "varchar(50) not null");
        establish.put("occupation", "varchar(50) not null");
        establish.put("education", "varchar(50) not null");
        establish.put("autograph", "varchar(50) not null");
        establish.put("phone", "varchar(50) not null");


        return establish;
    }

    /**
     * 创建系统表
     *
     * @return
     */
    private static Establish createServerTable() {
        Establish establish = new Establish();

        establish.put("userid", "varchar(25) not null");
        establish.put("nickname", "varchar(25)");
        establish.put("password", "varchar(25) not null");
        establish.put("online", "integer");
        establish.put("laststate", "integer not null default 0");

        return establish;
    }
}
