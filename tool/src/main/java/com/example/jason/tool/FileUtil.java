package com.example.jason.tool;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/20$ 14:07$
 * <p/>
 */
public class FileUtil {

    public static String DATE_DIR = null;


    private FileUtil(){
    }


    public static String getRootDir(){
        if (TextUtils.isEmpty(DATE_DIR)){
            File sdDir = null;
            boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
            if (sdCardExist){
                sdDir = Environment.getExternalStorageDirectory();
                DATE_DIR = sdDir.toString();
            }
            if (null == sdDir){
                DATE_DIR = "";
            }
        }
        return DATE_DIR;
    }


    public static void printEnvironmentDir(){
        Log.i("DATE_DIR()",getRootDir().toString());
        Log.i("DownloadCacheDirectory",Environment.getDownloadCacheDirectory().toString());
        Log.i("ExternalStorageDirec",Environment.getExternalStorageDirectory().toString());
        Log.i("getExternalStor",Environment.getExternalStoragePublicDirectory("").toString());

    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e+"");
        }
    }

    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    public static void printContextDir(Activity context){
        Log.i("getExternalCacheDir()",context.getExternalCacheDir().toString());
        Log.i("getFilesDir",context.getFilesDir().toString());
        Log.i("getCacheDir()",context.getCacheDir().toString());
    }

    public static File getAlbumStorageDir(File file) {
        File dir  = file.getParentFile();
        if (!dir.exists()) {
            Log.e("LOG_TAG", "Directory not created");
            dir.mkdirs();
        }

        try{
            file.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
        }
        return file;
    }


    /**
     * 以 {@link FileChannel }的方式复制文件
     * @param s
     * @param t
     */
    public static void fileChannelCopy(File s, File t) {
        FileInputStream  fi  = null;
        FileOutputStream fo  = null;
        FileChannel      in  = null;
        FileChannel      out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
