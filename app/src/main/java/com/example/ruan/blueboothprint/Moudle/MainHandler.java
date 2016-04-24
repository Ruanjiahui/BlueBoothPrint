package com.example.ruan.blueboothprint.Moudle;

import android.content.ContentValues;
import android.content.Context;


import com.example.ruan.blueboothprint.Moudle.Database.CheckDatabase;
import com.example.ruan.blueboothprint.Moudle.Database.GetDatabaseData;
import com.example.ruan.blueboothprint.Moudle.Moudle.Server;
import com.example.ruan.blueboothprint.Moudle.Net.LoadModel;
import com.example.ruan.blueboothprint.Moudle.Util.PATH;

import java.util.Map;

/**
 * Created by Administrator on 2016/4/15.
 */
public class MainHandler {

    private Context context = null;

    public MainHandler(Context context) {
        this.context = context;
    }

    /**
     * 判断是否是登录状态
     *
     * @return
     */
    public Map<String, String> isUserLoad() {
        //首先获取数据server表的数据判断有没有给数据表
        //首先判断有没有server表
        CheckDatabase.FirstCheckDatabase(context);
        //之后获取数据表的数据查看哪条数据登录的，没有登陆过的就跳出方法
        GetDatabaseData getDatabaseData = new GetDatabaseData();
        Map<String, String> map = getDatabaseData.Query(context, "BlueBooth", "Server", null, "online = ?", new String[]{"1"}, "", "", "", "");
        if (map == null || map.size() == 0)
            return null;

        if (!PATH.mConnection.isConnected()) {
            //如果有一条数据登录过则自动链接服务器
            Server server = new Server();
            server.setOnline(1);
            server.setUserid(map.get("userid"));
            server.setLaststate(1);
            server.setPassword(map.get("password"));

            LoadModel loadModel = new LoadModel(context);
            //将server封装成ContentValues
            loadModel.UpdateServer(server);
            //实现登录的方法
            loadModel.LinkLoad(server);
        }
        //更改主界面的UI
        map = getDatabaseData.Query(context, "BlueBooth", "User", null, "userid = ?", new String[]{map.get("userid")}, "", "", "", "");
        return map;
    }

    public void ExistLoad(String userid) {
        //退出登录首先断开服务器的链接
        PATH.mConnection.disconnect();

        //之后修改本地数据的状态
        GetDatabaseData getDatabaseData = new GetDatabaseData();

        ContentValues contentValues = new ContentValues();
        contentValues.put("online", "0");
        getDatabaseData.Update(context, "BlueBooth", "Server", contentValues, "userid = ?", new String[]{userid});

        //更新主界面的UI
        //更新主界面是跳转回到Activity设置
    }

    /**
     * 判断是否链接上服务器
     * @return
     */
    public boolean isLoad() {
        return PATH.mConnection.isConnected();
    }
}
