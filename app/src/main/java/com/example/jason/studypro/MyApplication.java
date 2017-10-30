package com.example.jason.studypro;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.jason.studypro.common.Config;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/13$ 10:54$
 * <p/>
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        basicInit();
    }

    private void basicInit() {
        Config.isDebug.setmValue(true);
        if (Config.isDebug.getmValue()) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }
}
