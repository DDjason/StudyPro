package com.example.jason.studypro.common;

import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;

import com.example.jason.studypro.viewModel.PrivateUser;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/9$ 11:40$
 * <p/>
 */
public class MyBindingAdapter {
    @BindingAdapter({"text"})
    public void setText(View textView, PrivateUser user) {
        Log.d("setText", "isCalled");
    }
}
