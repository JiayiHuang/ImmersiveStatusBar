package com.hjy.statusbar2.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.hjy.statusbar2.R;
import com.hjy.statusbar2.fragment.FlexibleFragment;
import com.hjy.statusbar2.utils.ScreenUtil;

import java.util.ArrayList;

/**
 * <pre>
 *     author : HJY
 *     time   : 2018/03/15/15:07
 *     desc   : 不同Fragment中对StatusBar的处理不一样
 *     version: 19+
 * </pre>
 */
public class Scene4Activity extends AppCompatActivity {

    public Toolbar mToolbar;
    private TextView mTitle;
    private ArrayList<Fragment> mFragments;
    public View mStatusBarView;
    private FragmentManager mSupportFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mLastFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene4);

        mToolbar = findViewById(R.id.scene4_toolbar);
        mTitle = findViewById(R.id.scene4_title);
        // 直接使用setSupportActionBar 需要将原有的ActionBar去掉，可以在主题中设置
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mFragments = new ArrayList<>();
        Bundle bundle1 = new Bundle();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = new Bundle();
        Bundle bundle4 = new Bundle();
        FlexibleFragment fragment1 = new FlexibleFragment();
        bundle1.putInt(FlexibleFragment.INDEX_FRAGMENT, 0);
        fragment1.setArguments(bundle1);
        FlexibleFragment fragment2 = new FlexibleFragment();
        bundle2.putInt(FlexibleFragment.INDEX_FRAGMENT, 1);
        fragment2.setArguments(bundle2);
        FlexibleFragment fragment3 = new FlexibleFragment();
        bundle3.putInt(FlexibleFragment.INDEX_FRAGMENT, 2);
        fragment3.setArguments(bundle3);
        FlexibleFragment fragment4 = new FlexibleFragment();
        bundle4.putInt(FlexibleFragment.INDEX_FRAGMENT, 3);
        fragment4.setArguments(bundle4);

        mFragments.add(fragment1);
        mFragments.add(fragment2);
        mFragments.add(fragment3);
        mFragments.add(fragment4);
/*
        HaveToolBarFragment haveToolBarFragment = new HaveToolBarFragment();
        NoToolBarStatusFragment noToolBarStatusFragment = new NoToolBarStatusFragment();
        ToolBarStatusDifColorFragment toolBarStatusDifColorFragment = new ToolBarStatusDifColorFragment();
        OnlyBannerFragment onlyBannerFragment = new OnlyBannerFragment();

        mFragments.add(haveToolBarFragment);
        mFragments.add(noToolBarStatusFragment);
        mFragments.add(toolBarStatusDifColorFragment);
        mFragments.add(onlyBannerFragment);
*/

        mSupportFragmentManager = getSupportFragmentManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            addStatusBar();
        }
        selectFragment(0);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_have_tool_bar:
                selectFragment(0);
                break;
            case R.id.bt_no_have_toolbar:
                selectFragment(1);
                break;
            case R.id.bt_have_toolbar_change_color:
                selectFragment(2);
                break;
            case R.id.bt_only_banner:
                selectFragment(3);
                break;
        }
    }

    private void selectFragment(int index) {
        if (mFragments != null && mFragments.size() > 0) {
            mFragmentTransaction = mSupportFragmentManager.beginTransaction();
            Fragment baseFragment = mFragments.get(index);
            if (mLastFragment != null) {
                mFragmentTransaction.hide(mLastFragment);
            }
            if (mFragmentTransaction != null) {
                if (baseFragment.isAdded()) {
                    mFragmentTransaction.show(baseFragment);
                } else {
                    mFragmentTransaction.add(R.id.scene4_container, baseFragment);
                }
            }
            assert mFragmentTransaction != null;
            mFragmentTransaction.commitAllowingStateLoss();
            mLastFragment = baseFragment;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void addStatusBar() {
        //条件状态栏透明，要不然不会起作用
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (mStatusBarView == null) {
            mStatusBarView = new View(this);
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            int statusBarHeight = ScreenUtil.getStatusBarHeight(this);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(screenWidth, statusBarHeight);
            mStatusBarView.setLayoutParams(params);
            mStatusBarView.requestLayout();

            //获取根布局
            ViewGroup systemContent = findViewById(android.R.id.content);
            ViewGroup userContent = (ViewGroup) systemContent.getChildAt(0);
            userContent.setFitsSystemWindows(false);
            userContent.addView(mStatusBarView, 0);
        }
    }
}
