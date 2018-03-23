package com.hjy.statusbar2.fragment;

import android.os.Build;
import android.view.View;

import com.hjy.statusbar2.R;
import com.hjy.statusbar2.ui.Scene4Activity;

/**
 * <pre>
 *     author : HJY
 *     time   : 2018/03/15/16:37
 *     desc   : 文件描述
 *     version: 当前版本号
 * </pre>
 */
public class ToolBarStatusDifColorFragment extends BaseFragment {
    private Scene4Activity mActivity;

    @Override
    protected void configFragmentView(View view) {
        mActivity = ((Scene4Activity) getActivity());
        mActivity.mToolbar.setVisibility(View.VISIBLE);
        mActivity.mToolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        mActivity.mStatusBarView.setVisibility(View.VISIBLE);
        mActivity.mStatusBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mActivity.mToolbar.setVisibility(View.VISIBLE);
        mActivity.mToolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        mActivity.mStatusBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        mActivity.mStatusBarView.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    public int getFragmentView() {
        return R.layout.fragment_no_have_tool;
    }

    @Override
    public String getFragmentTitle() {
        return "ToolBarStatusDifColorFragment";
    }

}
