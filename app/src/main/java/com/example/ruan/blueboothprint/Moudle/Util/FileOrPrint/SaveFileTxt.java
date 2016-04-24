package com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint;

import java.io.IOException;

/**
 * Created by Administrator on 2016/4/13.
 */
public class SaveFileTxt implements PrintInterface , PrintInterface.Print{

    @Override
    public void Save(String filename , String path , String SaveContent) throws IOException {
        FileTool.saveFile(filename , path , SaveContent);
    }

    @Override
    public void Save(String filename, String path, byte[] SaveContent) {
        FileTool.saveFile(filename , path , SaveContent);
    }

    @Override
    public void Print() {

    }
}
