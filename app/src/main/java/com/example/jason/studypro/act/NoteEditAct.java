package com.example.jason.studypro.act;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.jason.studypro.R;
import com.example.jason.studypro.constant.RoutePath;
import com.example.jason.studypro.databinding.ActNoteEditBinding;
import com.example.jason.studypro.viewCtrl.NoteEditCtrl;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/25$ 16:17$
 * <p/>
 */
@Route(path = RoutePath.NOTE_EDIT_SHOW)
public class NoteEditAct extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActNoteEditBinding binding = DataBindingUtil.setContentView(this, R.layout.act_note_edit);
        binding.setViewCtrl(new NoteEditCtrl(binding));
    }
}
