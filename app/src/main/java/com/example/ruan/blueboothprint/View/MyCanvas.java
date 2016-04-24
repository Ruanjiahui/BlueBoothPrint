package com.example.ruan.blueboothprint.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.administrator.boothprint.R;

/**
 * Created by Administrator on 2016/4/20.
 */
public class MyCanvas {

    private Context context = null;
    private Bitmap bitmap = null;
    private Canvas canvas = null;
    private Paint paint = null;
    private int width = 200;
    private int height = 200;
    private int color = 0;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public MyCanvas(Context context) {
        this.context = context;
        inti();
    }

    public void inti() {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        canvas = new Canvas(bitmap);
        //设置画布默认为白色
        setCanvasColor(R.color.ColorWhite);

        paint = CreatePaint();

    }

    /**
     * 设置画布的背景颜色
     *
     * @param color
     */
    public void setCanvasColor(int color) {
        this.color = color;
        canvas.drawColor(context.getResources().getColor(color));
    }

    /**
     * 画线
     *
     * @param startx 开始的横坐标
     * @param starty 开始的纵坐标
     * @param endx   结束的横坐标
     * @param endy   结束的纵坐标
     */
    public void DrawLine(int startx, int starty, int endx, int endy) {
        canvas.drawLine(startx, starty, endx, endy, paint);
    }

    /**
     * 画布写字
     * @param content
     * @param startx
     * @param starty
     */
    public void DrawText(String content , int startx , int starty){
        canvas.drawText(content , startx , starty , paint);
    }


    private Paint CreatePaint() {
        Paint paint = new Paint();

        paint.setColor(context.getResources().getColor(R.color.ColorBlack));
        paint.setStrokeWidth(2);

        return paint;
    }




    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setForm(Bitmap bitmap){
        this.bitmap = bitmap;
        canvas.setBitmap(bitmap);
    }
}
