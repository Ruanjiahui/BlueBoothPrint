package com.example.ruan.blueboothprint.Moudle.Net;

import android.content.Context;


import com.example.ruan.blueboothprint.Moudle.Database.GetDatabaseData;
import com.example.ruan.blueboothprint.Moudle.Moudle.Server;
import com.example.ruan.blueboothprint.Moudle.Moudle.User;

import java.util.Map;

/**
 * Created by Administrator on 2016/3/17.
 */

public class GetDataDataModel implements GetData.SetUser{

    //获取数据库的方法
    @Override
    public void setUserData(Context context , GetData.GetUser getUser) {
        //1.首先要获取数据库的用户数据
        //这个是实例化对象
        GetDatabaseData getDatabaseData = new GetDatabaseData();
        //将从数据库中获取的数据赋值给map集合
        Map<String , String> map = getDatabaseData.Query(context, "BlueBooth", "User", null, "", null, "", "", "", "");

        //2.之后将数据封装到User这个类
        User user = new User(map);

        //3.将User这个类赋值给getUser这个接口
        getUser.getUserData(user.getUser());
    }

    //获取数据库的方法
    @Override
    public void setServerData(Context context , GetData.GetServer getServer) {
        //1.首先要获取数据库的用户数据
        //这个是实例化对象
        GetDatabaseData getDatabaseData = new GetDatabaseData();
        //将从数据库中获取的数据赋值给map集合
        Map<String , String> map = getDatabaseData.Query(context, "BlueBooth", "Server", null, "", null, "", "", "", "");

        //2.之后将数据封装到Server这个类
        Server server = new Server(map);

        //3.将Server这个类赋值给getServer这个接口
        getServer.getServerData(server.getServer());
    }

}
