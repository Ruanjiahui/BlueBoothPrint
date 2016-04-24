package com.example.ruan.blueboothprint.Moudle.Net;

import android.content.ContentValues;
import android.content.Context;


import com.example.ruan.blueboothprint.Controller.Login;
import com.example.ruan.blueboothprint.Controller.Register;
import com.example.ruan.blueboothprint.Moudle.Database.CheckDatabase;
import com.example.ruan.blueboothprint.Moudle.Database.GetDatabaseData;
import com.example.ruan.blueboothprint.Moudle.Moudle.Server;
import com.example.ruan.blueboothprint.Moudle.Moudle.User;
import com.example.ruan.blueboothprint.Moudle.Util.PATH;
import com.example.ruan.blueboothprint.Moudle.Util.System.SystemTool;
import com.example.ruan.blueboothprint.boothprint.Applications;

import java.util.Timer;

/**
 * Created by Administrator on 2016/3/17.
 */
public class ResgisterModel implements HttpInterface.HttpHandler, GetData.GetUser {

    private Context context = null;
    private Timer timer = null;
    private Server server = null;

    public ResgisterModel(Context context) {
        this.context = context;
    }

    /**
     * 检查两个字符串是否一样
     *
     * @param newPassword
     * @param olnPassword
     * @param msg
     * @return
     */
    public boolean PasswordCheck(String newPassword, String olnPassword, String msg) {
        if (!olnPassword.equals(newPassword)) {
            SystemTool.showToast(context, msg);
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否为空
     *
     * @param info
     * @param msg
     * @return
     */
    public boolean SpaceText(String info, String msg) {
        if ("".equals(info) || info == null || info.length() == 0) {
            SystemTool.showToast(context, msg);
            return true;
        }
        return false;
    }

    /**
     * 验证码的时间设定
     *
     * @param interval
     */
    public void Code(TimerHandler timerHandler, int interval) {
        timer = new Timer();
        timer.schedule(new MyTimerTask(timerHandler), 0, interval);
    }

    /**
     * 将开始的时间调度关闭
     */
    public void closeTimer() {
        if (timer != null)
            timer.cancel();
    }

    public void UpdateServer(Server server) {
        //检查数据库的表是否存在
        CheckDatabase.FirstCheckDatabase(context);

        ContentValues contentValues = new ContentValues();
        contentValues.put("password", server.getPassword());
        contentValues.put("userid", server.getUserid());
        contentValues.put("online", server.getOnline());


        GetDatabaseData getDatabaseData = new GetDatabaseData();

        //将用户的登录信息写到数据库Server表
        getDatabaseData.Insert2Update(context, "BlueBooth", "Server", server.getUserid(), new LoadModel(context).UpdateServer(server), "userid = ?", new String[]{server.getUserid()});

        //将用户个人的信息写到数据库User表
        getDatabaseData.Insert2Update(context, "BlueBooth", "User", server.getUserid(), contentValues, "userid = ?", new String[]{server.getUserid()});

    }

    public void ResgisterNet(Server server) {
        this.server = server;
        PATH.mConnection.connect(PATH.Load + server.getUserid() + "@" + server.getUserid() + "/" + server.getPassword(), new ResgisterNet(this));
    }

    @Override
    public void handler(String result) {
        if ("OK".equals(result)) {
            UpdateServer(server);

            PATH.server = server;

            GetDataDataModel getDataDataModel = new GetDataDataModel();
            getDataDataModel.setUserData(context, this);

            //注册成功将销毁登录界面
            Applications.getInstance().removeOneActivity(Login.activity);
            Applications.getInstance().removeOneActivity(Register.activity);
            SystemTool.showToast(context, "注册成功");
        } else
            SystemTool.showToast(context, "注册失败");
    }

    @Override
    public void getUserData(User user) {
    }
}
