package com.example.jason.studypro.act;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.jason.studypro.R;
import com.example.jason.studypro.constant.RoutePath;
import com.example.jason.studypro.databinding.GlideLoadActBinding;
import com.example.jason.studypro.viewCtrl.GlideLoadCtrl;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/9$ 9:09$
 * <p/>
 */
@Route(path = RoutePath.Glide_Lode_SHOW)
public class GlideLoadAct extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlideLoadActBinding binding = DataBindingUtil.setContentView(this, R.layout.glide_load_act);
        binding.setViewCtrl(new GlideLoadCtrl());
    }
}
