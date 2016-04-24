package com.example.ruan.blueboothprint.View;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.boothprint.R;


/**
 * Created by Administrator on 2016/1/5.
 */
public class MyDialog extends Dialog implements View.OnClickListener{


    private TextView dialongTitle = null;
    private TextView dialogContent = null;
    private TextView dialogcancal = null;
    private TextView dialogenter = null;
    private LinearLayout dialoglinear = null;
    private Context context = null;
    private ImageView dialogIcon = null;

    private DialogClick dialogClick = null;

    private View view = null;

    public MyDialog(Context context) {
        this(context, R.style.mydialog);
        this.context = context;
        init();
    }

    public MyDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        init();
    }

    protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
        init();
    }

    private View getView() {
        view = LayoutInflater.from(context).inflate(R.layout.mydialog, null);

        //获取对话框组件的ID
        ID();

        return view;
    }

    private void ID(){
        dialoglinear = (LinearLayout) view.findViewById(R.id.dialoglinear);
        dialongTitle = (TextView) view.findViewById(R.id.dialongTitle);
        dialogContent = (TextView) view.findViewById(R.id.dialogContent);
        dialogcancal = (TextView) view.findViewById(R.id.dialogcancal);
        dialogenter = (TextView) view.findViewById(R.id.dialogenter);
        dialogIcon = (ImageView) view.findViewById(R.id.dialogIcon);

    }

    private void init() {
        this.setContentView(getView());

        dialogcancal.setOnClickListener(this);
        dialogenter.setOnClickListener(this);
    }

    //设置对话框的高度
    public void setHeight(int height) {
        ViewGroup.LayoutParams params = dialoglinear.getLayoutParams();
        params.height = height;
    }

    //设置对话框的宽度
    public void setWidth(int width) {
        ViewGroup.LayoutParams params = dialoglinear.getLayoutParams();
        params.width = width;
    }

    //设置对话框的内容
    public void setMessage(String msg) {
        dialogContent.setText(msg);
    }

    /**
     * 设置对话框的标题图片
     * @param drawable
     */
    public void setTopImage(Drawable drawable){
        dialogIcon.setImageDrawable(drawable);
    }


    //设置左边的按钮的文字
    public void setLeft_but(String msg) {
        dialogcancal.setText(msg);
    }

    //设置右边的按钮的文字
    public void setRight_but(String msg) {
        dialogenter.setText(msg);
    }

    //获取对话框的标题的文字
    public void setTitle(String msg) {
        dialongTitle.setText(msg);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        dialogClick.onDialogClick(v);
    }

    /**
     * 按钮点击事件的操作方法
     * @param dialogClick
     */
    public void onDialogClick(DialogClick dialogClick){
        this.dialogClick = dialogClick;
    }
}
