package com.example.ruan.blueboothprint.Moudle.Util.System;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.SimpleFormatter;

/**
 * Created by Administrator on 2016/3/15.
 * <p/>
 * 这个类是实现系统的操作
 */
public class SystemTool {

    // 以下是获得版本信息的工具方法
    private static PackageInfo PackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }

    /**
     * 判断移动设备是否有蓝牙模块
     *
     * 0 没有蓝牙设备
     * 1 蓝牙没有开启
     * 2 蓝牙已经开启
     *
     * @return
     */
    public static int isBluetooth(){
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        //如果adapter为空则说明该移动设备没有蓝牙模块
        if (adapter == null)
            return 0;
        //如果isEnabled为false则说明蓝牙设备没有开启否则则开启
        if (!adapter.isEnabled())
            return 1;
        return 2;
    }

    public static BluetoothDevice[] getBluetoothDevice(){
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> set = adapter.getBondedDevices();
        BluetoothDevice[] device = new BluetoothDevice[set.size()];
        Iterator<BluetoothDevice> iterator = set.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            device[n] = iterator.next();
            n++;
        }
        return device;
    }


    //版本名
    public static String getVersionName(Context context) {
        return PackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return PackageInfo(context).versionCode;
    }

    /**
     * 获取软件的应用的包名
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        return PackageInfo(context).packageName;
    }


    /**
     * 显示弹出框
     *
     * @param context
     * @param content
     */
    public static void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }


    /**
     * 解析Base64的转码
     *
     * @param msg
     * @return
     */
    public static String Transcodingdecode(String msg) {
        return new String(Base64.decode(msg.getBytes(), Base64.DEFAULT));
    }

    /**
     * 加密Base64的转码
     *
     * @param s
     * @return
     */
    public static String EncodeStr(String s) {
        return new String(Base64.encode(s.getBytes(), Base64.DEFAULT));
    }


    /**
     * 系统打印
     *
     * @param msg
     */
    public static void Log(String msg) {
        Log.e("Ruan", msg);
    }


    /**
     * 扫描全盘的图片获取的路径格式是jpeg
     *
     * @param context
     * @return
     */
    public static List<HashMap<String, String>> LocalImage(Context context) {
        // 指定要查询的uri资源
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        // 获取ContentResolver
        ContentResolver contentResolver = context.getContentResolver();
        // 查询的字段
        String[] projection = {MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATA, MediaStore.Images.Media.SIZE};
        // 条件
        String selection = MediaStore.Images.Media.MIME_TYPE + "=?";
        // 条件值(這裡的参数不是图片的格式，而是标准，所有不要改动)
        String[] selectionArgs = {"image/jpeg"};
        // 排序
        String sortOrder = MediaStore.Images.Media.DATE_MODIFIED + " desc";
        // 查询sd卡上的图片
        Cursor cursor = contentResolver.query(uri, projection, selection,
                selectionArgs, sortOrder);
        List<HashMap<String, String>> imageList = new ArrayList<HashMap<String, String>>();
        if (cursor != null) {
            HashMap<String, String> imageMap = null;
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                imageMap = new HashMap<String, String>();
                // 获得图片的id
                imageMap.put("imageID", cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media._ID)));
                // 获得图片显示的名称
                imageMap.put("imageName", cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)));
                // 获得图片的信息
                imageMap.put(
                        "imageInfo",
                        "" + cursor.getLong(cursor
                                .getColumnIndex(MediaStore.Images.Media.SIZE) / 1024)
                                + "kb");
                // 获得图片所在的路径(可以使用路径构建URI)
                imageMap.put("data", cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA)));
                imageList.add(imageMap);
            }
            // 关闭cursor
            cursor.close();
        }
        return imageList;
    }


    /***
     * 获取系统时间
     * 类型是年月日时分秒
     * @return 字符串类型
     */
    public static String getSystemTime(String type){
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        Date date = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }

}
