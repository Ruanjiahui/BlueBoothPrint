package com.example.ruan.blueboothprint.Moudle.Bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;

import com.example.administrator.boothprint.R;
import com.example.ruan.blueboothprint.Controller.Consignee;
import com.example.ruan.blueboothprint.Controller.DrawName;
import com.example.ruan.blueboothprint.Moudle.Util.Image.ImageTransformation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * Created by Administrator on 2016/4/18.
 */
public class BluetoothServer {

    private BluetoothDevice device = null;
    private Context context = null;

    public BluetoothServer(BluetoothDevice device, Context context) {
        this.device = device;
        this.context = context;
        inti();
    }

    /**
     * 初始化
     */
    public void inti() {
//        try {


//            BluetoothSocket mmSocket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001126-0000-1000-8000-00805f9b34fb"));

//            if (mmSocket != null) {
//                mmSocket.connect();

//                OutputStream outputStream = mmSocket.getOutputStream();


                //将数据传输给打印机
                BluetoothPrint print = new BluetoothPrint(null);



                print.PrintBitmap(Consignee.bitmap, 500 , 1500);
//                print.PrintWrite();

//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
