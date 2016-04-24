package com.example.ruan.blueboothprint.Moudle.Net;

import com.example.administrator.websocket.Wamp;

/**
 * Created by Administrator on 2016/3/18.
 */
public class ResgisterNet implements Wamp.ConnectionHandler {

    private HttpInterface.HttpHandler httpHandler = null;
    public ResgisterNet(){}

    public ResgisterNet(HttpInterface.HttpHandler httpHandler){
        this.httpHandler = httpHandler;

    }
    //注册成功触发的接口
    @Override
    public void onOpen() {
        //注册成功将用户登陆信息写进数据库
        httpHandler.handler("OK");
    }

    //链接失败触发的接口
    @Override
    public void onClose(int code, String reason) {
        httpHandler.handler("Fail");
    }

    //接口二进制的接口
    @Override
    public void onBinaryMessage(byte[] payload) {

    }
}
