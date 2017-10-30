package com.example.jason.studypro.viewCtrl;

import com.example.jason.studypro.viewModel.GlideLoadModel;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/9$ 9:07$
 * <p/>
 */
public class GlideLoadCtrl {
    private GlideLoadModel viewModel;

    public GlideLoadCtrl() {
        viewModel = new GlideLoadModel();
    }

    public void onClickLoad() {
        viewModel.setImageUrl("http://p1.pstatp.com/large/166200019850062839d3");
    }

    public GlideLoadModel getViewModel() {
        return viewModel;
    }
}
