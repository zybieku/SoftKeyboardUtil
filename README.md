
## 通过一行代码实现软键盘与EditText的交互.

> **介绍 :** 
 >有一个很常见的需求,就是页面中软键盘打开时,希望能**点击屏幕其他的任何位置,就能把软键盘给隐藏掉.**  

 >比如登录, 注册之类的常见页面. 

 >在实际开发中,很多大佬通过其他控件的点击事件,在onclick方法中调用软键盘的隐藏方法.

 >在页面复杂的时候,要实现的onclick方法会很多,甚至还会有逻辑处理冲突的情况.这样做就显得非常不友好. 


----------
####方式一 继承方式

> **原理 :** 怎样封装成一行代码实现,就是重写Activity的dispatchTouchEvent(MotionEvent ev)方法,全局监听触摸事件, 当点击的页面时,发现如果焦点在EditView上,就把软键盘隐藏,否则就不做处理.

>**使用:** 只要对应的页面 重写下面一个方法,改变return的返回值即可
 ```
    @Override
    public int[] hideSoftByEditViewIds() {
        return 传入页面EditText的id[]
    }
 ```
> 如果要对某些控件进行过滤 ,可以重写
   ```
      @Override
    public View[] filterViewByIds() {
           return 传入要过滤的View[]
    }
   ```
####方式二  proxy链式调用(由jiiiiiin提供)

```
      try {
            mActivityKeyBoardProxy = ActivityKeyBoardProxyBuild.getInstance()
                    .withActivity(this)
                    .withHideSoftByEditViewIds(new int[]{R.id.et_phone, R.id.et_check_code, R.id.et_city_code})
                    .withFilterViewByIds(new View[]{mBtnCode})
                    .withOnHideInputForceListener(new ActivityKeyBoardProxy.OnHideInputForceListener() {
                        @Override
                        public void onHideInputForce(MotionEvent motionEvent) {
                            Log.d(TAG, "隐藏了系统键盘");
                        }
                    })
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
```

----------
>**修复bug:** 
   修复连续点击EditText,软键盘会先关闭在打开的问题
----------
> **效果 :** 

![图片](https://github.com/zybieku/SoftKeyboardUtil/blob/master/gif/GIF.gif)

----------
由于半年多没写Android,很多代码陌生了,欢迎各位大佬提pull request
喜欢的朋友记得点个Star
具体详情请看主页
[博客地址](http://blog.csdn.net/zybieku/article/details/68925116)



