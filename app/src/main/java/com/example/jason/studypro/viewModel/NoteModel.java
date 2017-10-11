package com.example.jason.studypro.viewModel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.jason.studypro.BR;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/25$ 16:08$
 * <p/>
 */
public class NoteModel extends BaseObservable{
    private String note_text;

    @Bindable
    public String getNote_text() {
        return note_text;
    }

    public void setNote_text(String note_text) {
        this.note_text = note_text;
        notifyPropertyChanged(BR.note_text);
    }
}
