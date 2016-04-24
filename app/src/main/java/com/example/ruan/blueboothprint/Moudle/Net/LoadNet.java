package com.example.ruan.blueboothprint.Moudle.Net;

import android.content.Context;

import com.example.administrator.websocket.Wamp;
import com.example.ruan.blueboothprint.Moudle.Util.PATH;

/**
 * Created by Administrator on 2016/3/17.
 */
public class LoadNet implements Wamp.ConnectionHandler, HttpInterface.HttpHandler{

    private String json;
    private Context context = null;
    private NetHandler netHandler = null;

    public LoadNet(Context context, String json, NetHandler netHandler) {
        this.json = json;
        this.context = context;
        this.netHandler = netHandler;
    }

    //登录成功执行的方法
    @Override
    public void onOpen() {
        //1.登录成功之后获取用户信息
        //getuserinfo这个是获取用户信息的标识
        PATH.mConnection.call("getuserinfo", String.class, new GetDataNet(this), json);
        //3.将获取的用户信息写进数据
    }

    //链接断开的时候执行的方法
    @Override
    public void onClose(int code, String reason) {

    }

    //传输二进制的方法
    @Override
    public void onBinaryMessage(byte[] payload) {

    }

    //这个用户返回方法处理
    @Override
    public void handler(String result) {
        netHandler.ResultHandler(result);
    }
}
