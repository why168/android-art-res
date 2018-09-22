package com.ryg.chapter_3;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * 事件测试
 * <p>
 * dispatchTouchEvent
 * onInterceptTouchEvent
 * onTouchEvent
 *
 * @author Edwin.Wu edwin.wu05@gmail.com
 * @version 2018/9/22 上午12:04
 * @since JDK1.8
 */
public class EventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("EventActivity---> dispatchTouchEvent " + MotionEvent.actionToString(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("EventActivity---> onTouchEvent " + MotionEvent.actionToString(event.getAction()));
        return super.onTouchEvent(event);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }
}
