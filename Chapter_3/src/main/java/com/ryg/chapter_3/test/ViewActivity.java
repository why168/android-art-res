package com.ryg.chapter_3.test;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;

import com.ryg.chapter_3.R;

import java.util.logging.Logger;

public class ViewActivity extends Activity {
    private Button moveButton;
    private int count = 1;

    private VelocityTracker obtain; // 速率
    private int scaledTouchSlop; // 最小滑动距离
    private GestureDetector mGestureDetector; // 手势检测

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        obtain = VelocityTracker.obtain();

        mGestureDetector = new GestureDetector(this, new MyOnGestureListener());
        // 解决长按屏幕后无法拖动的现象
        mGestureDetector.setIsLongpressEnabled(false);

        moveButton = findViewById(R.id.moveButton);

        moveButton.post(new Runnable() {
            @Override
            public void run() {
                getInfo();
            }
        });

        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveButton.setTranslationX(10 * count);
                count++;

                getInfo();

            }
        });

    }

    private void getInfo() {
        int width = moveButton.getWidth();
        int height = moveButton.getHeight();
        float x = moveButton.getX();
        float y = moveButton.getY();
        float translationX = moveButton.getTranslationX();
        float translationY = moveButton.getTranslationY();

        Log.e("Edwin", "width = " + width + "，height = " + height + "\n" +
                "x = " + x + ",y = " + y + "\n" +
                "translationX = " + translationX + ", translationY = " + translationY);

        scaledTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getInfo();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        obtain.addMovement(event);
        boolean consume = mGestureDetector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
            }
            case MotionEvent.ACTION_MOVE: {
            }
            case MotionEvent.ACTION_UP: {
                obtain.computeCurrentVelocity(1000);
                float xVelocity = obtain.getXVelocity();
                float yVelocity = obtain.getYVelocity();


                if (xVelocity > Math.abs(scaledTouchSlop)) {
                    Log.e("Edwin", "左右滑动");
                }

                if (yVelocity > Math.abs(scaledTouchSlop)) {
                    Log.e("Edwin", "上下滑动");
                }

                Log.i("Edwin", "xVelocity = " + xVelocity + ",yVelocity = " + yVelocity);
                obtain.clear();
            }
        }
//        return super.onTouchEvent(event);

        return consume;
        // 速度 = （终点位置 - 起点位置）/ 时间段
    }


    /**
     * 手势检测
     */
    class MyOnGestureListener implements GestureDetector.OnGestureListener {

        // 手指轻轻触摸屏幕一瞬间，由1个ACTION_DOWN触
        @Override
        public boolean onDown(MotionEvent e) {
            Log.i("MyOnGestureListener", "onDown");
            return false;
        }

        // 手指轻轻触摸屏幕，尚未松开或拖动，由1个ACTION_DOWN触发，这是单击行为
        @Override
        public void onShowPress(MotionEvent e) {
            Log.i("MyOnGestureListener", "onShowPress");

        }

        // 手指（轻轻触摸屏幕后）松开，伴随着1个ACTION_UP而触发，这是单击行为
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("MyOnGestureListener", "onSingleTapUp");
            return false;
        }

        // 手指按下屏幕并拖动，由1个ACTION_DOWN，多个ACTION_MOVE触发，这是拖动行为
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i("MyOnGestureListener", "onScroll");
            return false;
        }

        // 用户长久按着屏幕不放，即长按
        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("MyOnGestureListener", "onLongPress");
        }

        // 用户按下触摸屏、快速滑动后松开，由1个ACTION_DOWN，多个ACTION_MOVE触发和1个ACTION_UP
        // 这是快速滑动行为
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("MyOnGestureListener", "onFling");
            return false;
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
