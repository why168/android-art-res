# 第3章 View的事件体系

![Image of 示例](https://raw.githubusercontent.com/why168/android-art-res/master/Chapter_3/art/1.png)

## View坐标
```
View的宽高计算公式
width = right - left
height = bottom - top

Android3.0开始，View增加了额外几个参数：x、y、translationX、translationY
x和y是View左上角的坐标
translationX和translationY是View左上角相当于父容器的偏移量
translationX和translationY默认为0

换算关系公式
x = left + translationX
y = top + translationY

注意：
View在平移过程中，top和left表示是原始左上角的位置信息，其值并不会发生改变，发送变化的是x、y、translationX和translationY这4个参数
```

## MotionEvent
* ACTION_DOWN：手指刚接触屏幕
* ACTION_MOVE：手指在屏幕上移动
* ACTION_UP：手指从屏幕上松开的一瞬间
* 点屏幕后立即松开，事件顺序DOWN->UP
* 点屏幕滑动一会在松开，事件顺序DOWN->MOVE->MOVE->...MOVE->->UP
* getX/getY：相对于View左上角的x和y坐标
* getRawX/getRawY：相对于手机屏幕左上角的x和y坐标

## TouchSlop 临界点类
* TouchSlop这个常量是被系统认为滑动和点击事件的临界点
* ViewConfiguration.get(this).getScaledTouchSlop(); 获取系统的滑动常量来，判断此时是否属于滑动事件

## VelocityTracker 速率
* VelocityTracker obtain = VelocityTracker.obtain();
* obtain.addMovement(event);
* obtain.computeCurrentVelocity(1000);
* float xVelocity = obtain.getXVelocity();
* float yVelocity = obtain.getYVelocity();
* obtain.clear();
* obtain.recycle();

```
获取速度之前必须先计算速度，getXVelocity、getYVelocity这2个方法前面必须调用computeCurrentVelocity
这里的速度指一段时间内手指所滑过的像素
比如将时间间隔设为1000ms时，在1s内手指在水平方向从左向右滑过100像素，那么水平速度就是100（速度可以为负数）
当手指从右向左滑动时，水平方向速度即为负值

速度计算公式
速度 = （终点位置 - 起点位置）/ 时间段

computeCurrentVelocity这个方法参数表示的是一个时间单位或者说时间间隔，它的单位是毫秒（ms）
```

## Gesture

* mGestureDetector = new GestureDetector(this, new MyOnGestureListener());
* boolean consume = mGestureDetector.onTouchEvent(event);



方法名     | 描述                                  | 所属接口
--------  | ------------------------------------ | ------------------- |
onDown  | 手指轻轻触摸屏幕一瞬间，由1个ACTION_DOWN触  | OnGestureListener  |
onShowPress  | 手指轻轻触摸屏幕，尚未松开或拖动，由1个ACTION_DOWN触发，这是单击行为  | OnGestureListener  |
onSingleTapUp  | 手指（轻轻触摸屏幕后）松开，伴随着1个ACTION_UP而触发，这是单击行为  | OnGestureListener  |
onScroll  | 手指按下屏幕并拖动，由1个ACTION_DOWN，多个ACTION_MOVE触发，这是拖动行为  | OnGestureListener  |
onLongPress  | 用户长久按着屏幕不放，即长按  | OnGestureListener  |
onFling  | 用户按下触摸屏、快速滑动后松开，由1个ACTION_DOWN，多个ACTION_MOVE触发和1个ACTION_UP,这是快速滑动行为 | OnGestureListener  |
onDoubleTap  | 双击，由2次连续的单击组成，它不可能和onSingTapConfirmed共存 |  OnDoubleTapListener |
onSingleTapConfirmed  | 严格的单机行为 | OnDoubleTapListener |
onDoubleTapEvent  | 表示发生了双击行为，在双击的期间，ACTION_DOWN、ACTION_MOVE、ACTION_UP | OnDoubleTapListener |





## VelocityTracker