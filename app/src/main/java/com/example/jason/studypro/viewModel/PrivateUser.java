package com.example.jason.studypro.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.jason.studypro.BR;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/9$ 11:39$
 * <p/>
 */
public class PrivateUser extends BaseObservable {
    private String fistName;
    private String lastName;

    public PrivateUser(String fistName, String lastName) {
        this.fistName = fistName;
        this.lastName = lastName;
    }

    @Bindable
    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
        notifyPropertyChanged(BR.fistName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLasetName(String lasetName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }
}
