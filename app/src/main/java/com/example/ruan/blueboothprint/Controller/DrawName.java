package com.example.ruan.blueboothprint.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.boothprint.R;
import com.example.ruan.blueboothprint.Moudle.Bluetooth.Print.HandlerBitmap;
import com.example.ruan.blueboothprint.Moudle.Bluetooth.Print.VoucherF;

/**
 * Created by Administrator on 2016/4/22.
 */
public class DrawName extends Activity implements OnClickListener {
    private ImageView drawImageView;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint paint;
    private int height, width;
    private Button buttonEnter, buttonRester;
    public static Bitmap nameBitmap = null;

//    @Override
//    protected void onResume() {
//        /**
//         * 设置为横屏
//         */
//        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }
//        super.onResume();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawname);


        this.drawImageView = (ImageView) this.findViewById(R.id.drawImageView);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        buttonRester = (Button) findViewById(R.id.buttonRester);


        // 创建一张空白图片
        baseBitmap = Bitmap.createBitmap(MainActivity.width, MainActivity.height, Bitmap.Config.ARGB_8888);
        // 创建一张画布
        canvas = new Canvas(baseBitmap);
        // 画布背景为灰色
        canvas.drawColor(Color.WHITE);
        // 创建画笔
        paint = new Paint();
        // 画笔颜色为红色
        paint.setColor(Color.RED);

        paint.setAntiAlias(true);
        // 宽度5个像素
        paint.setStrokeWidth(15);
        // 先将灰色背景画上
        canvas.drawBitmap(baseBitmap, 0, 0, paint);
        //显示画布
        drawImageView.setImageBitmap(baseBitmap);

        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));

        //监控ImageView的手指事件
        drawImageView.setOnTouchListener(new View.OnTouchListener() {
            int startX;
            int startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 获取手按下时的坐标
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // 获取手移动后的坐标
                        int stopX = (int) event.getX();
                        int stopY = (int) event.getY();
                        // 在开始和结束坐标间画一条线
                        canvas.drawLine(startX, startY, stopX, stopY, paint);
                        // 实时更新开始坐标
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        drawImageView.setImageBitmap(baseBitmap);
                        break;
                }
                return true;
            }
        });

        buttonEnter.setOnClickListener(this);
        buttonRester.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonEnter:
                Consignee.bitmap = VoucherF.getVoucherF(VoucherF.compressPic(HandlerBitmap.Readpixel(baseBitmap), 100, 50), Consignee.bitmap);
                finish();
                break;
            case R.id.buttonRester:
                canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);
                drawImageView.setImageBitmap(baseBitmap);
                break;
        }
    }
}