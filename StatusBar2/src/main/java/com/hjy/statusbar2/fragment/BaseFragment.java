package com.hjy.statusbar2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjy.statusbar2.utils.FragmentUtil;

/**
 * <pre>
 *     author : HJY
 *     time   : 2018/03/15/15:57
 *     desc   : 文件描述
 *     version: 当前版本号
 * </pre>
 */
public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    private ViewGroup mRootView;

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, getClass().getSimpleName() + "：onHiddenChanged hidden == " + hidden);

        if (!hidden && FragmentUtil.judgeGetActivityCanUse(this)) {
            getActivity().setTitle(getFragmentTitle());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, getClass().getSimpleName() + "：onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getClass().getSimpleName() + "：onCreate");
    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + "：onCreateView");
        mRootView = (ViewGroup) inflater.inflate(getFragmentView(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + "：onViewCreated");
        configFragmentView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + "：onActivityCreated");
        getActivity().setTitle(getFragmentTitle());
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + "：onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + "：onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + "：onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + "：onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + "：onDestroyView");
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + "：onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + "：onDetach");
        super.onDetach();
    }

    protected abstract void configFragmentView(View view);

    public abstract int getFragmentView();

    public abstract String getFragmentTitle();

}
