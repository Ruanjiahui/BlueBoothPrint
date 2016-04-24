package com.example.administrator.websocket;

/**
 * Created by Administrator on 2016/3/21.
 */
public class WebSockectConnection {

    private Wamp connection = null;

    public WebSockectConnection(Wamp connection) {
        if (connection.isConnected()) {
            this.connection = connection;
        }
    }

    /**
     * 更新数据和获取数据
     * @param path
     * @param callHandler
     * @param json
     */
    public void UpdateorGet(String path, Wamp.CallHandler callHandler, String json) {
        if (connection != null) {
            connection.call(path , String.class , callHandler , json);
        }
    }
}
