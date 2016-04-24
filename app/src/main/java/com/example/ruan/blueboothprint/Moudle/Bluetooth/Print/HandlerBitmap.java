package com.example.ruan.blueboothprint.Moudle.Bluetooth.Print;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/22.
 */
public class HandlerBitmap {


    /**
     * 这里是截取像素点获取有效的像素点
     *
     * @param bitmap
     */
    public static Bitmap Readpixel(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int pixel = 0;
        int minx = 100000, maxx = 0, miny = 100000, maxy = 0;


        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> listx = new ArrayList<>();
        ArrayList<Integer> listy = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int r = 0; r < width; r++) {
                pixel = bitmap.getPixel(r, i);
                if (pixel != -1) {
                    //获取像素点最大的X坐标
                    if (r > maxx)
                        maxx = r;
                    //获取像素点最小的X坐标
                    if (r < minx)
                        minx = r;
                    //获取像素点最大的Y坐标
                    if (i > maxy)
                        maxy = i;
                    //获取像素点最小的Y坐标
                    if (i < miny)
                        miny = i;
                    list.add(pixel);
                    listx.add(r);
                    listy.add(i);
                }
            }
        }

        int nwidth, nheight;
        nwidth = maxx - minx + 1;
        nheight = maxy - miny + 1;


        Bitmap b = Bitmap.createBitmap(nwidth, nheight, Bitmap.Config.ARGB_8888);


        int n = 0;
        for (int i = 0; i < nheight; i++) {
            for (int r = 0; r < nwidth; r++) {
                if (n < list.size() && r + minx == listx.get(n) && i + miny == listy.get(n)) {
                    b.setPixel(r, i, list.get(n));
                    n++;
                } else {
                    b.setPixel(r, i, -1);
                }
            }
        }
        return b;
    }

}
