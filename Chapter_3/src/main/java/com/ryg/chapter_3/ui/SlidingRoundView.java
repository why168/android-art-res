package com.ryg.chapter_3.ui;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ryg.chapter_3.R;

/**
 * 浮球
 *
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
    private ViewConfiguration mViewConfiguration;

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

        mViewConfiguration = ViewConfiguration.get(context);

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
        System.out.println("onWindowFocusChanged hasWindowFocus = " + hasWindowFocus);
    }

    // 在所有的View对象都创建好了后会调用
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        System.out.println("onFinishInflate");
    }

    // 在视图对象都创建好后, 调用
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        System.out.println("onAttachedToWindow");
    }

    // 当Activity退出或当前View被移除触发
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        System.out.println("onDetachedFromWindow");
    }


    private float moveX = 0;
    private float moveY = 0;
    float DownX = 0.0f;
    float DownY = 0.0f;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        float rawX = event.getRawX();
        float rawY = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                DownX = event.getRawX();
                DownY = event.getRawY();

                moveX = 0;
                moveY = 0;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                moveX += Math.abs(event.getX() - DownX);//X轴距离
                moveY += Math.abs(event.getY() - DownY);//y轴距离
                DownX = event.getX();
                DownY = event.getY();


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
                int scaledTouchSlop = mViewConfiguration.getScaledTouchSlop();

                if ((moveX > scaledTouchSlop || moveY > scaledTouchSlop)) {

                    // 判断在屏幕左边还是右边
                    int scrollX = getScrollX();
                    int scrollY = getScrollY();

                    float currentRawX = event.getRawX();
                    if (currentRawX <= (width / 2)) { // 屏幕左边
                        localComputeScroll((int) currentRawX, 0);
                    } else { // 屏幕右边
                        localComputeScroll((int) currentRawX, (width - viewWidth));
                    }
                    invalidate();
                    System.out.println("scrollX = " + scrollX + ",scrollY = " + scrollY + ",event.getRawX() = " + event.getRawX());
                }
                break;
            }
        }

        mLastX = rawX;
        mLastY = rawY;
        super.onTouchEvent(event);
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
