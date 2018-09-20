package com.ryg.chapter_3.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Scroller;

import com.nineoldandroids.view.ViewHelper;
import com.ryg.chapter_3.R;

/**
 * @author Edwin.Wu edwin.wu05@gmail.com
 * @version 2018/9/20 下午9:27
 * @since JDK1.8
 */
public class SlidingRoundView extends AppCompatImageView {
    // 分别记录上次滑动的坐标
    private float mLastX = 0;
    private float mLastY = 0;

    private int width;
    private int heigth;

    private int viewWidth = 0;
    private int viewHeight = 0;

    public SlidingRoundView(Context context) {
        super(context, null);
    }

    public SlidingRoundView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setImageResource(R.drawable.round_icon);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        heigth = dm.heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float rawX = event.getRawX();
        float rawY = event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                float deltaX = rawX - mLastX;
                float deltaY = rawY - mLastY;

//                System.out.println("move, deltaX:" + deltaX + " deltaY:" + deltaY);
                float translationX = getTranslationX() + deltaX;
                float translationY = getTranslationY() + deltaY;

                setTranslationX(translationX);
                setTranslationY(translationY);
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE: {
                // 判断在屏幕左边还是右边
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                float currentRawX = event.getRawX();
                if (currentRawX <= (width / 2)) { // 屏幕左边
                    localComputeScroll((int) currentRawX, 0);
                } else { // 屏幕右边
                    localComputeScroll((int) currentRawX, (width - viewWidth));
                }

                System.out.println("scrollX = " + scrollX + ",scrollY = " + scrollY + ",event.getRawX() = " + event.getRawX());
                invalidate();
                break;
            }
        }

        mLastX = rawX;
        mLastY = rawY;
//        return super.onTouchEvent(event);
        return true;
    }

    public void localComputeScroll(int startValue, int endValue) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startValue, endValue);
        valueAnimator.setDuration(250);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                setX(animatedValue);
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
}
