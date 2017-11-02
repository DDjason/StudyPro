package com.example.jason.studypro.viewCtrl;

import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jason.studypro.databinding.CoordinatorLayoutActBinding;

/**
 * @author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/11/2$ 9:33$
 * <p/> {@link com.example.jason.studypro.act.CoordinatorLayoutAct}
 */
public class CoordinatorLayoutCtrl {
    private CoordinatorLayoutActBinding binding;

    public CoordinatorLayoutCtrl(CoordinatorLayoutActBinding binding) {
        this.binding = binding;
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) binding.child.getLayoutParams();
        params.setBehavior(new MyBehavior());
        binding.dependency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("dependency", "dependency");

            }
        });
    }

    class MyBehavior extends CoordinatorLayout.Behavior<Button> {
        @Override
        public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
            return TextUtils.equals(child.getText(), "dependency");
        }

        @Override
        public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {
            CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) dependency.getLayoutParams();
            layoutParams.leftMargin++;
            layoutParams.topMargin++;
            return true;
        }
    }
}
