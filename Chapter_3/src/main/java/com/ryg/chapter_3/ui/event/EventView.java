package com.ryg.chapter_3.ui.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * View
 *
 * @author Edwin.Wu edwin.wu05@gmail.com
 * @version 2018/9/22 上午12:06
 * @since JDK1.8
 */
public class EventView extends android.support.v7.widget.AppCompatTextView {

    public EventView(Context context) {
        super(context);
    }

    public EventView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        System.out.println("EventView---> dispatchTouchEvent " + MotionEvent.actionToString(event.getAction()));

        return super.dispatchTouchEvent(event);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("EventView---> onTouchEvent " + MotionEvent.actionToString(event.getAction()));

        return super.onTouchEvent(event);
    }
}
