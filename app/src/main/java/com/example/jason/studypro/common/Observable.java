package com.example.jason.studypro.common;

import java.io.Serializable;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/13$ 15:17$
 * <p/>
 */
public class Observable<T> implements Serializable {
    private T mValue;

    public Observable() {

    }

    public Observable(T mValue) {
        this.mValue = mValue;
    }

    public T getmValue() {
        return mValue;
    }

    public void setmValue(T mValue) {
        this.mValue = mValue;
    }
}
