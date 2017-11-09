package com.example.jason.tool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.security.Provider;

/**
 * @author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/11/8$ 15:03$
 * <p/>
 * Description:
 */
public class PhotoLogicalUtil {
    //拍照
    public static final int LOGICAL_TAKE_PHOTO   = 0x801;
    //相册
    public static final int LOGICAL_CHOOSE_PHOTO = 0x802;
    //文件位置Provider
    public static final String FILE_PROVIDER_LOCATION = "";

    /**
     * 拍照功能
     *
     * @param activity
     * @param uri
     */
    public static void takePhoto(Activity activity, @Nullable File file) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = null;
        if (null != file) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                //添加 URI 允许
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                uri = FileProvider.getUriForFile(activity,)
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        activity.startActivityForResult(intent, LOGICAL_TAKE_PHOTO);
    }

    public static void choosePhoto(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent, LOGICAL_CHOOSE_PHOTO);
    }

    /**
     * onActivityResult
     *
     * @param activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public static void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PhotoLogicalUtil.LOGICAL_TAKE_PHOTO:
                    //成功拍摄照片
                    if (data != null) {
                        //可能尚未指定EXTRA_OUTPUT
                        if (data.hasExtra("data")) {
                            Bitmap thumbnail = data.getParcelableExtra("data");
                            Log.i("可能尚未指定EXTRA_OUTPUT", "hive");
                        } else {
                            Log.i("可能尚未指定EXTRA_OUTPUT", "n");
                        }
                    } else {
                        //由于指定了目标uri，存储在目标
                        Log.i("可能尚未指定EXTRA_OUTPUT", "null");
                    }
                    Log.i("ww", "成功拍摄照片");
                    break;
                case PhotoLogicalUtil.LOGICAL_CHOOSE_PHOTO:
                    //成功选取照片
                    Log.i("onActivityResult", data.getData() + "");
                    break;
                default:
                    break;
            }
        }
    }
}
