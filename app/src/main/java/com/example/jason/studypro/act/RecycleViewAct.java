package com.example.jason.studypro.act;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.jason.studypro.R;
import com.example.jason.studypro.constant.RoutePath;
import com.example.jason.studypro.databinding.RecycleViewShowBinding;
import com.example.jason.studypro.viewCtrl.RecycleViewCtrl;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/13$ 10:43$
 * <p/>
 */
@Route(path = RoutePath.RECYCLE_VIEW_SHOW)
public class RecycleViewAct extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecycleViewShowBinding binding = DataBindingUtil.setContentView(this, R.layout.recycle_view_show);
        binding.setViewCtrl(new RecycleViewCtrl(binding));
    }
}
