package com.example.ruan.blueboothprint.Moudle.Bluetooth.Print;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.administrator.boothprint.R;

/**
 * Created by Administrator on 2016/4/22.
 */
public class BillF {

    public static Bitmap getBill(Context context) {
        int TextSize = 0;
        //记录当前的高度
        int height = 0;
        int CWidth = 500, CHeight = 300;


        MyCanvas canvas = new MyCanvas(context, CWidth, CHeight);

        TextSize = 20;
        canvas.setCanvaTextSize(TextSize);

        canvas.DrawBitmap(VoucherF.compressPic(ImageTransformation.Resouce2Bitmap(context, R.mipmap.logo1), 200, 50), 70, 20);
        height += 80;


        canvas.DrawBitmap(VoucherF.compressPic(ImageTransformation.Resouce2Bitmap(context, R.mipmap.logo), 100, 100), CWidth - 120, 20);

        canvas.setCanvaTextSize(40);
        canvas.DrawText("北京", 70, height);
        canvas.DrawText("王六", CWidth / 2, height);
        height += 45;

        canvas.DrawBitmap(VoucherF.compressPic(ImageTransformation.Resouce2Bitmap(context, R.mipmap.code1), 50, CHeight - height), 10, height);
        canvas.setCanvaTextSize(20);
        canvas.DrawText("经由地 : 北京", CWidth / 2 - 100 / 2, height);
        height += 70;

        canvas.DrawText("20160422002-01-01", 70, height);
        canvas.DrawText("卡班", CWidth / 2 + 80, height);
        height += 25;

        canvas.DrawText("2016-04-22 14:08", 70, height);
        canvas.DrawText("广州", CWidth / 2 + 80, height);
        height += 25;


        canvas.DrawBitmap(VoucherF.compressPic(ImageTransformation.Resouce2Bitmap(context, R.mipmap.code), 300, CHeight - height), 70, height);

        return canvas.getBitmap();
    }
}
