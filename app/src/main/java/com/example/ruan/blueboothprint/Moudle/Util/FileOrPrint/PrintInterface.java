package com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint;

import java.io.IOException;

/**
 * Created by Administrator on 2016/4/13.
 */
public interface PrintInterface {

    public void Save(String filename, String path, String SaveContent) throws IOException;

    public void Save(String filename, String path, byte[] SaveContent);

    public interface Print {

        public void Print();
    }
}
