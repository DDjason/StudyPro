package com.example.jason.studypro.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.jason.studypro.BR;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/26$ 11:34$
 * <p/>
 */
public class RecycleModel extends BaseObservable {
    private String editInput;

    @Bindable
    public String getEditInput() {
        return editInput;
    }

    public void setEditInput(String editInput) {
        this.editInput = editInput;
        notifyPropertyChanged(BR.editInput);
    }
}
