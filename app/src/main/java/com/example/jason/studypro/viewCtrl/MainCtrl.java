package com.example.jason.studypro.viewCtrl;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.jason.studypro.AndroidUtil;
import com.example.jason.studypro.common.Comparaobj;
import com.example.jason.studypro.common.PersonalInfo;
import com.example.jason.studypro.constant.RoutePath;
import com.example.jason.studypro.databinding.ActivityMainBinding;
import com.example.jason.tool.FileUtil;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/13$ 10:21$
 * <p/>{@link com.example.jason.studypro.MainAct}
 */
public class MainCtrl {
    List<Comparaobj> data;
    private ActivityMainBinding binding;

    public MainCtrl(final ActivityMainBinding binding) {
        Comparaobj   objTina = new Comparaobj("tina", 21);
        PersonalInfo pp      = new PersonalInfo();
        pp.setTime("wewe");
        objTina.setPersonalInfo(pp);
        try {
            Field pAge = objTina.getClass().getDeclaredField("personalInfo");
            pAge.setAccessible(true);
            try {
                PersonalInfo age_get = (PersonalInfo) pAge.get(objTina);
                Log.i("age_get ", age_get.getTime() + "");
                age_get.setTime("1111");
                Log.i("age_get ", objTina.getPersonalInfo().getTime() + "");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        this.binding = binding;
        List<String> mml = new ArrayList<>();
       for (int i = 1 ; i < 15 ; i ++)
       {
           mml.add(i+"");

       }
        binding.weekView.setData(mml, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        data = new ArrayList<>();
        data.clear();
        data.add(new Comparaobj("tom", 23));
        data.add(new Comparaobj("jack", 19));
        data.add(new Comparaobj("jim", 27));
        Collections.sort(data, new Comparator<Comparaobj>() {
            @Override
            public int compare(Comparaobj t0, Comparaobj t1) {
                if (t0.getAge() < t1.getAge()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        for (Comparaobj obj : data) {
            Log.i("Comparaobj", obj.getName() + " " + obj.getAge());
        }
        binding.lyricList.post(new Runnable() {
            @Override
            public void run() {
                binding.lyricList.setText("Runnable");
            }
        });
        binding.seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.imageProcess.setSeek_bar_1(binding.seekBar1.getProgress());
                binding.imageProcess.setSeek_bar_2(binding.seekBar2.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        binding.seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.imageProcess.setSeek_bar_1(binding.seekBar1.getProgress());
                binding.imageProcess.setSeek_bar_2(binding.seekBar2.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void onClickRecycle(View view) {
        Log.i(this.getClass().getName(), "onClickRecycle");
        ARouter.getInstance().build(RoutePath.RECYCLE_VIEW_SHOW).navigation();
    }

    public static String SAVED_IMAGE_DIR_PATH = Environment.getExternalStorageDirectory().getPath();

    public void onClickObjectAnimation(View view) {
        ObjectAnimator translationUp = ObjectAnimator.ofInt(view,
                "backgroundColor", Color.RED, Color.BLUE, Color.GRAY,
                Color.GREEN);
        translationUp.start();
        File   sdDir      = Environment.getExternalStorageDirectory();
        String cameraPath = "/sstudy/" + System.currentTimeMillis() + ".png";
        Log.i(cameraPath, cameraPath);
        Uri    uri    = Uri.fromFile(new File(cameraPath));
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        Activity activity = (Activity) binding.getRoot().getRootView().getContext();
        activity.startActivityForResult(intent, 2017);
    }

    public void onClickPsw(View view) {
        ARouter.getInstance().build(RoutePath.PSWTEXT_VIEW_SHOW).navigation();
    }
    public void onClickPdv(View view) {
        ARouter.getInstance().build(RoutePath.PSWTEXT_VIEW_SHOW).navigation();
    }

    public void onClickGlide() {
        ARouter.getInstance().build(RoutePath.Glide_Lode_SHOW).navigation();
    }

    public void onClickBind() {
        ARouter.getInstance().build(RoutePath.Bind_Adapter_SHOW).navigation();
    }

    public void onClickViewPage() {
        ARouter.getInstance().build(RoutePath.PAGE_VIEW_SHOW).navigation();
    }

    public void onClickRXjava2() {
        ARouter.getInstance().build(RoutePath.RX_JAVA_SHOW).navigation();
    }

    public void onCoordinatorLayout(View view){
        ARouter.getInstance().build(RoutePath.COOLDINATOR_LAYOUT).navigation();
    }

    public void onClickUM(View view){
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(AndroidUtil.getActivity(view),mPermissionList,123);
        }

        new ShareAction(AndroidUtil.getActivity(view))
                .withText("hello")
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.i("onStart",new Gson().toJson(share_media));
                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Log.i("onResult",new Gson().toJson(share_media));

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Log.i("onError",new Gson().toJson(share_media));
                        throwable.printStackTrace();

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        Log.i("onCancel",new Gson().toJson(share_media));

                    }
                })
                .open();
    }

    public void onClickLyric(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(binding.lyricList, "progress", 0f, 1f);
        animator.setDuration(3000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }

    private void blueToothTesta() {
        BluetoothManager bluetoothManager = (BluetoothManager) binding.getRoot().getContext().getSystemService(Context.BLUETOOTH_SERVICE);
        //        BluetoothAdapter btAdapter = bluetoothManager.getAdapter();
    }

    public void onClickToAutoSize(View view) {
        FileUtil.printEnvironmentDir();
        FileUtil.printContextDir((Activity) binding.getRoot().getContext());
        File createFile = new File(Environment.getExternalStorageDirectory(), "xx");
        File file       = FileUtil.getAlbumStorageDir(createFile);
        Log.i("FileUtil", FileUtil.getRootDir());
        // ARouter.getInstance().build(RoutePath.NOTE_EDIT_SHOW).navigation();
    }

    public void onClickSave(View view) {
        File    dir         = null;
        String  dirPath     = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

        if (sdCardExist) {
            dir = Environment.getExternalStorageDirectory();
            dirPath = dir.toString();
        } else {
            dirPath = "";
        }

        dirPath = dirPath + "/temp_photo";
    }
}
