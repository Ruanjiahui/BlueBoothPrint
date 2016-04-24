package com.example.ruan.blueboothprint.Controller;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.boothprint.R;
import com.example.ruan.blueboothprint.Moudle.Moudle.Server;
import com.example.ruan.blueboothprint.Moudle.Net.LoadModel;
import com.example.ruan.blueboothprint.Moudle.Util.CommonIntent;
import com.example.ruan.blueboothprint.Moudle.Util.System.DensityUtil;
import com.example.ruan.blueboothprint.Moudle.Util.System.SystemOperation;
import com.example.ruan.blueboothprint.boothprint.BaseActivity;

/**
 * Created by Administrator on 2016/3/1.
 */
public class Login extends BaseActivity implements View.OnFocusChangeListener, TextWatcher {

    private Context context = null;
    public static Activity activity = null;
    private View view = null;

    private EditText load_edit1, load_edit2 = null;
    private ImageView load_image, load_image1 = null;
    //注册的按钮
    private Button load_but1 = null;
    //登录的按钮
    private Button load_but = null;

    private String id , password = null;

    @Override
    public void inti() {
        context = this;
        activity = (Activity) context;

        setTitle("登录");
        setTopColor(R.color.ColorBlue);
        setRightTitleVisiable(false);
        setTopTitleColor(R.color.ColorWhite);
        setLeftTitleColor(R.color.ColorWhite);
        setContentColor(R.color.ColorWhiteSmoke);

        view = LayoutInflater.from(context).inflate(R.layout.login, null);

        load_edit1 = (EditText) view.findViewById(R.id.load_edit1);
        load_edit2 = (EditText) view.findViewById(R.id.load_edit2);
        load_image = (ImageView) view.findViewById(R.id.load_image);
        load_image1 = (ImageView) view.findViewById(R.id.load_image1);
        load_but = (Button) view.findViewById(R.id.load_but);
        load_but1 = (Button) view.findViewById(R.id.load_but1);

        DensityUtil.setWidth(view, width);
        setContent(view);

        load_edit1.setOnFocusChangeListener(this);
        load_edit2.setOnFocusChangeListener(this);
        load_edit1.addTextChangedListener(this);
        load_edit2.addTextChangedListener(this);
        load_but1.setOnClickListener(this);
        load_but.setOnClickListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.load_edit1:
                if (hasFocus) {
                    load_image.setImageResource(R.mipmap.head);
                    load_image1.setImageResource(R.mipmap.password);
                } else {
                    load_image.setImageResource(R.mipmap.head_up);
                    load_image1.setImageResource(R.mipmap.password_up);
                }
                break;
            case R.id.load_edit2:
                if (hasFocus) {
                    load_image.setImageResource(R.mipmap.head_up);
                    load_image1.setImageResource(R.mipmap.password_up);
                } else {
                    load_image.setImageResource(R.mipmap.head);
                    load_image1.setImageResource(R.mipmap.password);
                }
                break;
        }
    }


    @Override
    public void Click(View v) {
        switch (v.getId()) {
            case R.id.load_but:
                id = load_edit1.getText().toString();
                password = load_edit2.getText().toString();

                Server server = new Server();
                server.setOnline(1);
                server.setUserid(id);
                server.setLaststate(1);
                server.setPassword(password);

                LoadModel loadModel = new LoadModel(context);
                //将server封装成ContentValues
                loadModel.UpdateServer(server);
                //实现登录的方法
                loadModel.LinkLoad(server);
                break;
            case R.id.load_but1:
                CommonIntent.IntentActivity(context, Register.class);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (load_edit1.getText().toString().length() > 0 && load_edit2.getText().toString().length() > 0) {
            load_but.setClickable(true);
            load_but.setEnabled(true);
            load_but.setBackgroundResource(R.drawable.button_selector_red);
        } else {
            load_but.setClickable(false);
            load_but.setEnabled(false);
            load_but.setBackgroundResource(R.drawable.button_down_red);
        }
    }

    /**
     * 整个屏幕的触摸事件
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //关闭软键盘
            SystemOperation.Closekeyboard(activity);
        }
        return super.onTouchEvent(event);
    }
}
