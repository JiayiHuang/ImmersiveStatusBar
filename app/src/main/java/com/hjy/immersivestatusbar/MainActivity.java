package com.hjy.immersivestatusbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * <pre>
 *  原文：郭霖：http://mp.weixin.qq.com/s?__biz=MzA5MzI3NjE2MA==&mid=502753150&idx=1&sn=1d0b28a929b4e65287b34dee1e51e534&scene=20#rd
 *
 *  沉浸式状态栏相关的实现:
 *      根据百度百科上的定义，沉浸式就是要给用户提供完全沉浸的体验，使用户有一种置身于虚拟世界之中的感觉。
 *      对应到Android操作系统上面，界面应该没有状态栏也没有导航栏，用户可以完全沉浸在游戏当中，而不会被一些系统的界面元素所打扰。
 *      打造沉浸式模式的用户体验，就是要将这些系统元素全部隐藏，只留下主体内容部分。
 *
 *   View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN:
 *       这个标识，可以防止在全屏时人工交互，影响全屏效果，会直接覆盖在原内容上，不会退出全屏
 *   View.SYSTEM_UI_FLAG_FULLSCREEN:
 *       这个标识，在进入全屏后，有人工交互是，会退出全屏。
 *
 *
 * 另有具体各种情景模式下的实现方案，在Module StatusBar2 中，原文链接：https://juejin.im/post/5a52023b6fb9a01c9c1ed937
 *
 *  简答题:
 *      1. 全屏、不保留状态栏文字(Splash页面，欢迎页面)
 *      2. 全屏保留状态栏文字(页面上部有Banner图)
 *      3. 标题栏与状态栏颜色一致(部分App风格)
 *      4. 不同Fragment中对StatusBar的处理不一样
 *      5. 设置状态栏文字的颜色
 *      6. 切换fragment时，toolBar显示与否、statusBar显示与否、statusBar颜色、statusBar文字颜色（新增）
 *
 *  思考题:
 *      Activity中window是怎么回事？里面有什么View/ViewGroup？
 *      setFitsSystemWindows()是什么鬼？
 * </pre>
 */
public class MainActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.main_img);

//        hideStatusBarAbove16();
//        makeStatusBarTransparentAbove21();
//        hideNavigationBar();
//        simulateImmersive();
        realImmersive();


        // 根据Android的设计建议，ActionBar是不应该独立于状态栏而单独显示的，因此状态栏如果隐藏了，
        // 我们同时也需要调用ActionBar的hide()方法将ActionBar也进行隐藏。
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();

    }

    /**
     * 4.4(19+)以上版本，需要配合 onWindowFocusChanged() 使用
     */
    private void realImmersive() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }

    /**
     * 模拟沉浸式，透明状态栏，透明导航栏
     */
    private void simulateImmersive() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int option = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        }
        decorView.setSystemUiVisibility(option);
    }

    /**
     * 只支持5.0（21）
     */
    private void makeStatusBarTransparentAbove21() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            // TODO(Hjy): 2018/3/15 三星s7 edge 状态栏不是透明的。。。
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * >= 4.1(16) 以下版本不考虑兼容，只是全屏功能，隐藏了状态栏，没有隐藏导航栏（如果有的话）
     */
    private void hideStatusBarAbove16() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            // FULLSCREEN 隱藏狀態欄，但不隱藏導航欄
            decorView.setSystemUiVisibility(option);
        }
    }
}
