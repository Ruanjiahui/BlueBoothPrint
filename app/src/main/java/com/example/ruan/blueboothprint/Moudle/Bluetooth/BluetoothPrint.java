package com.example.ruan.blueboothprint.Moudle.Bluetooth;

import android.graphics.Bitmap;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/19.
 */
public class BluetoothPrint {

    private String content = null;
    private Bitmap bitmap = null;
    private OutputStream outputStream = null;
    private ArrayList<byte[]> list = null;

    //打印机复位
    private byte[] reset = new byte[]{0x1B,0x40};
    //设置顺时针选择90度
    private byte[] rotate = new byte[]{0x1B,0x56,0x01};
    //设置不旋转
    private byte[] unrotate = new byte[]{0x1B,0x56,0x00};
    //设置居中
    private byte[] enter = new byte[]{0x1B,0x61,0x01};
    //设置粗体
    private byte[] border = new byte[]{0x1B,0x45,0x01};
    //设置字体3倍大小
    private byte[] size = new byte[]{0x1D,0x21,0x22};
    // 还原默认字体大小，取消下划线，取消粗体模式
    private byte[] restart = new byte[]{0x1B,0x21,0x00};
    //设置行间距为：3.75mm
    private byte[] space = new byte[]{0x1B,0x33,0x1E};
    //设置默认行间距：1mm
    private byte[] startspace = new byte[]{0x1B,0x32};
    //选择反白打印模式
    private byte[] WhiteBlack = new byte[]{0x1D,0x42,0x01};
    //取消反白打印模式
    private byte[] unWhiteBlack = new byte[]{0x1D,0x42,0x00};


    /**
     * 打印的文字
     * @param content
     */
    public void PrintText(String content) throws UnsupportedEncodingException {
        this.content = content;
        list.add(content.getBytes("gb2312"));
    }


    /**
     * 打印的图片
     * @param bitmap
     */
    public void PrintBitmap(Bitmap bitmap , int width , int height){
        this.bitmap = bitmap;
        Bitmap b = PicFromPrintUtils.compressPic(bitmap , width , height);
        list.add(PicFromPrintUtils.draw2PxPoint(b, width, height));
    }

    public BluetoothPrint(OutputStream outputStream){
        this.outputStream = outputStream;
        list = new ArrayList<>();
    }

    /**
     * 将打印的信息传送给打印机
     */
    public void PrintWrite() throws IOException {
        for (int i = 0 ; i < list.size() ; i++) {
            outputStream.write(list.get(i));
        }
        outputStream.flush();
        outputStream.close();
    }
}
