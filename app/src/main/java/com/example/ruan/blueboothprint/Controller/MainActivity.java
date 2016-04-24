package com.example.ruan.blueboothprint.Controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.administrator.boothprint.R;
import com.example.ruan.blueboothprint.Moudle.Adapter.ListViewAdapter;
import com.example.ruan.blueboothprint.Moudle.Adapter.ViewPagerAdapter;
import com.example.ruan.blueboothprint.Moudle.MainHandler;
import com.example.ruan.blueboothprint.Moudle.Util.AdapterData;
import com.example.ruan.blueboothprint.Moudle.Util.CommonIntent;
import com.example.ruan.blueboothprint.Moudle.Util.Image.ImageTransformation;
import com.example.ruan.blueboothprint.Moudle.Util.System.DensityUtil;
import com.example.ruan.blueboothprint.View.FixedSpeedScroller;
import com.example.ruan.blueboothprint.boothprint.SystemManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends FragmentActivity implements View.OnClickListener, OnItemClickListener {

    private DrawerLayout mainDrawerlayout = null;
    private GridView mainGridView = null;
    private ViewPager mainViewpager = null;
    private ImageView contentTitleimage = null;
    private LinearLayout mainDrawerLinear = null;
    private ImageView drawerImage = null;
    private TextView drawerText = null;
    private ListView drawerListView = null;
    private LinearLayout drawerLoad = null;
    private LinearLayout activityContentTitle = null;
    private MainHandler mainHandler = null;

    private Context context = null;

    public static int width, height;
    private Map<String, String> map = null;
    private int LoadState = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        context = this;

        //判断用户是否登录过
        mainHandler = new MainHandler(context);
        map = mainHandler.isUserLoad();

        getPhone();

        //获取组件的ID
        id();

        //实例化各个组件包括配置listview的适配器
        //配置轮播组件的适配器
        //轮播组件的属性
        instance();

        //按钮的点击事件
        Click();

        //设置沉寂式状态栏
        SystemManager.setWindowColor(activityContentTitle, this);

    }

    private void id() {
        mainDrawerlayout = (DrawerLayout) findViewById(R.id.mainDrawerlayout);
        mainGridView = (GridView) findViewById(R.id.mainGridView);
        mainViewpager = (ViewPager) findViewById(R.id.mainViewpager);
        contentTitleimage = (ImageView) findViewById(R.id.contentTitleimage);
        mainDrawerLinear = (LinearLayout) findViewById(R.id.mainDrawerLinear);
        drawerText = (TextView) findViewById(R.id.drawerText);
        drawerImage = (ImageView) findViewById(R.id.drawerImage);
        drawerListView = (ListView) findViewById(R.id.drawerListView);
        drawerLoad = (LinearLayout) findViewById(R.id.drawerLoad);
        activityContentTitle = (LinearLayout) findViewById(R.id.activityContentTitle);
    }

    private void Click() {
        contentTitleimage.setOnClickListener(this);
        mainGridView.setOnItemClickListener(this);
        drawerLoad.setOnClickListener(this);
    }

    private void instance() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(AdapterData.getMap("收件开单", ImageTransformation.Resouce2Drawable(context, R.mipmap.congisnee), DensityUtil.dip2px(context, 120)));
        list.add(AdapterData.getMap("装车", ImageTransformation.Resouce2Drawable(context, R.mipmap.car), DensityUtil.dip2px(context, 120)));
        list.add(AdapterData.getMap("到车卸货", ImageTransformation.Resouce2Drawable(context, R.mipmap.move), DensityUtil.dip2px(context, 120)));
        list.add(AdapterData.getMap("到达入库", ImageTransformation.Resouce2Drawable(context, R.mipmap.conpany), DensityUtil.dip2px(context, 120)));
        list.add(AdapterData.getMap("运单估价", ImageTransformation.Resouce2Drawable(context, R.mipmap.peice), DensityUtil.dip2px(context, 120)));
        list.add(AdapterData.getMap("网点今日", ImageTransformation.Resouce2Drawable(context, R.mipmap.net), DensityUtil.dip2px(context, 120)));
        list.add(AdapterData.getMap("发货签单", ImageTransformation.Resouce2Drawable(context, R.mipmap.send), DensityUtil.dip2px(context, 120)));
        list.add(AdapterData.getMap("接货签单", ImageTransformation.Resouce2Drawable(context, R.mipmap.take), DensityUtil.dip2px(context, 120)));
        list.add(AdapterData.getMap("扫码单据", ImageTransformation.Resouce2Drawable(context, R.mipmap.scan), DensityUtil.dip2px(context, 120)));

        mainGridView.setAdapter(new ListViewAdapter(context, list, "GridView"));
        ArrayList<Object> drawerList = new ArrayList<>();
        drawerList.add(AdapterData.getMap(DensityUtil.dip2px(context, 50), "设置", ImageTransformation.Resouce2Drawable(context, R.mipmap.ic_launcher)));
        drawerList.add(AdapterData.getMap(DensityUtil.dip2px(context, 50), "设置", ImageTransformation.Resouce2Drawable(context, R.mipmap.ic_launcher)));
        drawerList.add(AdapterData.getMap(DensityUtil.dip2px(context, 50), "关于", ImageTransformation.Resouce2Drawable(context, R.mipmap.ic_launcher)));
        drawerList.add(AdapterData.getMap(DensityUtil.dip2px(context, 50), "退出", ImageTransformation.Resouce2Drawable(context, R.mipmap.ic_launcher)));

        drawerListView.setAdapter(new ListViewAdapter(context, drawerList, "ListView"));
        //配置完listview之后将数据清空
        //之后配置viewpager
        ArrayList<ImageView> viewpagerList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(context);
            switch (i) {
                case 0:
                    imageView.setBackground(ImageTransformation.Resouce2Drawable(context, R.mipmap.a001));
                    break;
                case 1:
                    imageView.setBackground(ImageTransformation.Resouce2Drawable(context, R.mipmap.a002));
                    break;
                case 2:
                    imageView.setBackground(ImageTransformation.Resouce2Drawable(context, R.mipmap.a003));
                    break;
            }

            viewpagerList.add(imageView);
        }

        mainViewpager.setAdapter(new ViewPagerAdapter(viewpagerList));
        mainViewpager.setCurrentItem(0);
        DensityUtil.setHeight(mainViewpager, height / 4);
        DensityUtil.setLinearSize(mainDrawerLinear, width / 2, height);
        //设置Viewpager滑动的速度
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(mainViewpager.getContext(),
                    new AccelerateInterpolator());
            field.set(mainViewpager, scroller);
            scroller.setmDuration(500);
        } catch (Exception e) {
        }


        //更新主界面
        if (map != null) {
            HandlerMainUI(1);
            LoadState = 1;
        }
    }


    /**
     * 获取手机屏幕大小
     */
    private void getPhone() {
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contentTitleimage:
                mainDrawerlayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.drawerLoad:
                if (LoadState == 0)
                    CommonIntent.IntentActivity(context, Login.class);
                else {
                    mainHandler.ExistLoad(map.get("userid"));
                    HandlerMainUI(0);
                    LoadState = 0;
                }
                break;
        }
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
        if (!mainDrawerlayout.isDrawerOpen(Gravity.LEFT)) {
            switch (parent.getId()) {
                case R.id.mainGridView:
                    switch (position) {
                        case 0:
                            CommonIntent.IntentActivity(context, Consignee.class);
                            break;
                        case 6:
                            CommonIntent.IntentActivity(context, Receiving.class , "Take");
                            break;
                        case 7:
                            CommonIntent.IntentActivity(context, Receiving.class , "Send");
                            break;
                        case 8:
                            CommonIntent.IntentActivity(context, Qcode.class);
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mainHandler.isLoad()) {
            HandlerMainUI(1);
            map = mainHandler.isUserLoad();
            LoadState = 1;
        } else
            HandlerMainUI(0);

    }

    private void HandlerMainUI(int State) {
        if (State == 0)
            drawerText.setText("....");
        else
            drawerText.setText(map.get("nickname"));
    }
}
