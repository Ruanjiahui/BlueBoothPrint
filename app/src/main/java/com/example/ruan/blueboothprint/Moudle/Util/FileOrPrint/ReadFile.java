package com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint;

import android.graphics.Bitmap;

/**
 * Created by 19820 on 2016/4/24.
 */
public class ReadFile implements ReadInterface {

    @Override
    public void ReadFile(String filename, String path) {
    }

    @Override
    public Bitmap ReadBitmap(String filename, String path) {
        return new FileTool().readPictureByteBitmap(filename, path);
    }
}
