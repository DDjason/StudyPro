package com.example.jason.studypro.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.jason.studypro.BR;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/9$ 9:07$
 * <p/>
 */
public class GlideLoadModel extends BaseObservable{
    private String imageUrl;

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }
}
