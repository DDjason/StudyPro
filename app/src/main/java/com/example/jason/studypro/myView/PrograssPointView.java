package com.example.jason.studypro.myView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/9/19$ 15:30$
 * <p/>
 */
public class PrograssPointView extends View {
    private Paint paint;

    public PrograssPointView(Context context) {
        super(context);
        init();
    }

    public PrograssPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PrograssPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth();
        int y = getHeight();

        paint.setAlpha(0x88);
        canvas.drawCircle(x / 2, y / 2, Math.min(x, y) / 2, paint);
        paint.setAlpha(0xFF);
        canvas.drawCircle(x / 2, y / 2, Math.min(x, y) * 7 / 24, paint);
    }

    public void setPointColor(int pointColor) {
        paint.setColor(pointColor);
        invalidate();
    }
}