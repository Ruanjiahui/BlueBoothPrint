package com.example.ruan.blueboothprint.Moudle.Util.FileOrPrint;

import java.io.IOException;

/**
 * Created by Administrator on 2016/4/13.
 */
public class FilePrint {

    /**
     * 保存要打印的文件
     */
    public void FileSaveTxt(String filename, String path, String SaveContent) {
        try {
            new SaveFileTxt().Save(filename, path, SaveContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void FileSaveTxt(String filename, String path, byte[] saveContent) {
        new SaveFileTxt().Save(filename, path, saveContent);
    }


    /**
     * 保存文件并且打印文件
     */
    public void FileSaveandPrint(String filename, String path, String SaveContent) {
        SaveFileTxt saveFileTxt = new SaveFileTxt();
        //首先保存文件
        try {
            saveFileTxt.Save(filename, path, SaveContent);
            //打印文件
            saveFileTxt.Print();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
