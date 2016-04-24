package com.example.ruan.blueboothprint.Moudle.Moudle;

import java.util.Map;

/**
 * Created by Administrator on 2016/3/17.
 */
public class Server extends User {

    //用户密码
    protected String password = "";
    //用户在线状态
    protected int online = 0;
    //上次登录状态
    protected int laststate = 0;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public void setLaststate(int laststate){
        this.laststate = laststate;
    }

    public int getLaststate(){
        return laststate;
    }


    public Server(){}

    public Server(Map<String, String> map) {
        getMap(map);
    }

    public Server getServer() {
        return this;
    }

    private void getMap(Map<String, String> map) {
        if (map != null && map.size() != 0) {
            setNickname(map.get("nickname"));
            setUserid(map.get("userid"));
            setPassword(map.get("password"));
            setOnline(Integer.parseInt(map.get("online")));
            setLaststate(Integer.parseInt(map.get("laststate")));
        }
    }
}
