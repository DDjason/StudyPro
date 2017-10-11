package com.example.jason.studypro.myView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/18$ 17:32$
 * <p/>
 */
public class MyPswTextView extends View{


    //绘制原点
    private Paint paintDot;

    public MyPswTextView(Context context){
        this(context,null);
    }

    public MyPswTextView(Context context, AttributeSet attrs){
        super(context,attrs);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(5,7,5,paintDot);
    }


    private void initPaint(){
        paintDot = new Paint();
        paintDot.setColor(Color.BLUE);
        paintDot.setAntiAlias(true);
        paintDot.setStrokeWidth(2);
        paintDot.setStyle(Paint.Style.FILL);


    }
}
