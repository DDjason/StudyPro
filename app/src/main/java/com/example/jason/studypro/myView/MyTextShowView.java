package com.example.jason.studypro.myView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/10$ 17:37$
 * <p/>
 */
public class MyTextShowView extends View {
    //显示的文字
    private String text_show = "abcgfn你好";
    //画笔-文字
    private Paint paintText;
    //画笔-线
    private Paint paintLine;
    private Rect  bounds;

    public MyTextShowView(Context context) {
        this(context, null);
    }

    public MyTextShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        toolInit();
    }

    //初始化
    private void toolInit() {
        paintLine = new Paint();
        paintLine.setColor(Color.RED);

        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextSize(120);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text_show, 0, 115, paintText);
        canvas.drawLine(0, 115, 3000, 115, paintLine);
        canvas.restore();
    }
}
