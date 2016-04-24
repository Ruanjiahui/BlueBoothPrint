package com.example.ruan.blueboothprint.Moudle.Net;

import android.content.ContentValues;
import android.content.Context;

import com.example.ruan.blueboothprint.Controller.Login;
import com.example.ruan.blueboothprint.Moudle.Database.CheckDatabase;
import com.example.ruan.blueboothprint.Moudle.Database.GetDatabaseData;
import com.example.ruan.blueboothprint.Moudle.Moudle.Server;
import com.example.ruan.blueboothprint.Moudle.Moudle.User;
import com.example.ruan.blueboothprint.Moudle.Util.AnalysisJSON;
import com.example.ruan.blueboothprint.Moudle.Util.PATH;
import com.example.ruan.blueboothprint.Moudle.Util.System.SystemTool;
import com.example.ruan.blueboothprint.boothprint.Applications;


/**
 * Created by Administrator on 2016/3/17.
 */
public class LoadModel implements NetHandler , GetData.GetUser{

    private static ContentValues contentValues = null;

    private Context context = null;
    private Server server = null;

    public LoadModel(Context context) {
        this.context = context;
    }

    /**
     * 这个是将用户的登录信息保存到ContentValues
     *
     * @param server
     */
    public ContentValues UpdateServer(Server server) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", server.getPassword());
        contentValues.put("userid", server.getUserid());
        contentValues.put("online", server.getOnline());
        this.contentValues = contentValues;
        return contentValues;
    }

    /**
     * 实现登录的操作
     *
     * @param server
     */
    public void LinkLoad(Server server) {
        this.server = server;
        //链接服务器的操作
        PATH.mConnection.connect(PATH.Load + server.getUserid() + "/" + server.getPassword(), new LoadNet(context, server.getUserid(), this));
    }


    public void InsertData(Context context, String info) {
        GetDatabaseData getDatabaseData = new GetDatabaseData();

        //将用户的登录信息写到数据库Server表
        getDatabaseData.Insert2Update(context, "BlueBooth", "Server", server.getUserid(), contentValues, "userid = ?", new String[]{server.getUserid()});

        //将用户个人的信息写到数据库User表
        getDatabaseData.Insert2Update(context, "BlueBooth", "User", server.getUserid(), AnalysisJSON.getUserinfo(info), "userid = ?", new String[]{server.getUserid()});


//        PATH.server = server;

        GetDataDataModel getDataDataModel = new GetDataDataModel();
        getDataDataModel.setUserData(context, this);

        //登录成功将销毁登录界面
        Applications.getInstance().removeOneActivity(Login.activity);
        SystemTool.showToast(context, "登录成功");
    }


    /**
     * 登录返回的结果
     *
     * @param result
     */
    @Override
    public void ResultHandler(String result) {
        //检查数据库的表是否存在
        CheckDatabase.FirstCheckDatabase(context);
        //将数据插入数据库
        InsertData(context, result);
    }

    @Override
    public void WebSocketResult(String result) {
    }

    @Override
    public void getUserData(User user) {

    }
}
