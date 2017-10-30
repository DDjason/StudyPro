package com.example.jason.studypro.myView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.jason.studypro.R;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/14$ 15:14$
 * <p/>
 */
public class TextShowView extends View {
    private String text;
    private int    textWidth; //text的宽高
    private int    textHeight;
    private int    width; //控件宽高
    private int    height;
    private float  textsize;
    private Paint  paint;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextShowView(Context context) {
        this(context, null);
    }

    public TextShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init_Paint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextShowView);
        text = typedArray.getString(R.styleable.TextShowView_text);
        if (null == text) {
            text = "";
        }
        textsize = typedArray.getDimension(R.styleable.TextShowView_text_size, sp2px(16));
    }

    private void init_Paint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textsize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //控件高度
        width = measure(widthMeasureSpec, true);
        height = measure(heightMeasureSpec, false);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setTextSize(textsize);
        paint.setColor(Color.BLACK);

        int startX;
        int endX;
        int realWidth  = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
        int realHeight = (getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
        int textLeft   = getPaddingLeft() + realWidth / 2 - textWidth / 2;   //文本在控件中的起始x位置
        int textRight  = getPaddingLeft() + realWidth / 2 + textWidth / 2;   // 文本在控件中的结束x位置
        int textBottom = getPaddingTop() + realHeight / 2 + textHeight / 2;  //文本在控件中的结束y位置
        //    canvas.clipRect(0, 0, 200, 20);
        canvas.drawText(text, textLeft, textBottom, paint);
        paint.setColor(Color.BLUE);
        //  canvas.clipRect(0, 0, 100, 20);
        canvas.drawText(text, textLeft, textBottom, paint);
    }

    private int measure(int measureSpec, boolean isWidth) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
                Log.d("ccy", isWidth + "exactly");
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                if (isWidth) {
                    size = textWidth;
                } else {
                    size = textHeight;
                }
                Log.d("ccy", isWidth + "at most");
                break;
        }
        return isWidth ? (size + getPaddingLeft() + getPaddingRight()) : (size + getPaddingTop() + getPaddingBottom());
    }

    //工具
    private float dp2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private float sp2px(int sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }
}
