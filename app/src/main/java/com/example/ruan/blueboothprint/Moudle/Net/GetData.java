package com.example.ruan.blueboothprint.Moudle.Net;

import android.content.Context;

import com.example.ruan.blueboothprint.Moudle.Moudle.Server;
import com.example.ruan.blueboothprint.Moudle.Moudle.User;


/**
 * Created by Administrator on 2016/3/17.
 */
public interface GetData {

    public interface GetUser {

        public void getUserData(User user);
    }

    public interface GetServer{

        public void getServerData(Server server);
    }

    public interface SetUser{

        public void setUserData(Context context, GetUser getUser);

        public void setServerData(Context context, GetServer getServer);
    }


    public interface getPasage{

        public void getData();
    }
}
