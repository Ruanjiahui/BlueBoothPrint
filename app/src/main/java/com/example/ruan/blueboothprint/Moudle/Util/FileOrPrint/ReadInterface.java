package com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint;

import android.graphics.Bitmap;

/**
 * Created by 19820 on 2016/4/24.
 */
public interface ReadInterface {


    public void ReadFile(String filename, String path);

    public Bitmap ReadBitmap(String filename, String path);
}
