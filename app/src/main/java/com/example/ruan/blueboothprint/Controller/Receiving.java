package com.example.ruan.blueboothprint.Controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.boothprint.R;
import com.example.ruan.blueboothprint.Moudle.Bluetooth.Print.HandlerBitmap;
import com.example.ruan.blueboothprint.Moudle.Bluetooth.Print.VoucherF;
import com.example.ruan.blueboothprint.Moudle.Util.CommonIntent;
import com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint.FilePrint;
import com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint.FileRead;
import com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint.ReadFile;
import com.example.ruan.blueboothprint.Moudle.Util.Image.ImageTransformation;
import com.example.ruan.blueboothprint.Moudle.Util.PATH;
import com.example.ruan.blueboothprint.Moudle.Util.System.DensityUtil;
import com.example.ruan.blueboothprint.Moudle.Util.System.SystemOperation;
import com.example.ruan.blueboothprint.Moudle.Util.System.SystemTool;
import com.example.ruan.blueboothprint.boothprint.BaseActivity;

/**
 * Created by 19820 on 2016/4/24.
 */
public class Receiving extends BaseActivity {

    private Button recevingButton = null;
    private ImageView recevingImage = null;
    private View view = null;
    private LinearLayout recevingCenter = null;
    private RelativeLayout recevingRelative = null;
    private Button recevingEnter = null;
    private EditText recevingEdit = null;
    private Bitmap bitmap = null;
    private boolean flag = false;
    private String State = null;
    private String TAKE = "Take";
    private String SEND = "Send";

    @Override
    public void inti() {
        setTitle("签名");
        setLeftTitleColor(R.color.ColorWhite);
        setTopTitleColor(R.color.ColorWhite);
        setTopColor(R.color.ColorBlue);
        setRightTitle("保存");
        setRightTitleColor(R.color.ColorWhite);

        view = LayoutInflater.from(context).inflate(R.layout.receving, null);

        recevingButton = (Button) view.findViewById(R.id.recevingButton);
        recevingImage = (ImageView) view.findViewById(R.id.recevingImage);
        recevingCenter = (LinearLayout) view.findViewById(R.id.recevingCenter);
        recevingEnter = (Button) view.findViewById(R.id.recevingEnter);
        recevingEdit = (EditText) view.findViewById(R.id.recevingEdit);
        recevingRelative = (RelativeLayout) view.findViewById(R.id.recevingRelative);


        DensityUtil.setRelWidthCenter(recevingCenter, MainActivity.width / 4 * 3, RelativeLayout.CENTER_IN_PARENT);

        State = getIntent().getStringExtra("data");

        setContent(view);

        recevingEnter.setOnClickListener(this);
        recevingButton.setOnClickListener(this);

    }


    @Override
    public void Click(View v) {
        super.Click(v);
        switch (v.getId()) {
            case R.id.recevingButton:
                flag = true;
                CommonIntent.IntentActivity(context, DrawName.class);
                break;
            case R.id.recevingEnter:
                bitmap = FileRead.ReadBitmap(recevingEdit.getText().toString() + ".png", PATH.SaveFile);
                if (bitmap != null) {
                    recevingImage.setImageBitmap(bitmap);
                    recevingRelative.setVisibility(View.GONE);
                    recevingButton.setVisibility(View.VISIBLE);
                } else {
                    SystemTool.showToast(context, "没有该单号");
                }
                break;
        }

    }

    @Override
    public void setRightTextClick() {
        super.setRightTextClick();
        if (recevingRelative.getVisibility() == View.GONE) {
            new FilePrint().FileSaveTxt(recevingEdit.getText().toString() + ".png", PATH.SaveFile, ImageTransformation.Bitmap2Byte(bitmap));
            SystemTool.showToast(context, "保存成功");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (flag) {
            //这个话的意思就是将bitmap变成可编辑的图片否则就是出错
            Bitmap b = BitmapFactory.decodeByteArray(ImageTransformation.Bitmap2Byte(bitmap), 0, ImageTransformation.Bitmap2Byte(bitmap).length).copy(Bitmap.Config.ARGB_8888, true);
            if (TAKE.equals(State))
                bitmap = VoucherF.getVoucherF(VoucherF.compressPic(HandlerBitmap.Readpixel(DrawName.nameBitmap), 100, 50), b);
            else if (SEND.equals(State)){
                bitmap = VoucherF.getVocherFSend(VoucherF.compressPic(HandlerBitmap.Readpixel(DrawName.nameBitmap), 100, 50), b);
            }
            recevingImage.setImageBitmap(bitmap);
        }
    }
}
