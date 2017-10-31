package com.example.jason.studypro.myView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.jason.studypro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/31$ 8:59$
 * <p/>
 */
public class WeekView extends ViewGroup {
    /**
     * 用户点击监听回调
     */
    OnClickListener listener;
    /**
     * 滚动操作实例
     */
    private Scroller scroller;
    /**
     * 最小移动像素
     */
    private int      mTouchSlop;
    /**
     * 显示数据
     */
    List<String> mDatas      = new ArrayList<>();
    /**
     * 默认选中
     */
    int          selectIndex = 2;
    int itemWidth;
    private int view_margin_left_or_right;
    /**
     * 左边界
     */
    private int leftBorder;
    /**
     * 右边界
     */
    private int rightBorder;

    public WeekView(Context context) {
        this(context, null);
    }

    public WeekView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //step 1 : 创建Scroller的实例
        scroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        //step 2 : 获取最小TouchSlop值
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }

    /**
     * 主要内容为重新设置高度
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int defaultHeight  = 0, childHeight = 0;
        int widthSpecMode  = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize  = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightSpecMode == MeasureSpec.AT_MOST) {
            //高度设置为wrap_content
            for (int i = 0; i < getChildCount(); i++) {
                childHeight = (int) (getChildAt(0).getMeasuredHeight() * 1.5);
            }
            setMeasuredDimension(widthSpecSize, childHeight);
        } else {
            //高度设置为match_parent或者具体值
            setMeasuredDimension(widthSpecSize, heightSpecSize);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //每行显示五个
        itemWidth = getWidth() / 5;
        int right, bottom, left;
        bottom = getHeight() - (getHeight() - getChildAt(0).getMeasuredHeight()) / 2;
        view_margin_left_or_right = (itemWidth - getChildAt(0).getMeasuredWidth()) / 2;
        //居中的左边界
        int left_X = getWidth() - itemWidth * 3;
        for (int j = selectIndex - 1; j >= 0; j--) {
            //中间View左边的View
            View view = getChildAt(j);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            right = left_X - view_margin_left_or_right - itemWidth * (selectIndex - 1 - j);
            view.layout(right - getChildAt(j).getMeasuredWidth(), bottom - getChildAt(j).getMeasuredHeight(), right, bottom);
        }
        //中间一个的右边界坐标
        int right_X = itemWidth * 3;
        for (int m = selectIndex + 1; m < getChildCount(); m++) {
            //中间那个view右边的那些view
            View view = getChildAt(m);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            left = right_X + view_margin_left_or_right + itemWidth * (m - (selectIndex + 1));
            view.layout(left, bottom - getChildAt(m).getMeasuredHeight(), left + getChildAt(m).getMeasuredWidth(), bottom);
        }

        //中间一个view
        left = itemWidth * 2 + view_margin_left_or_right;
        getChildAt(selectIndex).layout(left, bottom - getChildAt(selectIndex).getMeasuredHeight(), left + getChildAt(selectIndex).getMeasuredWidth(), bottom);
        getChildAt(selectIndex).setScaleX(1.2f);
        getChildAt(selectIndex).setScaleY(1.2f);

        // 初始化左右边界值
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(getChildCount() - 1).getRight();
    }


    /**
     * 手机按下时的屏幕坐标
     */
    private float mxDown;
    /**
     * 手机当前所处的屏幕坐标
     */
    private float mxMove;
    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mxLastMove;

    /**
     * 主要是解决触摸冲突
     *
     * @param ev
     *
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mxDown = ev.getRawX();
                mxLastMove = mxDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mxMove = ev.getRawX();
                float dx = Math.abs(mxMove - mxDown);
                if (dx > mTouchSlop) {
                    //返回true
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                mxMove = event.getRawX();
                //计算本次View的移动距离
                int scrolledX = (int) (mxLastMove - mxMove);

                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder - view_margin_left_or_right, 0);
                    return true;
                } else if (getScrollX() + scrolledX > rightBorder - getWidth()) {
                    //以前滑动的距离加上本次滑动的距离比右边最后一个view的right减去viewGroup的宽度大 既为滑出右边界了 滑出右边界是向左滑动所以getScrollX()为正值
                    scrollTo(rightBorder + view_margin_left_or_right - getWidth(), 0);
                    return true;
                }
                scrollBy(scrolledX, 0);
                mxLastMove = mxMove;
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 数据的加载和显示
     */
    public void setData(List<String> mList, OnClickListener mlistener) {
        listener = mlistener;
        mDatas = mList;
        if (mDatas != null) {
            for (int i = 0; i < mDatas.size(); i++) {
                TextView tv = (TextView) LayoutInflater.from(getContext())
                        .inflate(R.layout.week_item_view, this, false);

                tv.setText(mDatas.get(i));
                tv.setTag(i);
                tv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = (int) view.getTag();
                        startAnim(pos, selectIndex);
                        selectIndex = pos;
                    }
                });
                if (i > 1 && selectIndex == i && i < mDatas.size() - 2) {
                    selectIndex = i;
                }
                addView(tv);
            }
        }
    }

    private void startAnim(int current, int last) {

        if (current == last) {
            return;
        }

        ObjectAnimator anim1_current = ObjectAnimator.ofFloat(getChildAt(current), "scaleX", 1.0f, 1.2f);
        ObjectAnimator anim2_current = ObjectAnimator.ofFloat(getChildAt(current), "scaleY", 1.0f, 1.2f);

        ObjectAnimator anim1_last = ObjectAnimator.ofFloat(getChildAt(last), "scaleX", 1.2f, 1.0f);
        ObjectAnimator anim2_last = ObjectAnimator.ofFloat(getChildAt(last), "scaleY", 1.2f, 1.0f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.playTogether(anim1_current, anim2_current, anim1_last, anim2_last);
        set.start();

        int dx = (int) Math.abs(mxMove - mxLastMove);
        //以前滑动的距离加上本次滑动的距离比左边第一个view的lfet小 既为滑出左边界了 滑出左边界是向右滑动所以getScrollX()为负值
        if (getScrollX() + dx < leftBorder) {
            scrollTo(leftBorder - view_margin_left_or_right, 0);
            return;
        } else if (getScrollX() + dx > rightBorder - getWidth()) {//以前滑动的距离加上本次滑动的距离比右边最后一个view的right减去viewGroup的宽度大 既为滑出右边界了 滑出右边界是向左滑动所以getScrollX()为正值
            scrollTo(rightBorder + view_margin_left_or_right - getWidth(), 0);
            return;
        }
        //        scrollBy(dx,0);
        scroller.startScroll(getScrollX(), 0, dx, 0, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }
}
