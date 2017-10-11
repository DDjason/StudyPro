package com.example.jason.studypro.common;

import android.util.Log;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/20$ 10:09$
 * <p/>
 */
public class EnglishBook extends BaseBook {
    private String defaultName = "EnglishBook";
    private String counts = "";
    @Override
    void getBook() {
        Log.i(this.getClass().getName(),"类名");
    }

    @Override
    public String getBookCount() {
        return super.getBookCount();
    }

    @Override
    public String getBookName() {
        return defaultName;
    }
}
