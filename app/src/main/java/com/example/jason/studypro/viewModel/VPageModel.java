package com.example.jason.studypro.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.jason.studypro.BR;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/10$ 11:21$
 * <p/>
 */
public class VPageModel extends BaseObservable {
    private float process;

    @Bindable
    public float getProcess() {
        return process;
    }

    public void setProcess(float process) {
        this.process = process;
        notifyPropertyChanged(BR.process);
    }
}
