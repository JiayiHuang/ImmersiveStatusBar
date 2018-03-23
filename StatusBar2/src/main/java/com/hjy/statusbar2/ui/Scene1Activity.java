package com.hjy.statusbar2.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.hjy.statusbar2.R;

/**
 * <pre>
 *     author : HJY
 *     time   : 2018/03/15/14:12
 *     desc   : 全屏，不保留状态栏文字(Splash页面，欢迎页面)
 *     version: 当前版本号
 * </pre>
 */
public class Scene1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene1_fullscreen);

        // 方式一
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 方式二
        /*
        在xml文件中的所使用的主题中设置，
        <style name="Scene_1" parent="Theme.AppCompat.Light.NoActionBar">
            <item name="android:windowFullscreen">true</item>
        </style>
         */

        // 方式三 这种体验不好，需要配合操作 StatusBar ,要求版本较高（设置StatusBar color transparent 和 View.SYSTEM_UI_FLAG_ 的使用）
/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
*/
    }
}
