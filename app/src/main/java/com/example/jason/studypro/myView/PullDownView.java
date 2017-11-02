package com.example.jason.studypro.myView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/31$ 17:49$
 * <p/>
 */
public class PullDownView extends View {
    public PullDownView(Context context) {
        this(context, null);
    }

    public PullDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }

    /**
     * 贝塞尔曲线画笔
     */
    private Paint mPaintLine;
    private Paint mPaintPoint2;
    /**
     * 点画笔
     */
    private Paint mPaintPoint;
    private Path  mPath;
    private int   mLeft, mRight, mTop, mBottom;
    private int mWidth, mHeight;
    private Point mPointM1, mPointM2, mPointM3, mPointC1, mPointC2;

    /**
     * 画笔颜色初始化
     */
    private void initDraw() {
        mPaintLine = new Paint();
        mPaintLine.setColor(Color.BLUE);
        mPaintLine.setStyle(Paint.Style.STROKE);
        mPaintLine.setStrokeWidth(2);
        mPaintPoint = new Paint();
        mPaintPoint.setColor(Color.RED);
        mPaintPoint.setStyle(Paint.Style.FILL);
        mPath = new Path();
        mPointC1 = new Point();
        mPointC2 = new Point();
        mPointM1 = new Point();
        mPointM2 = new Point();
        mPointM3 = new Point();
        mPaintPoint2 = new Paint();
        mPaintPoint2.setColor(Color.BLACK);
        mPaintPoint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mPointC1.set(mWidth / 4, 0);
        mPointC2.set(mWidth / 4 * 3, 0);
        mPointM1.set(0, 0);
        mPointM2.set(mWidth / 2, 0);
        mPointM3.set(mWidth, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //左边
        mPath.moveTo(mPointM2.x - (float) Math.cos(2 * Math.PI / 360 / moveCal) * 50, mPointM2.y + (float) Math.sin(2 * Math.PI / 360 / moveCal) * 50);
        mPath.quadTo(mPointC1.x + mWidth / 4 * moveCal, mPointC1.y, mPointM1.x + mWidth / 2 * moveCal, mPointM1.y);
        canvas.drawCircle(mPointM2.x - (float) Math.cos(2 * Math.PI / 360 / moveCal) * 50, mPointM2.y + (float) Math.sin(2 * Math.PI / 360 / moveCal) * 50,
                5, mPaintPoint2);
        canvas.drawCircle(mPointC1.x + mWidth / 4 * moveCal, mPointC1.y, 3, mPaintPoint);
        canvas.drawCircle(mPointM1.x + mWidth / 2 * moveCal, mPointM1.y, 3, mPaintPoint);
        canvas.drawPath(mPath, mPaintLine);
        //右边
        mPath.moveTo(mPointM2.x + (float) Math.cos(2 * Math.PI / 360 / moveCal) * 50, mPointM2.y + (float) Math.sin(2 * Math.PI / 360 / moveCal) * 50);
        mPath.quadTo(mPointC2.x - mWidth / 4 * moveCal, mPointC2.y, mPointM3.x - mWidth / 2 * moveCal, mPointM3.y);
        canvas.drawCircle(mPointM2.x + (float) Math.cos(2 * Math.PI / 360 / moveCal) * 50, mPointM2.y + (float) Math.sin(2 * Math.PI / 360 / moveCal) * 50,
                5, mPaintPoint2);
        canvas.drawCircle(mPointC2.x - mWidth / 4 * moveCal, mPointC2.y, 3, mPaintPoint);
        canvas.drawCircle(mPointM3.x - mWidth / 2 * moveCal, mPointM3.y, 3, mPaintPoint);
        canvas.drawPath(mPath, mPaintLine);

        //画圆
        canvas.drawCircle(mPointM2.x, mPointM2.y, 50, mPaintPoint);

        // super.onDraw(canvas);
    }

    private float moveCal = 0;
    private float localY;
    private int   absY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                localY = event.getRawY();
                return true;
            case MotionEvent.ACTION_MOVE:
                mPath.reset();
                absY = (int) Math.abs(event.getRawY() - localY);
                moveCal = (float) absY / mHeight;
                Log.i("moveCal", "" + moveCal + " " + absY + " " + mHeight);
                mPointM2.set(mWidth / 2, absY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mPath.reset();
                moveCal = 0;
                mPointM2.set(mWidth / 2, 0);
                invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
