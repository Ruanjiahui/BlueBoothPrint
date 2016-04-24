package com.example.ruan.blueboothprint.Controller;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.administrator.boothprint.R;
import com.example.ruan.blueboothprint.Moudle.Adapter.ListViewAdapter;
import com.example.ruan.blueboothprint.Moudle.Bluetooth.Print.VoucherF;
import com.example.ruan.blueboothprint.Moudle.Util.AdapterData;
import com.example.ruan.blueboothprint.Moudle.Util.CommonIntent;
import com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint.FilePrint;
import com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint.Form;
import com.example.ruan.blueboothprint.Moudle.Util.Image.ImageTransformation;
import com.example.ruan.blueboothprint.Moudle.Util.PATH;
import com.example.ruan.blueboothprint.Moudle.Util.System.DensityUtil;
import com.example.ruan.blueboothprint.Moudle.Util.System.SystemTool;
import com.example.ruan.blueboothprint.View.DialogClick;
import com.example.ruan.blueboothprint.View.MyCanvas;
import com.example.ruan.blueboothprint.View.MyDialog;
import com.example.ruan.blueboothprint.boothprint.BaseActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/11.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Consignee extends BaseActivity implements Animation.AnimationListener, AdapterView.OnItemClickListener, DialogClick {

    private View view = null;
    private View bottomMain = null;
    private RelativeLayout consigneeToLinear, consigneeFromLinear;
    private RelativeLayout consigneeGoodsTypeRelative = null;
    private RelativeLayout consigneeBackground = null;
    private RelativeLayout consigneeGoodsPickRelative, consigneeGoodsStateRelative, consigneeGoodsPayRelative, consigneeGoodsPayStateRelative;
    private Button bottomButtonCancal, bottomButtonEnter;
    private ListView bottomListView = null;
    private TextView bottomText = null;
    private Button consigneeSave, consigneePrint;

    private EditText consigneeGoodsname, consigneeGoodspack, consigneeGoodsweight, consigneeGoodsvolume;
    private TextView consigneeGoodsTypename;
    private EditText consigneeCostservice, consigneeCostDelivery, consigneeCosttransfer, consigneeCostLoading, consigneeCosttake, consigneeCostOther;
    private TextView consigneePickTypename, consigneeStateTypename, consigneePayTypename, consigneePayStateTypename;

    private TextView consigneeToname, consigneeTophone, consigneeToaddress;
    private TextView consigneeFromname, consigneeFromphone, consigneeFromaddress;

    private String[] array = null;
    private ArrayList<Object> list = null;
    private ListViewAdapter adapter = null;

    private MyDialog dialog = null;
    //判断是收件还是发件
    private int flag = 0;

    private String content = null;

    private int STATE = 0;
    private final int TO = 1, PICK = 2, SEND = 3, PAY = 4, PAYSTATE = 5;
    //这个是暂时记录单号的标志
    public static String name = null;

    /**
     * 这个是程序的开始
     */
    @Override
    public void inti() {
        setTitle("收件开单");
        setTopColor(R.color.ColorBlue);
        setRightTitleVisiable(false);
        setTopTitleColor(R.color.ColorWhite);
        setLeftTitleColor(R.color.ColorWhite);
        setContentColor(R.color.ColorWhiteSmoke);

        view = LayoutInflater.from(context).inflate(R.layout.consignee, null);

        setContent(view);

        //获取组件ID
        id();

        // 设置各个组件的属性包括配置一些初始化的配置
        instance();

        //按钮的点击事件点击就绪
        Click();

    }

    private void id() {
        consigneeToLinear = (RelativeLayout) view.findViewById(R.id.consigneeToLinear);
        consigneeFromLinear = (RelativeLayout) view.findViewById(R.id.consigneeFromLinear);
        bottomMain = view.findViewById(R.id.bottomMain);
        consigneeGoodsTypeRelative = (RelativeLayout) view.findViewById(R.id.consigneeGoodsTypeRelative);
        consigneeBackground = (RelativeLayout) view.findViewById(R.id.consigneeBackground);
        consigneeGoodsPickRelative = (RelativeLayout) view.findViewById(R.id.consigneeGoodsPickRelative);
        consigneeGoodsStateRelative = (RelativeLayout) view.findViewById(R.id.consigneeGoodsStateRelative);
        consigneeGoodsPayRelative = (RelativeLayout) view.findViewById(R.id.consigneeGoodsPayRelative);
        consigneeGoodsPayStateRelative = (RelativeLayout) view.findViewById(R.id.consigneeGoodsPayStateRelative);
        bottomButtonCancal = (Button) view.findViewById(R.id.bottomButtonCancal);
        bottomButtonEnter = (Button) view.findViewById(R.id.bottomButtonEnter);
        bottomListView = (ListView) view.findViewById(R.id.bottomListView);
        bottomText = (TextView) view.findViewById(R.id.bottomText);
        consigneeSave = (Button) view.findViewById(R.id.consigneeSave);
        consigneePrint = (Button) view.findViewById(R.id.consigneePrint);
        //下面是表单的组件ID
        consigneeGoodsname = (EditText) view.findViewById(R.id.consigneeGoodsname);
        consigneeGoodspack = (EditText) view.findViewById(R.id.consigneeGoodspack);
        consigneeGoodsweight = (EditText) view.findViewById(R.id.consigneeGoodsweight);
        consigneeGoodsvolume = (EditText) view.findViewById(R.id.consigneeGoodsvolume);
        consigneeCostservice = (EditText) view.findViewById(R.id.consigneeCostservice);
        consigneeCostDelivery = (EditText) view.findViewById(R.id.consigneeCostDelivery);
        consigneeCosttransfer = (EditText) view.findViewById(R.id.consigneeCosttransfer);
        consigneeCostLoading = (EditText) view.findViewById(R.id.consigneeCostLoading);
        consigneeCosttake = (EditText) view.findViewById(R.id.consigneeCosttake);
        consigneeCostOther = (EditText) view.findViewById(R.id.consigneeCostOther);
        consigneeGoodsTypename = (TextView) view.findViewById(R.id.consigneeGoodsTypename);
        consigneePickTypename = (TextView) view.findViewById(R.id.consigneePickTypename);
        consigneeStateTypename = (TextView) view.findViewById(R.id.consigneeStateTypename);
        consigneePayTypename = (TextView) view.findViewById(R.id.consigneePayTypename);
        consigneePayStateTypename = (TextView) view.findViewById(R.id.consigneePayStateTypename);


        consigneeToname = (TextView) view.findViewById(R.id.consigneeToname);
        consigneeTophone = (TextView) view.findViewById(R.id.consigneeTophone);
        consigneeToaddress = (TextView) view.findViewById(R.id.consigneeToaddress);
        consigneeFromname = (TextView) view.findViewById(R.id.consigneeFromname);
        consigneeFromphone = (TextView) view.findViewById(R.id.consigneeFromphone);
        consigneeFromaddress = (TextView) view.findViewById(R.id.consigneeFromaddress);
    }

    private void instance() {
        DensityUtil.setRelHeight(consigneeToLinear, MainActivity.height / 8);
        DensityUtil.setRelHeightBottom(consigneeFromLinear, MainActivity.height / 8, R.id.consigneeToLinear);
        DensityUtil.setRelHeightBottom(bottomMain, MainActivity.height / 2);

        bottomMain.setVisibility(View.GONE);
        consigneeBackground.setAlpha(0);
        consigneeBackground.setVisibility(View.GONE);

    }

    /**
     * 配置下拉菜单的内容
     *
     * @param array
     */
    private void Bottoninstance(String[] array) {
        list = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            list.add(AdapterData.getMap(context, array[i], null));
        }
        adapter = new ListViewAdapter(context, list, "ListView");
        bottomListView.setAdapter(adapter);
    }

    private void Click() {
        consigneeToLinear.setOnClickListener(this);
        consigneeFromLinear.setOnClickListener(this);
        consigneeGoodsTypeRelative.setOnClickListener(this);
        consigneeGoodsPickRelative.setOnClickListener(this);
        consigneeGoodsStateRelative.setOnClickListener(this);
        consigneeGoodsPayRelative.setOnClickListener(this);
        consigneeGoodsPayStateRelative.setOnClickListener(this);
        bottomButtonCancal.setOnClickListener(this);
        consigneeBackground.setOnClickListener(this);
        bottomListView.setOnItemClickListener(this);
        bottomButtonEnter.setOnClickListener(this);
        consigneeSave.setOnClickListener(this);
        consigneePrint.setOnClickListener(this);
    }

    @Override
    public void Click(View v) {
        super.Click(v);
        switch (v.getId()) {
            case R.id.consigneeToLinear:
                flag = 1;
                PATH.addressMoudle = null;
                CommonIntent.IntentActivity(context, Address.class);
                break;
            case R.id.consigneeFromLinear:
                flag = 2;
                PATH.addressMoudle = null;
                CommonIntent.IntentActivity(context, Address.class);
                break;
            case R.id.consigneeGoodsTypeRelative:
                array = getResources().getStringArray(R.array.bottomType);
                Bottoninstance(array);
                BottomAnimation();
                bottomText.setText("收件类型");
                STATE = TO;
                break;
            case R.id.consigneeGoodsPickRelative:
                array = getResources().getStringArray(R.array.bottomPick);
                Bottoninstance(array);
                BottomAnimation();
                bottomText.setText("取货方式");
                STATE = PICK;
                break;
            case R.id.consigneeGoodsStateRelative:
                array = getResources().getStringArray(R.array.bottomState);
                Bottoninstance(array);
                BottomAnimation();
                bottomText.setText("发货状态");
                STATE = SEND;
                break;
            case R.id.consigneeGoodsPayRelative:
                array = getResources().getStringArray(R.array.bottomPay);
                Bottoninstance(array);
                BottomAnimation();
                bottomText.setText("付款方式");
                STATE = PAY;
                break;
            case R.id.consigneeGoodsPayStateRelative:
                array = getResources().getStringArray(R.array.bottomPayState);
                Bottoninstance(array);
                BottomAnimation();
                bottomText.setText("付款状态");
                STATE = PAYSTATE;
                break;
            case R.id.consigneeBackground:
                BottomAnimation();
                break;
            case R.id.bottomButtonCancal:
                BottomAnimation();
                break;
            case R.id.bottomButtonEnter:
                BottomAnimation();
                switch (STATE) {
                    case TO:
                        consigneeGoodsTypename.setText(content);
                        break;
                    case PICK:
                        consigneePickTypename.setText(content);
                        break;
                    case PAY:
                        consigneePayTypename.setText(content);
                        break;
                    case PAYSTATE:
                        consigneePayStateTypename.setText(content);
                        break;
                    case SEND:
                        consigneeStateTypename.setText(content);
                        break;
                }
                break;
            //点击保存收件
            case R.id.consigneeSave:
                break;
            //点击保存和打印
            case R.id.consigneePrint:
                //获取物品的品名
                name = consigneeGoodsname.getText().toString();

                bitmap = VoucherF.getVocher(context, getForm());
                new FilePrint().FileSaveTxt(name + ".png", PATH.SaveFile, ImageTransformation.Bitmap2Byte(bitmap));
                CommonIntent.IntentActivity(context, BluetoothSetting.class);
                switch (SystemTool.isBluetooth()) {
                    //没有蓝牙
                    case 0:
                        SystemTool.showToast(context, "该移动设备没有蓝牙模块");
                        break;
                    //没有开启蓝牙
                    case 1:
                        //弹出对话框
                        createDialog();
                        break;
                    //开启蓝牙
                    case 2:
                        bitmap = VoucherF.getVocher(context, getForm());
                        new FilePrint().FileSaveTxt("123.png", PATH.SaveFile, ImageTransformation.Bitmap2Byte(bitmap));
                        CommonIntent.IntentActivity(context, BluetoothSetting.class);
                        //跳转到打印页面
                        //制作打印凭证
//                        Bitmap bitmap = VoucherF.getVocher(context, getForm());
                        //将图片保存到本地
                        //第一个参数是保存的文件名
                        //第二个参数是保存的绝对路径
                        //第三个参数是保存文件的字节流
//                        new FilePrint().FileSaveTxt("123.txt" , PATH.SaveFile , "111".getBytes());
//                        CommonIntent.IntentActivity(context, BluetoothSetting.class);
                        break;
                }
                break;
        }
    }

    public static Bitmap bitmap = null;

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
        content = array[position];
        for (int i = 0; i < list.size(); i++) {
            if (i == position)
                list.set(i, AdapterData.getMap(context, array[i], ImageTransformation.Resouce2Drawable(context, R.mipmap.nice)));
            else
                list.set(i, AdapterData.getMap(context, array[i], null));
        }
        adapter.ChangeData(list);
    }

    /**
     * 将收件的表单格式化
     *
     * @return
     */
    private Form getForm() {
        Form form = new Form(context);
        form.setCosttakeContent(consigneeCosttake.getText().toString());
        form.setCostDeliveryContent(consigneeCostDelivery.getText().toString());
        form.setCostLoadingContent(consigneeCostLoading.getText().toString());
        form.setCostOtherContent(consigneeCostOther.getText().toString());
        form.setCostserviceContent(consigneeCostservice.getText().toString());
        form.setCosttransferContent(consigneeCosttransfer.getText().toString());
        form.setGoodsnameContent(consigneeGoodsname.getText().toString());
        form.setGoodspackContent(consigneeGoodspack.getText().toString());
        form.setGoodsTypeContent(consigneeGoodsTypename.getText().toString());
        form.setGoodsvolumeContent(consigneeGoodsvolume.getText().toString());
        form.setGoodsweightContent(consigneeGoodsweight.getText().toString());
        form.setPayStateTypename(consigneePayStateTypename.getText().toString());
        form.setPayTypename(consigneePayTypename.getText().toString());
        form.setPickTypename(consigneePickTypename.getText().toString());
        form.setStateTypename(consigneeStateTypename.getText().toString());

        form.setConsigneeFromaddress(FromAddress);
        form.setConsigneeFromConvider(FromConvider);
        form.setConsigneeFromCity(FromCity);
        form.setConsigneeFromname(FromName);
        form.setConsigneeFromphone(FromPhone);

        form.setConsigneeToaddress(ToAddress);
        form.setConsigneeToConvider(ToConvider);
        form.setConsigneeToname(ToName);
        form.setConsigneeTophone(ToPhone);
        form.setConsigneeToCity(ToCity);


        return form;
    }

    /**
     * 创建对话框
     */
    private void createDialog() {
        dialog = new MyDialog(context);
        //设置默认的对话框宽高度
        dialog.setWidth(MainActivity.width / 5 * 4);
        dialog.setTopImage(ImageTransformation.Resouce2Drawable(context, R.mipmap.warning));
        dialog.onDialogClick(this);
        dialog.show();
    }

    /**
     * 对话框的点击事件
     *
     * @param v
     */
    @Override
    public void onDialogClick(View v) {
        switch (v.getId()) {
            case R.id.dialogcancal:
                dialog.dismiss();
                break;
            case R.id.dialogenter:
                Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                startActivity(intent);
                dialog.dismiss();
                break;
        }
    }

    private String FromConvider, FromCity, FromAddress, FromName, FromPhone = null;
    private String ToCity, ToConvider, ToAddress, ToName, ToPhone = null;

    @Override
    protected void onRestart() {
        super.onRestart();
        if (PATH.addressMoudle != null) {
            if (flag == 1) {
                ToConvider = PATH.addressMoudle.getConvider();
                ToCity = PATH.addressMoudle.getCity();
                ToAddress = PATH.addressMoudle.getAddress();
                ToName = PATH.addressMoudle.getName();
                ToPhone = PATH.addressMoudle.getPhone();
                consigneeToname.setText(getResources().getString(R.string.Toname) + PATH.addressMoudle.getName());
                consigneeTophone.setText(PATH.addressMoudle.getPhone());
                consigneeToaddress.setText(getResources().getString(R.string.Toaddress) + ToConvider + ToCity + ToAddress);
            } else if (flag == 2) {
                FromConvider = PATH.addressMoudle.getConvider();
                FromCity = PATH.addressMoudle.getCity();
                FromAddress = PATH.addressMoudle.getAddress();
                FromName = PATH.addressMoudle.getName();
                FromPhone = PATH.addressMoudle.getPhone();
                consigneeFromname.setText(getResources().getString(R.string.Fromname) + FromName);
                consigneeFromphone.setText(FromPhone);
                consigneeFromaddress.setText(getResources().getString(R.string.Fromaddress) + FromConvider + FromCity + FromAddress);
            }
        }
    }
}
