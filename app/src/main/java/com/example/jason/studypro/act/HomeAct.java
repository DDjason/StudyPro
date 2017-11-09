package com.example.jason.studypro.act;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.jason.studypro.R;
import com.example.jason.studypro.constant.RoutePath;
import com.example.jason.studypro.databinding.HomeActLayoutBinding;
import com.example.jason.studypro.viewCtrl.HomeCtrl;

/**
 * @author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/11/8$ 15:44$
 * <p/>
 */
@Route(path = RoutePath.HOME_CTRL)
public class HomeAct extends Activity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        HomeActLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.home_act_layout);
        binding.setViewCtrl(new HomeCtrl());
    }
}
