package com.example.jason.studypro.myView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.jason.studypro.R;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/14$ 17:28$
 * <p/>
 */
public class ImageProcessView extends View{
    private Bitmap mBitmap;

    private  float[] mMatrixFloats = new float[] {
            1, 0, 0, 0, 0,
            0, 2, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0
    };

    private float seek_bar_1;
    private float seek_bar_2;

    public ImageProcessView(Context context){
        this(context,null);
    }

    public ImageProcessView(Context context , AttributeSet attrs){
        super(context,attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.supermandebug);
    }

    public float getSeek_bar_1() {
        return seek_bar_1;

    }

    public void setSeek_bar_1(float seek_bar_1) {
        this.seek_bar_1 = seek_bar_1;
        mMatrixFloats[0] = this.seek_bar_1/100;
        invalidate();
    }

    public float getSeek_bar_2() {
        return seek_bar_2;
    }

    public void setSeek_bar_2(float seek_bar_2) {
        this.seek_bar_2 = seek_bar_2;
        mMatrixFloats[6] = this.seek_bar_2/100;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        mPaint.setColorFilter(new ColorMatrixColorFilter(mMatrixFloats));
        canvas.drawBitmap(mBitmap,0,0,mPaint);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,mBitmap.getHeight());
    }
}
