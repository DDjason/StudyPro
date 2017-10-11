package com.example.jason.studypro.act;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.jason.studypro.R;
import com.example.jason.studypro.constant.RoutePath;
import com.example.jason.studypro.databinding.PrivateUserShowBinding;
import com.example.jason.studypro.viewModel.PrivateUser;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/9$ 11:42$
 * <p/>
 */
@Route(path = RoutePath.Bind_Adapter_SHOW)
public class PrivateUserAct extends AppCompatActivity {
    private PrivateUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过DataBindingUtil加载我们的布局文件
        //在这里我们实例化了一个我们定义的DataBindingComponent对象MyComponent
        //这个是非常关键的一个地方，否则系统会使用DataBindingUtil#getDefaultComponent()拿到的默认实例作为参数
        //如果我们在此之前没有调用DataBindingUtil.setDefaultComponent()方法，上面的方法就会返回null
        PrivateUserShowBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.private_user_show);
        //将view与数据进行绑定
        user = new PrivateUser("privateFistName", "privateLastName");
        activityBinding.setPrivateUser(user);
    }
}