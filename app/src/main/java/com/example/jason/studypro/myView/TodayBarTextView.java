package com.example.jason.studypro.myView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/9$ 17:37$
 * <p/>
 */
public class TodayBarTextView extends AppCompatTextView {
    float process;

    public TodayBarTextView(Context context) {
        this(context, null);
    }

    public TodayBarTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TodayBarTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        Log.i("TodayBarTextView", getText().toString());
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(getTextSize());
        Paint mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setColor(Color.GRAY);
        mPaint2.setTextSize(getTextSize());
        float stringWidth = mPaint.measureText(getText().toString());
        float x           = (getWidth() - stringWidth) / 2;
        if (process <= 0) {
            process = 1;
        }
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(x, 0, stringWidth, getMeasuredHeight());
        canvas.drawText(getText().toString(), x, getHeight() / 2, mPaint2);
        canvas.restore();
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        float WidthTemp = stringWidth * process;
        canvas.clipRect(x, 0, WidthTemp, getMeasuredHeight());
        canvas.drawText(getText().toString(), x, getHeight() / 2, mPaint);
        canvas.restore();
    }

    public void setProcess(float process) {
        this.process = process;
        invalidate();
    }
}
