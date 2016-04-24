package com.example.ruan.blueboothprint.Moudle.Bluetooth.Print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.administrator.boothprint.R;
import com.example.ruan.blueboothprint.Controller.Consignee;
import com.example.ruan.blueboothprint.Controller.DrawName;
import com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint.Form;
import com.example.ruan.blueboothprint.Moudle.Util.System.SystemTool;
import com.example.ruan.blueboothprint.boothprint.view.Barcode;
import com.google.zxing.BarcodeFormat;

/**
 * Created by Administrator on 2016/4/22.
 */
public class VoucherF {

    private static int CWidth = 500, CHeight = 1500;
    private static int Nameheight = 0;

    public static Bitmap getVocher(Context context, Form formContent) {
        int TextSize = 0;
        //记录当前的高度
        int height = 0;


        MyCanvas canvas = new MyCanvas(context, CWidth, CHeight);

        TextSize = 20;
        canvas.setCanvaTextSize(TextSize);

        //标题
        canvas.DrawBitmap(compressPic(ImageTransformation.Resouce2Bitmap(context, R.mipmap.logo1), 200, 50), CWidth / 2 - 200 / 2, 0);
        height = TextSize;

        //二维码
        canvas.DrawBitmap(compressPic(ImageTransformation.Resouce2Bitmap(context, R.mipmap.logo), 100, 100), CWidth - 100, 0);
        height += 100;

        //运货的城市
        canvas.DrawText(formContent.getConsigneeFromConvider() + formContent.getConsigneeFromCity(), 0, height);
        canvas.DrawText(formContent.getConsigneeToConvider() + formContent.getConsigneeToCity(), CWidth / 2, height);
        height += 30;

        //横线
        canvas.DrawLine(0, height, CWidth, height);
        height += 2;

        //条形码
        canvas.DrawBitmap(compressPic(Barcode.CreateOneDCode("15119481373", 400, 50), 400, 50), CWidth / 2 - 200, height);
        height += 50;

        //横线
        canvas.DrawLine(0, height, CWidth, height);
        height += 2;

        //运单号信息
        canvas.DrawText("运单号:123456789", 0, height);
        canvas.DrawText("日期:" + SystemTool.getSystemTime("yyyy-MM-dd"), CWidth / 2, height);
        height += 30;

        //横线
        canvas.DrawLine(0, height, CWidth, height);
        height += 2;

        //收件方的信息标题
        canvas.DrawText("收件方的信息", 20, height);
        canvas.DrawText("目的地", CWidth / 2, height);
        height += 30;

        //收件方的信息
        canvas.DrawText(formContent.getConsigneeToname(), 20, height);
        canvas.DrawText(formContent.getConsigneeToConvider() + formContent.getConsigneeToCity(), CWidth / 2, height);
        height += 30;


        //收件方的具体地址
        canvas.DrawText(formContent.getConsigneeToaddress(), 20, height);
        height += 30;


        //横线
        canvas.DrawLine(0, height, CWidth, height);
        height += 2;

        //发货方的信息标题
        canvas.DrawText("发货方的信息", 20, height);
        canvas.DrawText("发货地", CWidth / 2, height);
        height += 30;


        //发件方的信息
        canvas.DrawText(formContent.getConsigneeFromname(), 20, height);
        canvas.DrawText(formContent.getConsigneeFromConvider() + formContent.getConsigneeFromCity(), CWidth / 2, height);
        height += 80;

        //横线
        canvas.DrawLine(0, height, CWidth, height);
        height += 2;

        //储运注意事项的标题
        canvas.setCanvaTextSize(15);
        canvas.DrawText("储运注意事项及其他:", 0, height);
        height += 20;

        //储运注意事项的内容
        canvas.setCanvaTextSize(10);
        canvas.DrawText("因为所做的是打印图片（或者是二维码） ，先发送一个请求，从网络下载图片或者是二维码，得到这张图片过后", 0, height);
        height += 25;
        canvas.DrawText("，进行压缩到你想要的大小，在对压缩后的图片进行二值化，即黑白化，但是图片本身会有个α的值（即透明度）", 0, height);
        height += 25;
        canvas.DrawText("的问题，如果不进行处理的话，即使是黑白化的图片，得到图片的像素点也不是很精确。对于去透明度处理过后，", 0, height);
        height += 25;
        canvas.DrawText("在对这张图的每个最标点去像素值，即xxx  X  xxx大的图片，会有xxx X xxx个像素点。求出每个像素点的", 0, height);
        height += 25;
        canvas.DrawText("r、g、b的值，通过换算公式进行换算，得到一个像素值（0-255之间）。如果是0-128是黑色，129-255是白色", 0, height);
        height += 25;
        canvas.DrawText("（非黑即白化），这样每个点的像素值就可以确定了。", 0, height);
        height += 25;

        formContent.setForm();
        //添加表单
        MyForm form = new MyForm(context, 500, 600, 10);
        form.setSplit(2, 15);
        form.setFormContent(formContent.toArrayString());
        form.CreateForm();
        canvas.DrawBitmap(form.getForm(), 0, height);
        height += 600;


        //备注表单
        MyForm Zform = new MyForm(context, 500, 50, 10);
        Zform.setSplit(1, 1);
        Zform.CreateForm();
        canvas.DrawBitmap(Zform.getForm(), 0, height);
        height += 50;

        //签名
        canvas.setCanvaTextSize(15);
        Nameheight = height;
        canvas.DrawText("发货人签名:", 0, height);
        canvas.DrawText("收件人签名:", CWidth / 2, height);

        height += 50;


        //日期
        canvas.setCanvaTextSize(10);
        canvas.DrawText(SystemTool.getSystemTime("yyyyMMddHHmmss"), CWidth - 100, height);
        return canvas.getBitmap();
    }

    public static Bitmap getVoucherF(Bitmap nbitmap, Bitmap obitmap) {
        Canvas canvas = new Canvas(obitmap);
        Paint paint = new Paint();
        //发货人的签名
        if (nbitmap != null)
            canvas.drawBitmap(nbitmap, 80, Nameheight, paint);
        //收件人的签名
//        if (nbitmap != null)
//            canvas.drawBitmap(nbitmap, 80 + CWidth / 2, Nameheight , paint);

        return obitmap;
    }


    /**
     * 对图片进行压缩（去除透明度）
     *
     * @param bitmapOrg
     */
    public static Bitmap compressPic(Bitmap bitmapOrg, int nwidth, int nheight) {
        // 获取这个图片的宽和高
        int width = bitmapOrg.getWidth();
        int height = bitmapOrg.getHeight();
        // 定义预转换成的图片的宽度和高度
        int newWidth = nwidth;
        int newHeight = nheight;
        Bitmap targetBmp = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Canvas targetCanvas = new Canvas(targetBmp);
        targetCanvas.drawColor(0xffffffff);
        targetCanvas.drawBitmap(bitmapOrg, new Rect(0, 0, width, height), new Rect(0, 0, newWidth, newHeight), null);
        return targetBmp;
    }
}
