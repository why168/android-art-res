package com.ryg.chapter_3.ui.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * ViewGroup
 *
 * @author Edwin.Wu edwin.wu05@gmail.com
 * @version 2018/9/22 上午12:08
 * @since JDK1.8
 */
public class EventFrameLayout extends FrameLayout {

    public EventFrameLayout(@NonNull Context context) {
        super(context);
    }

    public EventFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EventFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("EventFrameLayout---> dispatchTouchEvent " + MotionEvent.actionToString(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        System.out.println("EventFrameLayout---> onInterceptTouchEvent " + MotionEvent.actionToString(ev.getAction()));
//        return super.onInterceptTouchEvent(ev);
        return true;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("EventFrameLayout---> onTouchEvent " + MotionEvent.actionToString(event.getAction()));
//        return super.onTouchEvent(event);
        return true;
    }
}
