package com.example.jason.studypro.act;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.jason.studypro.R;
import com.example.jason.studypro.constant.RoutePath;
import com.example.jason.studypro.databinding.RxJavaActBinding;
import com.example.jason.studypro.viewCtrl.RxJavaTextCtrl;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/11$ 10:08$
 * <p/>
 */
@Route(path = RoutePath.RX_JAVA_SHOW)
public class RxJavaTextAct extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxJavaActBinding binding = (RxJavaActBinding) DataBindingUtil.setContentView(this, R.layout.rx_java_act);
        binding.setViewCtrl(new RxJavaTextCtrl());
    }
}
