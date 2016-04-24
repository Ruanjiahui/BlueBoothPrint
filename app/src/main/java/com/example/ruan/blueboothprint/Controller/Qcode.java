package com.example.ruan.blueboothprint.Controller;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.boothprint.R;
import com.example.ruan.blueboothprint.Moudle.Adapter.ListViewAdapter;
import com.example.ruan.blueboothprint.boothprint.BaseActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/14.
 */
public class Qcode extends BaseActivity {

    private View view = null;

    private ListView qcodeListView = null;
    private TextView QcodeText = null;

    public static ArrayList<Object> list = null;
    private ListViewAdapter adapter = null;

    /**
     * 这个是程序的开始
     */
    @Override
    public void inti() {
        setTitle("扫描单号");
        setTopTitleColor(R.color.ColorWhite);
        setTopColor(R.color.ColorBlue);
        setLeftTitleColor(R.color.ColorWhite);
        setRightTitleVisiable(false);

        list = new ArrayList<>();

        view = LayoutInflater.from(context).inflate(R.layout.qcode, null);

        qcodeListView = (ListView) view.findViewById(R.id.qcodeListView);
        QcodeText = (TextView) view.findViewById(R.id.QcodeText);

        intance();

        QcodeText.setOnClickListener(this);

        setContent(view);
    }

    private void intance() {
        adapter = new ListViewAdapter(context, list, "ListView");
        qcodeListView.setAdapter(adapter);
    }

    @Override
    public void Click(View v) {
        switch (v.getId()) {
            case R.id.QcodeText:
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
                break;
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        //处理扫描结果（在界面上显示）
//        if (resultCode == RESULT_OK) {
//            Bundle bundle = data.getExtras();
//            String scanResult = bundle.getString("result");
//
//            list.add(AdapterData.getMap(scanResult , DensityUtil.dip2px(context , 50)));
//            adapter.ChangeData(list);
//        }
//    }


    @Override
    protected void onRestart() {
        super.onRestart();

        adapter.ChangeData(list);
    }
}
