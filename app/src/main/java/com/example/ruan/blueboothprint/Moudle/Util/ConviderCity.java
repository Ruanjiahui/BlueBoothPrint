package com.example.ruan.blueboothprint.Moudle.Util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 19820 on 2016/4/23.
 */
public class ConviderCity {


    private static String[] convider = null;
    private static Map<String , ArrayList<String>> map = null;
    private static String[] city = null;
    private static String jsonTest = "[{\"convider\":\"广东省\" , \"city\":[\"广州市\" , \"深圳市\" , \"东莞市\" , \"阳江市\"]} , {\"convider\":\"北京市\" , \"city\":[\"朝阳区\"]} , {\"convider\":\"上海市\" , \"city\":[\"浦东区\"]}]";

    /**
     * 获取服务器的数据解析省份和城市的内容
     *
     * @param json
     */
    public static void setConviderCity(String json) {


        try {
            JSONArray array = new JSONArray(jsonTest);
            convider = new String[array.length()];
            map = new HashMap<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                convider[i] = jsonObject.getString("convider");
                JSONArray jsonArray = jsonObject.getJSONArray("city");
                ArrayList<String> list = new ArrayList<>();
                map.put("" + i , list);
                for (int r = 0; r < jsonArray.length(); r++) {
                    list.add(jsonArray.getString(r));
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取省份的数组
     *
     * @return
     */
    public static String[] getConvider() {
        return convider;
    }

    /**
     * 获取城市的数组
     *
     * @param convider
     * @return
     */
    public static String[] getCity(int convider) {
        city = new String[map.get(convider + "").size()];
        for (int i = 0 ; i < map.get(convider + "").size() ; i++){
            city[i] = map.get(convider + "").get(i);
        }
        return city;
    }

}
