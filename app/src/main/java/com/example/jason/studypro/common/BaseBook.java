package com.example.jason.studypro.common;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/20$ 10:03$
 * <p/>
 */
public abstract class BaseBook implements ReadBookInter {
    @Override
    public void readBook() {

    }

    @Override
    public String getBookCount() {
        return null;
    }

    @Override
    public String getBookName() {
        return null;
    }

    abstract void getBook();
}
