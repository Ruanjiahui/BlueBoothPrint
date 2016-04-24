package com.example.ruan.blueboothprint.Moudle.Net;

import com.example.administrator.websocket.Wamp;
import com.example.ruan.blueboothprint.Moudle.Util.System.SystemTool;

/**
 * Created by Administrator on 2016/3/17.
 */
public class GetDataNet implements Wamp.CallHandler {


    private HttpInterface.HttpHandler httpHandler = null;

    public GetDataNet(HttpInterface.HttpHandler httpHandler) {
        this.httpHandler = httpHandler;
    }

    //链接成功返回的数据的接口
    @Override
    public void onResult(Object result) {
        httpHandler.handler(SystemTool.Transcodingdecode(result.toString()));
    }

    //这个是出现异常的接口
    @Override
    public void onError(String errorUri, String errorDesc) {

    }
}
