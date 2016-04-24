package com.example.ruan.blueboothprint.Controller;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.administrator.boothprint.R;
import com.example.ruan.blueboothprint.Moudle.Moudle.Server;
import com.example.ruan.blueboothprint.Moudle.Net.ResgisterModel;
import com.example.ruan.blueboothprint.Moudle.Net.TimerHandler;
import com.example.ruan.blueboothprint.boothprint.BaseActivity;


/**
 * Created by Administrator on 2016/1/20.
 */
public class Register extends BaseActivity implements TextWatcher, TimerHandler {

    private View view = null;
    private Context context = null;
    public static Activity activity = null;

    private EditText register_edit = null;
    private EditText register_editPassword1 = null;
    private EditText register_editPassword2 = null;
    private Button register_but = null;
    private LinearLayout register_linear = null;
    private LinearLayout register_passwordLinear = null;
    private int time = 60;
    private String phone = "";

    private String registerText = "";
    private String PregisterText = "";
    private String PregisterTextN = "";

    private ResgisterModel resgisterModel = null;

    //这个标识判断这次是注册还是输入密码
    private boolean register = false;

    @Override
    public void inti() {
        setTitle("注册");
        setTopColor(R.color.ColorBlue);
        setRightTitleColor(R.color.ColorWhite);
        setLeftTitleColor(R.color.ColorWhite);
        setTopTitleColor(R.color.ColorWhite);
        setRightTitle("下一步");
        //刚开始默认隐藏
        setRightTitleVisiable(false);

        context = this;
        activity = (Activity) context;

        view = LayoutInflater.from(context).inflate(R.layout.register, null);
        register_edit = (EditText) view.findViewById(R.id.register_edit);
        register_but = (Button) view.findViewById(R.id.register_but);
        register_editPassword1 = (EditText) view.findViewById(R.id.register_editPassword1);
        register_editPassword2 = (EditText) view.findViewById(R.id.register_editPassword2);
        register_linear = (LinearLayout) view.findViewById(R.id.register_linear);
        register_passwordLinear = (LinearLayout) view.findViewById(R.id.register_passwordLinear);

        setContent(view);

        register_edit.addTextChangedListener(this);
        register_but.setOnClickListener(this);

        resgisterModel = new ResgisterModel(this);
    }

    @Override
    public void setRightTextClick() {
        super.setRightTextClick();
        resgisterModel.closeTimer();

        //如果为默认状态则是刚刚进行的是验证的点击
        if (!register) {
            //将状态改变成输入密码状态
            register = true;
            registerText = register_edit.getText().toString();
            if (!resgisterModel.SpaceText(registerText, "验证码不能为空")) {
                //设置右边文字
                setRightTitle("注册");
                setTitle("输入密码");
                //跳转到输入密码的界面
                register_passwordLinear.setVisibility(View.VISIBLE);
                register_linear.setVisibility(View.GONE);
                return;
            }
        }

        //获取新密码和旧密码
        PregisterText = register_editPassword1.getText().toString();
        PregisterTextN = register_editPassword2.getText().toString();

        //输入两次密码不能为空
        if (!resgisterModel.SpaceText(PregisterText, "密码不能为空")) {
            if (!resgisterModel.SpaceText(PregisterTextN, "密码不能为空")) {
                //判断两次输入的密码是否一样
                if (resgisterModel.PasswordCheck(PregisterText, PregisterTextN, "两次密码输入的不一样,请重新输入")) {
                    //两次密码一样则像服务器发出请求注册用户

                    Server server = new Server();
                    server.setOnline(1);
                    server.setLaststate(1);
                    server.setUserid(phone);
                    server.setPassword(PregisterText);
                    //首先向服务器发出请求注册用户
                    //最后注册成功则将信息写进本地数据库
                    resgisterModel.ResgisterNet(server);

                } else {
                    //清空输入框的内容
                    register_editPassword1.setText("");
                    register_editPassword1.setHint("请输入新密码");
                    register_editPassword2.setText("");
                    register_editPassword2.setHint("请再一次输入新密码");
                }
            }
        }


    }

    @Override
    public void Click(View view) {
        switch (view.getId()) {
            case R.id.register_but:
                //当点击获取验证码之后右边的下一步按钮将显示
                setRightTitleVisiable(true);
                //获取手机号
                phone = register_edit.getText().toString();
                //将输入手机的编辑框清空
                register_edit.setText("");
                //设置编辑框的内容
                register_edit.setHint("请输入验证码");
                //设置验证码最大可以输入6个
                register_edit.setMaxEms(6);
                //设置获取验证码的按钮不能点击
                register_but.setEnabled(false);
                register_but.setClickable(false);
                //设置按钮不可以点击的背景图
                register_but.setBackgroundResource(R.drawable.button_down_blue);
                //设置验证码验证时间间隔为一秒
                resgisterModel.Code(this, 1000);
                //将标识设置刚开始默认状态
                register = false;
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
        if (register_edit.getText().toString().length() == 11) {
            register_but.setEnabled(true);
            register_but.setClickable(true);
            register_but.setBackgroundResource(R.drawable.button_selector_blue);
        } else if (register_edit.getText().toString().length() < 11) {
            register_but.setEnabled(false);
            register_but.setClickable(false);
            register_but.setBackgroundResource(R.drawable.button_down_blue);
        }
    }


    //处理时间调度的接口方法
    @Override
    public void timerHandler(Message msg) {
        register_but.setText(msg.what + "秒后重新获取验证码");
        if (msg.what == 0) {
            register_but.setText("获取验证码");
            register_but.setEnabled(true);
            register_but.setClickable(true);
            time = 60;
            resgisterModel.closeTimer();
            register_but.setBackgroundResource(R.drawable.button_selector_blue);
        }
    }

    //处理时间进行实际的接口方法
    //每次线程执行一次就是时间减一达到倒计时的操作
    @Override
    public Message timerRun() {
        Message msg = new Message();
        time--;
        msg.what = time;
        return msg;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            SystemTool.Closekeyboard(activity);
        }
        return super.onTouchEvent(event);
    }
}
