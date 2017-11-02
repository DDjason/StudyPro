package com.example.jason.studypro.act;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.jason.studypro.R;
import com.example.jason.studypro.constant.RoutePath;
import com.example.jason.studypro.databinding.CoordinatorLayoutActBinding;
import com.example.jason.studypro.viewCtrl.CoordinatorLayoutCtrl;

/**
 * @author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/11/2$ 9:34$
 * <p/>
 */
@Route(path = RoutePath.COOLDINATOR_LAYOUT)
public class CoordinatorLayoutAct extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CoordinatorLayoutActBinding binding = DataBindingUtil.setContentView(this,R.layout.coordinator_layout_act);
        binding.setViewCtrl(new CoordinatorLayoutCtrl(binding));
    }
}
