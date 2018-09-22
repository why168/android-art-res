package com.ryg.chapter_3.ui.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * ViewGroup
 *
 * @author Edwin.Wu edwin.wu05@gmail.com
 * @version 2018/9/22 上午12:05
 * @since JDK1.8
 */
public class EventLinearLayout extends LinearLayout {

    public EventLinearLayout(Context context) {
        super(context);
    }

    public EventLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("EventLinearLayout---> dispatchTouchEvent " + MotionEvent.actionToString(ev.getAction()));

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        System.out.println("EventLinearLayout---> onInterceptTouchEvent " + MotionEvent.actionToString(ev.getAction()));

        requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("EventLinearLayout---> onTouchEvent " + MotionEvent.actionToString(event.getAction()));

        return super.onTouchEvent(event);
    }
}
