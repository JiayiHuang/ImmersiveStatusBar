package com.hjy.statusbar2.utils;

import android.content.Context;

/**
 * <pre>
 *     author : HJY
 *     time   : 2018/03/15/16:22
 *     desc   : 文件描述
 *     version: 当前版本号
 * </pre>
 */
public class ScreenUtil {

    public static int getStatusBarHeight(Context context) {
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }
}
