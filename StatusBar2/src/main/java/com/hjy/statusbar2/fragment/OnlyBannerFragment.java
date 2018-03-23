package com.hjy.statusbar2.fragment;

import android.os.Build;
import android.view.View;

import com.hjy.statusbar2.R;
import com.hjy.statusbar2.ui.Scene4Activity;

/**
 * <pre>
 *     author : HJY
 *     time   : 2018/03/15/16:38
 *     desc   : 文件描述
 *     version: 当前版本号
 * </pre>
 */
public class OnlyBannerFragment extends BaseFragment {
    private Scene4Activity mActivity;


    @Override
    protected void configFragmentView(View view) {
        mActivity = ((Scene4Activity) getActivity());
        mActivity.mToolbar.setVisibility(View.GONE);
        mActivity.mStatusBarView.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            mActivity.getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mActivity.mToolbar.setVisibility(View.GONE);
        mActivity.mStatusBarView.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            mActivity.getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE);
        mActivity.mStatusBarView.setVisibility(View.GONE);
    }

    @Override
    public int getFragmentView() {
        return R.layout.fragment_no_have_tool;
    }

    @Override
    public String getFragmentTitle() {
        return "no";
    }
}
