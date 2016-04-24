package com.example.ruan.blueboothprint.Moudle.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/29.
 *
 * 这个对象用于封装储存创建数据表的数据的对象
 */
public class Establish {

    private HashMap<String, String> map = null;
    private ArrayList<String> key = null;

    public Establish() {
        map = new HashMap<>();
        key = new ArrayList<>();
    }

    public void put(String key, String value) {
        map.put(key, value);
    }


    public String get(String key) {
        return map.get(key);
    }

    public ArrayList<String> getKey() {
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            key.add(entry.getKey() + "");
            // entry.getKey() 返回与此项对应的键
            // entry.getValue() 返回与此项对应的值
        }
        return key;
    }
}
