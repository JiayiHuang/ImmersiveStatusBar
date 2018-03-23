package com.hjy.statusbar2.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * <pre>
 *     author : HJY
 *     time   : 2018/03/15/16:00
 *     desc   : 文件描述
 *     version: 当前版本号
 * </pre>
 */
public class FragmentUtil {
    /**
     * 判断当前Fragment的getActivity是否可用
     */
    public static boolean judgeGetActivityCanUse(Fragment fragment) {
        if (null != fragment) {
            FragmentActivity activity = fragment.getActivity();
            if (null != activity
                    && !activity.isFinishing()
                    && !fragment.isDetached()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public static void replaceSupportFragment(AppCompatActivity activity, int containerId, Class<? extends Fragment> fragmentClass, String tag, boolean addToBackStack) {
        try {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            if (addToBackStack) {
                transaction.addToBackStack(null);
            }
            transaction.replace(containerId, fragmentClass.newInstance(), tag);
            transaction.commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
