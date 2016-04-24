package com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint;

import android.graphics.Bitmap;

/**
 * Created by 19820 on 2016/4/24.
 */
public class FileRead {


    public static void ReadFile(String filename , String path){
        new ReadFile().ReadFile(filename , path);
    }

    public static Bitmap ReadBitmap(String filename , String path){
        return new ReadFile().ReadBitmap(filename , path);
    }
}
