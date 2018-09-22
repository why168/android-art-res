# View的工作原理部分


```
已对View的measure过程分析，考虑一种情况，比如我们想在Activity启动的时候获取某个View的宽/高，实际上在onCreate/onStart/onResume均无法正确得到某个View的宽/高，这是因为View的measure过程和Activity的生命周期方法不是同步执行的，没有测量完毕获得的宽/高就是0。有没有什么方法能解决这几个问题呢？一共有四种方法
```

1. Activity/View#onWindowFocusChanged
2. view.post(runnable)
3. ViewTreeObserver
4. view.measure
5. measure(int widthMeasureSpec, int heightMeasureSpec)

