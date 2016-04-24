package com.example.ruan.blueboothprint.Controller;

import android.util.Log;
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
import com.example.ruan.blueboothprint.Moudle.Database.CheckDatabase;
import com.example.ruan.blueboothprint.Moudle.Database.GetDatabaseData;
import com.example.ruan.blueboothprint.Moudle.Moudle.AddressMoudle;
import com.example.ruan.blueboothprint.Moudle.Util.AdapterData;
import com.example.ruan.blueboothprint.Moudle.Util.ConviderCity;
import com.example.ruan.blueboothprint.Moudle.Util.Image.ImageTransformation;
import com.example.ruan.blueboothprint.Moudle.Util.System.DensityUtil;
import com.example.ruan.blueboothprint.boothprint.Applications;
import com.example.ruan.blueboothprint.boothprint.BaseActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/13.
 */
public class AddorUpdataAdress extends BaseActivity implements Animation.AnimationListener, AdapterView.OnItemClickListener {

    private View view = null;

    private Button addressBut = null;
    private EditText addressName, addressPhone, addressAddress;
    private int id = 0;
    private AddressMoudle addressMoudle = null;

    private RelativeLayout updateBackground = null;
    private RelativeLayout updateConviderLinear, updateCityLinear = null;
    private View updatebottomMain = null;
    private TextView bottomText = null;
    private TextView updateConviderContent, updateCityContent;
    private ArrayList<Object> list = null;
    private ListViewAdapter adapter = null;
    private ListView bottomListView = null;
    private Button bottomButtonCancal, bottomButtonEnter;
    private int tmp = 0;
    private String[] array = null;
    private String[] arrayCity = null;
    private int STATE = 0;
    private final int CONVIIDER = 1;
    private final int CITY = 2;
    private String content = null;

    /**
     * 这个是程序的开始
     */
    @Override
    public void inti() {

        setTitle("地址");
        setTopColor(R.color.ColorBlue);
        setRightTitleVisiable(false);
        setLeftTitleColor(R.color.ColorWhite);
        setTopTitleColor(R.color.ColorWhite);

        view = LayoutInflater.from(context).inflate(R.layout.updataaddress, null);

        addressBut = (Button) view.findViewById(R.id.addressBut);
        addressName = (EditText) view.findViewById(R.id.addressName);
        addressPhone = (EditText) view.findViewById(R.id.addressPhone);
        addressAddress = (EditText) view.findViewById(R.id.addressAddress);
        updateBackground = (RelativeLayout) view.findViewById(R.id.updateBackground);
        updatebottomMain = view.findViewById(R.id.updatebottomMain);
        updateConviderLinear = (RelativeLayout) view.findViewById(R.id.updateConviderLinear);
        updateCityLinear = (RelativeLayout) view.findViewById(R.id.updateCityLinear);
        bottomText = (TextView) view.findViewById(R.id.bottomText);
        bottomListView = (ListView) view.findViewById(R.id.bottomListView);
        bottomButtonCancal = (Button) view.findViewById(R.id.bottomButtonCancal);
        bottomButtonEnter = (Button) view.findViewById(R.id.bottomButtonEnter);
        updateConviderContent = (TextView) view.findViewById(R.id.updateConviderContent);
        updateCityContent = (TextView) view.findViewById(R.id.updateCityContent);

        ConviderCity.setConviderCity("");


        //判断跳转进来的页面是不是创建地址还是修改地址
        addressMoudle = getIntent().getParcelableExtra("data");
        if (addressMoudle.getId() != -1) {
            array = ConviderCity.getConvider();
            for (int i = 0; i < array.length; i++)
                if (array[i].equals(addressMoudle.getConvider())) {
                    tmp = i;
                    break;
                }
            id = addressMoudle.getId();
            addressName.setText(addressMoudle.getName());
            addressPhone.setText(addressMoudle.getPhone());
            addressAddress.setText(addressMoudle.getAddress());
            updateConviderContent.setText(addressMoudle.getConvider());
            updateCityContent.setText(addressMoudle.getCity());
        }

        //点击事件注册
        addressBut.setOnClickListener(this);
        updateConviderLinear.setOnClickListener(this);
        updateCityLinear.setOnClickListener(this);
        updateBackground.setOnClickListener(this);
        bottomButtonEnter.setOnClickListener(this);
        bottomButtonCancal.setOnClickListener(this);
        bottomListView.setOnItemClickListener(this);

        //初始化底部菜单
        DensityUtil.setRelHeightBottom(updatebottomMain, MainActivity.height / 2);
        updatebottomMain.setVisibility(View.GONE);
        updateBackground.setAlpha(0);
        updateBackground.setVisibility(View.GONE);


        setContent(view);
    }

    @Override
    public void Click(View v) {
        switch (v.getId()) {
            case R.id.addressBut:
                AddressDatabase(addressMoudle.getId());
                Applications.getInstance().removeOneActivity(this);
                break;
            case R.id.updateConviderLinear:
                STATE = 1;
                array = ConviderCity.getConvider();
                Bottoninstance(array);
                BottomAnimation();
                bottomText.setText("省份");
                break;
            case R.id.updateCityLinear:
                STATE = 2;
                arrayCity = ConviderCity.getCity(tmp);
                Bottoninstance(arrayCity);
                BottomAnimation();
                bottomText.setText("城市");
                break;
            case R.id.updateBackground:
                BottomAnimation();
                break;
            case R.id.bottomButtonCancal:
                BottomAnimation();
                break;
            case R.id.bottomButtonEnter:
                switch (STATE) {
                    case CONVIIDER:
                        updateConviderContent.setText(content);
                        break;
                    case CITY:
                        updateCityContent.setText(content);
                        break;
                }
                BottomAnimation();
        }
    }

    /**
     * 将地址添加或者修改地址
     */
    private void AddressDatabase(int flag) {
        //检查数据库的表是否存在
        CheckDatabase.AddressCheckDatabase(context);


        AddressMoudle address = new AddressMoudle();
        address.setId(id);
        address.setName(addressName.getText().toString());
        address.setAddress(addressAddress.getText().toString());
        address.setPhone(addressPhone.getText().toString());
        address.setConvider(updateConviderContent.getText().toString());
        address.setCity(updateCityContent.getText().toString());
        GetDatabaseData getDatabaseData = new GetDatabaseData();

        if (flag == -1)
            //将用户的登录信息写到数据库Server表
            getDatabaseData.Insert(context, "BlueBooth", "Address", address.getContentValues());
        else {
            getDatabaseData.Update(context, "BlueBooth", "Address", address.getContentValues(), "ids = ?", new String[]{address.getId() + ""});
        }
    }

    /**
     * 这个是底部的动画
     */
    private void BottomAnimation() {
        if (updatebottomMain.getVisibility() == View.VISIBLE) {
            Animation animationBackgroud = AnimationUtils.loadAnimation(context, R.anim.backgroudin);
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.bottomin);
            updatebottomMain.startAnimation(animation);
            //监听动画是否执行完毕
            animation.setAnimationListener(this);
            updateBackground.startAnimation(animationBackgroud);
        } else if (updatebottomMain.getVisibility() == View.GONE) {
            updatebottomMain.setVisibility(View.VISIBLE);
            updateBackground.setAlpha(0.7f);
            updateBackground.setVisibility(View.VISIBLE);
            Animation animationBackgroud = AnimationUtils.loadAnimation(context, R.anim.backgroudout);
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.bottomout);
            updatebottomMain.startAnimation(animation);
            updateBackground.startAnimation(animationBackgroud);
        }
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

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        updatebottomMain.setVisibility(View.GONE);
        updateBackground.setAlpha(0);
        updateBackground.setVisibility(View.GONE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (STATE) {
            case CONVIIDER:
                tmp = position;
                break;
            case CITY:
                array = arrayCity;
                break;
        }
        content = array[position];
        for (int i = 0; i < list.size(); i++) {
            if (i == position)
                list.set(i, AdapterData.getMap(context, array[i], ImageTransformation.Resouce2Drawable(context, R.mipmap.nice)));
            else
                list.set(i, AdapterData.getMap(context, array[i], null));
        }
        adapter.ChangeData(list);
    }
}
