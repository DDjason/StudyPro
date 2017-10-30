package com.example.jason.studypro.viewCtrl;

import android.util.Log;
import android.view.View;

import com.example.jason.studypro.databinding.ActNoteEditBinding;
import com.example.jason.studypro.viewModel.NoteModel;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/25$ 16:06$
 * <p/>
 */
public class NoteEditCtrl {
    private NoteModel          viewModel;
    private ActNoteEditBinding binding;

    public NoteEditCtrl(ActNoteEditBinding binding) {
        viewModel = new NoteModel();
        this.binding = binding;
    }

    public void onClickSave(View view) {
        Log.i(this.getClass().getName(), "save edit");
    }

    public NoteModel getViewModel() {
        return viewModel;
    }
}
