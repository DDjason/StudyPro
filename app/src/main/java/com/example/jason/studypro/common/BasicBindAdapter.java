package com.example.jason.studypro.common;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.jason.tool.TextUtils;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/9$ 9:41$
 * <p/>
 */
public class BasicBindAdapter {
    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }

    @BindingAdapter({"imageUrl", "placeHolder", "error"})
    public static void loadImage(final ImageView imageView, String url, Drawable holderDrawable, Drawable errorDrawable) {
        Log.i("imageUrlCallBack", url == null ? "null" : url);
        if (TextUtils.isEmpty(url)) {
            return;
        }
        SimpleTarget<GlideDrawable> simpleTarget = new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation glideAnimation) {
                imageView.setImageDrawable(resource);
            }
        };
        Glide.with(imageView.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(holderDrawable)
                .error(errorDrawable)
                .into(simpleTarget);
    }
}
