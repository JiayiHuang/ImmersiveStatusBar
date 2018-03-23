package com.hjy.statusbar2.fragment;

import android.content.Context;
import android.os.Build;
import android.view.View;

import com.hjy.statusbar2.R;
import com.hjy.statusbar2.ui.Scene4Activity;

/**
 * <pre>
 *     author : HJY
 *     time   : 2018/03/15/17:21
 *     desc   : 文件描述
 *     version: 当前版本号
 * </pre>
 */
public class FlexibleFragment extends BaseFragment {
    public static final String INDEX_FRAGMENT = "INDEX_FRAGMENT";
    private Scene4Activity mActivity;
    private int index = -1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        index = getArguments().getInt(INDEX_FRAGMENT);
    }

    @Override
    protected void configFragmentView(View view) {
        mActivity = ((Scene4Activity) getActivity());
        switchStatusBar(index);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        switchStatusBar(index);
    }

    private void switchStatusBar(int index) {
        switch (index) {
            case 0:
                switch2WithToolBar();
                break;
            case 1:
                switch2NoTitleBar();
                break;
            case 2:
                switch2StatusBarWithDiffTextColor();
                break;
            case 3:
                switch2OnlyBanner();
                break;
        }
        // 恢复默认statusBar文字颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE);
        }
    }


    private void switch2OnlyBanner() {
        mActivity.mToolbar.setVisibility(View.GONE);
        mActivity.mStatusBarView.setVisibility(View.GONE);
    }

    private void switch2StatusBarWithDiffTextColor() {
        mActivity.mToolbar.setVisibility(View.VISIBLE);
        mActivity.mToolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        mActivity.mStatusBarView.setVisibility(View.VISIBLE);
        mActivity.mStatusBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    private void switch2NoTitleBar() {
        mActivity.mToolbar.setVisibility(View.GONE);//设置ToolBar消失
        mActivity.mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        mActivity.mStatusBarView.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        mActivity.mStatusBarView.setVisibility(View.VISIBLE);
    }

    private void switch2WithToolBar() {
        mActivity.mToolbar.setVisibility(View.VISIBLE);
        mActivity.mToolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        mActivity.mStatusBarView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
        mActivity.mStatusBarView.setVisibility(View.VISIBLE);
    }

    @Override
    public int getFragmentView() {
        return R.layout.fragment_no_have_tool;
    }

    @Override
    public String getFragmentTitle() {
        return "INDEX - " + index;
    }
}
