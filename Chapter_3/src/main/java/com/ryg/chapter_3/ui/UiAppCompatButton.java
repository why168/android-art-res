package com.ryg.chapter_3.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.Scroller;

import com.nineoldandroids.view.ViewHelper;

/**
 * @author Edwin.Wu edwin.wu05@gmail.com
 * @version 2018/9/20 下午7:27
 * @since JDK1.8
 */
public class UiAppCompatButton extends AppCompatButton {
    private final Scroller mScroller; // 弹性滑动


    public UiAppCompatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public void smoothScrollerTo(int destX, int destY) {
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int delta = destX - scrollX;

        // 1000ms 内滑向destX，效果就是慢慢滑动
        mScroller.startScroll(scrollX, 0, delta, 0);
        invalidate();
    }


    @Override
    public void computeScroll() {
//        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            int currX = mScroller.getCurrX();
            int currY = mScroller.getCurrY();
            System.out.println("currX = " + currX + " ,currY = " + currY);
            scrollTo(currX, currY);
//            ViewHelper.setTranslationX(this,currX);
//            ViewHelper.setTranslationY(this,currY);

            postInvalidate();
        }
    }


}
