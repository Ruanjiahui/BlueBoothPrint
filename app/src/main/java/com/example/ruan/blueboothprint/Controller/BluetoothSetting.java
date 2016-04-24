package com.example.ruan.blueboothprint.Controller;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.boothprint.R;
import com.example.ruan.blueboothprint.Moudle.Adapter.ListViewAdapter;
import com.example.ruan.blueboothprint.Moudle.Bluetooth.BluetoothServer;
import com.example.ruan.blueboothprint.Moudle.Bluetooth.Print.HandlerBitmap;
import com.example.ruan.blueboothprint.Moudle.Bluetooth.Print.VoucherF;
import com.example.ruan.blueboothprint.Moudle.Util.AdapterData;
import com.example.ruan.blueboothprint.Moudle.Util.CommonIntent;
import com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint.FilePrint;
import com.example.ruan.blueboothprint.Moudle.Util.Image.ImageTransformation;
import com.example.ruan.blueboothprint.Moudle.Util.PATH;
import com.example.ruan.blueboothprint.Moudle.Util.System.DensityUtil;
import com.example.ruan.blueboothprint.Moudle.Util.System.SystemTool;
import com.example.ruan.blueboothprint.boothprint.BaseActivity;

import java.util.ArrayList;

import static com.example.ruan.blueboothprint.Controller.BluetoothSetting.bluetoothVoucherPic;

/**
 * Created by Administrator on 2016/4/15.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class BluetoothSetting extends BaseActivity implements Animation.AnimationListener, AdapterView.OnItemClickListener {

    private View view = null;

    private RelativeLayout bluetoothVoucherReltive, bluetoothBillReltive;
    private RelativeLayout consigneeBackground = null;
    private View bottomMain = null;
    private ArrayList<Object> list = null;
    private ListViewAdapter adapter = null;
    private ListView bottomListView = null;
    private TextView bottomText = null;
    private Button bottomButtonCancal, bottomButtonEnter;
    private String content = null;
    private BluetoothDevice[] array = null;
    private TextView bluetoothVoucherContent, bluetoothBillContent;
    private int STATE = 0;
    private final int Voucher = 1, Bill = 2;
    private BluetoothDevice VoucherDevice, BillDevice, Device;
    private TextView bluetoothPrint = null;
    public static ImageView bluetoothVoucherPic = null;
    private Button bluetoothName = null;

    /**
     * 这个是程序的开始
     */
    @Override
    public void inti() {
        setTitle("蓝牙设置");
        setRightTitleVisiable(false);
        setLeftTitleColor(R.color.ColorWhite);
        setTopTitleColor(R.color.ColorWhite);
        setTopColor(R.color.ColorBlue);

        view = LayoutInflater.from(context).inflate(R.layout.bluetoothsetting, null);

        id();

        setContent(view);

        intance();

        onClick();

    }

    /**
     * 获取组件的ID
     */
    private void id() {
        bluetoothVoucherReltive = (RelativeLayout) view.findViewById(R.id.bluetoothVoucherReltive);
        bluetoothBillReltive = (RelativeLayout) view.findViewById(R.id.bluetoothBillReltive);
        consigneeBackground = (RelativeLayout) view.findViewById(R.id.consigneeBackground);
        bottomMain = view.findViewById(R.id.bottomMain);
        bottomListView = (ListView) view.findViewById(R.id.bottomListView);
        bottomText = (TextView) view.findViewById(R.id.bottomText);
        bottomButtonCancal = (Button) view.findViewById(R.id.bottomButtonCancal);
        bottomButtonEnter = (Button) view.findViewById(R.id.bottomButtonEnter);
        bluetoothVoucherContent = (TextView) view.findViewById(R.id.bluetoothVoucherContent);
        bluetoothBillContent = (TextView) view.findViewById(R.id.bluetoothBillContent);
        bluetoothPrint = (TextView) view.findViewById(R.id.bluetoothPrint);
        bluetoothVoucherPic = (ImageView) view.findViewById(R.id.bluetoothVoucherPic);
        bluetoothName = (Button) view.findViewById(R.id.bluetoothName);
    }

    /**
     * 组件的点击事件
     */
    private void onClick() {
        bluetoothVoucherReltive.setOnClickListener(this);
        bluetoothBillReltive.setOnClickListener(this);
        bottomButtonCancal.setOnClickListener(this);
        bottomButtonEnter.setOnClickListener(this);
        consigneeBackground.setOnClickListener(this);
        bottomListView.setOnItemClickListener(this);
        bluetoothPrint.setOnClickListener(this);
        bluetoothName.setOnClickListener(this);
    }

    /**
     * 初始化页面
     */
    private void intance() {
        bottomText.setText("蓝牙设备");
        bottomMain.setVisibility(View.GONE);
        consigneeBackground.setAlpha(0);
        DensityUtil.setRelHeightBottom(bottomMain, MainActivity.height / 2);
        consigneeBackground.setVisibility(View.GONE);


        //显示打印单
        bluetoothVoucherPic.setImageBitmap(Consignee.bitmap);

//        array = SystemTool.getBluetoothDevice();
    }

    @Override
    public void Click(View v) {
        switch (v.getId()) {
            case R.id.bluetoothVoucherReltive:
                BottomAnimation();
                Bottoninstance(array);
                STATE = 1;
                break;
            case R.id.bluetoothBillReltive:
                BottomAnimation();
                Bottoninstance(array);
                STATE = 2;
                break;
            case R.id.bottomButtonEnter:
                BottomAnimation();
                switch (STATE) {
                    case Voucher:
                        VoucherDevice = Device;
                        bluetoothVoucherContent.setText(VoucherDevice.getName());
                        break;
                    case Bill:
                        BillDevice = Device;
                        bluetoothBillContent.setText(BillDevice.getName());
                        break;
                }
                break;
            case R.id.bottomButtonCancal:
                BottomAnimation();
                break;
            case R.id.consigneeBackground:
                BottomAnimation();
                break;
            case R.id.bluetoothPrint:
                //重新保存一份凭证
                if (DrawName.nameBitmap != null) {
                    Bitmap bitmap = VoucherF.getVoucherF(VoucherF.compressPic(HandlerBitmap.Readpixel(DrawName.nameBitmap), 100, 50), Consignee.bitmap);
                    new FilePrint().FileSaveTxt(Consignee.name + ".png", PATH.SaveFile, ImageTransformation.Bitmap2Byte(bitmap));
                }else{
                    new FilePrint().FileSaveTxt(Consignee.name + ".png", PATH.SaveFile, ImageTransformation.Bitmap2Byte(Consignee.bitmap));
                }

//                Log.e("Ruan" , "width--" + Consignee.bitmap.getWidth() + "height--" + Consignee.bitmap.getHeight());
                //输出打印即是，将字节流传输给另一个蓝牙设备
//                new BluetoothServer(VoucherDevice, context);
//                new BluetoothServer(BillDevice);
                break;
            case R.id.bluetoothName:
                CommonIntent.IntentActivity(context, DrawName.class);
                break;
        }
    }

    /**
     * 这个是底部的动画
     */
    private void BottomAnimation() {
        if (bottomMain.getVisibility() == View.VISIBLE) {
            Animation animationBackgroud = AnimationUtils.loadAnimation(context, R.anim.backgroudin);
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.bottomin);
            bottomMain.startAnimation(animation);
            //监听动画是否执行完毕
            animation.setAnimationListener(this);
            consigneeBackground.startAnimation(animationBackgroud);
        } else if (bottomMain.getVisibility() == View.GONE) {
            bottomMain.setVisibility(View.VISIBLE);
            consigneeBackground.setAlpha(0.7f);
            consigneeBackground.setVisibility(View.VISIBLE);
            Animation animationBackgroud = AnimationUtils.loadAnimation(context, R.anim.backgroudout);
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.bottomout);
            bottomMain.startAnimation(animation);
            consigneeBackground.startAnimation(animationBackgroud);
        }
    }

    /**
     * 配置下拉菜单的内容
     *
     * @param array
     */
    private void Bottoninstance(BluetoothDevice[] array) {
        list = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            list.add(AdapterData.getMap(context, array[i].getName(), null));
        }
        adapter = new ListViewAdapter(context, list, "ListView");
        bottomListView.setAdapter(adapter);
    }

    /**
     * <p>Notifies the start of the animation.</p>
     *
     * @param animation The started animation.
     */
    @Override
    public void onAnimationStart(Animation animation) {

    }

    /**
     * <p>Notifies the end of the animation. This callback is not invoked
     * for animations with repeat count set to INFINITE.</p>
     *
     * @param animation The animation which reached its end.
     */
    @Override
    public void onAnimationEnd(Animation animation) {
        bottomMain.setVisibility(View.GONE);
        consigneeBackground.setAlpha(0);
        consigneeBackground.setVisibility(View.GONE);
    }

    /**
     * <p>Notifies the repetition of the animation.</p>
     *
     * @param animation The animation which was repeated.
     */
    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Device = array[position];
        for (int i = 0; i < list.size(); i++) {
            if (i == position)
                list.set(i, AdapterData.getMap(context, array[i].getName(), ImageTransformation.Resouce2Drawable(context, R.mipmap.nice)));
            else
                list.set(i, AdapterData.getMap(context, array[i].getName(), null));
        }
        adapter.ChangeData(list);
    }


    //重启改页面的时候重写写入单凭证
    @Override
    protected void onRestart() {
        super.onRestart();
        if (DrawName.nameBitmap != null) {
            Bitmap bitmap = VoucherF.getVoucherF(VoucherF.compressPic(HandlerBitmap.Readpixel(DrawName.nameBitmap), 100, 50), Consignee.bitmap);
            bluetoothVoucherPic.setImageBitmap(bitmap);
        }else{
            bluetoothVoucherPic.setImageBitmap(Consignee.bitmap);
        }
    }
}
