package com.hjy.statusbar2.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.hjy.statusbar2.R;
import com.hjy.statusbar2.ui.Scene4Activity;

/**
 * <pre>
 *     author : HJY
 *     time   : 2018/03/15/15:25
 *     desc   : 文件描述
 *     version: 当前版本号
 * </pre>
 */
public class HaveToolBarFragment extends BaseFragment {
    private Scene4Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle args = getArguments();
    }

    @Override
    protected void configFragmentView(View view) {
        mActivity = ((Scene4Activity) getActivity());
        mActivity.mToolbar.setVisibility(View.VISIBLE);
        mActivity.mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        mActivity.mStatusBarView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        mActivity.mStatusBarView.setVisibility(View.VISIBLE);
        // 恢复默认statusBar文字颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mActivity.mToolbar.setVisibility(View.VISIBLE);
        mActivity.mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        mActivity.mStatusBarView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        mActivity.mStatusBarView.setVisibility(View.VISIBLE);
        // 恢复默认statusBar文字颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getFragmentView() {
        return R.layout.fragment_have_havetool;
    }

    @Override
    public String getFragmentTitle() {
        return "HappyNewYear_HaveToolBar";
    }
}
