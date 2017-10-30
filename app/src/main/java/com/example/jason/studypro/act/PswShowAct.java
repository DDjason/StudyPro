package com.example.jason.studypro.act;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.jason.studypro.R;
import com.example.jason.studypro.constant.RoutePath;
import com.example.jason.studypro.databinding.PswShowActBinding;
import com.example.jason.studypro.viewCtrl.PswTextCtrl;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/18$ 14:31$
 * <p/>
 */
@Route(path = RoutePath.PSWTEXT_VIEW_SHOW)
public class PswShowAct extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PswShowActBinding binding = DataBindingUtil.setContentView(this, R.layout.psw_show_act);
        binding.setViewCtrl(new PswTextCtrl(binding));
    }
}
