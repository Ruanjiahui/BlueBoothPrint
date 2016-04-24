package com.example.ruan.blueboothprint.Moudle.Bluetooth.Print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.administrator.boothprint.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/20.
 */
public class MyForm extends Canvas {
    //默认表单的边距为5px
    private static int margin = 5;
    private Bitmap bitmap = null;
    private int width;
    private int height;
    private int horizontal = 2;
    private int vertical = 5;
    private int color;
    private Context context = null;
    private Paint paint = null;
    private ArrayList<String> list = null;
    public static int LEFT = 1;
    public static int CENTER = 2;
    public static int RIGHT = 3;
    private int gravity = LEFT;
    private int splitX;
    private int splitY;
    //手机状态栏的高度
    private int stateHeight = 20;
    //默认字体的大小
    private int textSize = 20;

    public MyForm(Context context, int width, int height) {
        this(context, width, height, margin);
    }


    public MyForm(Context context, int width, int height, int margin) {
        this.width = width;
        this.height = height;
        MyForm.margin = margin;
        this.context = context;
        inti();
    }

    /**
     * 初始化表单
     * 刚开始构建表单就是在这个方法里面
     */
    public void inti() {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        this.setBitmap(bitmap);

        paint = CreatePaint();
    }

    /**
     * 设置表单的背景颜色
     *
     * @param color 背景颜色
     */
    public void setFormBackground(int color) {
        this.color = color;
        this.drawColor(context.getResources().getColor(color));
    }

    /**
     * 设置表单的横纵的数目
     *
     * @param horizontal 表单的垂直数目
     * @param vertical   表单的水平数目
     */
    public void setSplit(int horizontal, int vertical) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    /**
     * 这个方法就是创建表单
     */
    public void CreateForm() {
        //画表单的头部的横线
        DrawLine(margin, margin, width - margin, margin);
        //画表单两边的横线
        DrawLine(margin, margin, margin, height - margin);
        DrawLine(width - margin, margin, width - margin, height - margin);
        //画表单底部的横线
        DrawLine(margin, height - margin, width - margin, height - margin);

        //通过动态循环画出表单里面的分割线
        //画表单里面横向的横线
        splitX = (width - 2 * margin) / horizontal;
        splitY = (height - 2 * margin) / vertical;
        for (int i = 1; i < horizontal; i++) {
            DrawLine(margin + splitX * i, margin, margin + splitX * i, height - margin);
        }
        //画表单里面纵向的横线
        for (int i = 1; i < vertical; i++) {
            DrawLine(margin, margin + splitY * i, width - margin, margin + splitY * i);
        }

        //内容不为空则将内容编写到表单里面去
        if (list != null && list.size() != 0)
            WriteForm();
    }

    /**
     * 将文字写进表单里面
     */
    private void WriteForm() {
        int n = 0;
        for (int i = 0; i < vertical; i++) {
            for (int r = 0; r < horizontal; r++) {
                if (n == list.size())
                    return;
                if (gravity == LEFT)
                    //由于文字编写的坐标是从手机最顶端开始的不是画布开始的所以得加上手机状态栏的高度
                    //stateHeight + margin + splitY * i + (splitY - textSize) / 2
                    //首先是状态栏的高度 + 距离表单的上边距 + 每个格子的高度 + （每个格子的高度 - 字体的大小） / 2 = 就是字体距离上面那条线的高度
                    DrawText(list.get(n), margin + splitX * r, stateHeight + margin + splitY * i + (splitY - textSize) / 2);
                if (gravity == CENTER) {
                    DrawText(list.get(n), margin + splitX * r + ((splitX - textSize * list.get(i).length()) / 2), stateHeight + margin + splitY * i + (splitY - textSize) / 2);
                }if (gravity == RIGHT)
                    DrawText(list.get(n), margin + splitX * r + (splitX - textSize * list.get(i).length()), stateHeight + margin + splitY * i + (splitY - textSize) / 2);

                n++;
            }
        }
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        paint.setTextSize(textSize);
    }

    public void setGravityForm(int gravity) {
        this.gravity = gravity;
    }

    /**
     * 设置表单里面的内容
     * 传参数进来就是一个链表
     * 排写内容就是从左往右编写
     * 从上往下编写的
     *
     * @param list 表单内容的链表
     */
    public void setFormContent(ArrayList<String> list) {
        this.list = list;
    }

    /**
     * 获取表单的图片
     *
     * @return
     */
    public Bitmap getForm() {
        return bitmap;
    }


    /**
     * 画线
     *
     * @param startx 开始的横坐标
     * @param starty 开始的纵坐标
     * @param endx   结束的横坐标
     * @param endy   结束的纵坐标
     */
    private void DrawLine(int startx, int starty, int endx, int endy) {
        this.drawLine(startx, starty, endx, endy, paint);
    }

    /**
     * 编写文字
     *
     * @param content 文字的内容
     * @param startx  编写文字的开始横坐标
     * @param starty  编写文字的开始纵坐标
     */
    public void DrawText(String content, int startx, int starty) {
        this.drawText(content, startx, starty, paint);
    }

    /**
     * 创建画笔
     *
     * @return
     */
    private Paint CreatePaint() {
        Paint paint = new Paint();

        paint.setColor(context.getResources().getColor(R.color.ColorBlack));
        paint.setStrokeWidth(2);
        paint.setTextSize(20);

        return paint;
    }

}
