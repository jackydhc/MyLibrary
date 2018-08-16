package com.sinoshem.corelib.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinoshem.corelibrary.net.CoreApplication;
import com.sinoshem.corelibrary.net.mvp.MvpView;
import com.squareup.leakcanary.RefWatcher;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * @author jackydu
 * @date 2018/8/15
 */
public abstract class BaseFragment extends RxFragment implements MvpView{

    /**
     * 绑定到当前的attach的activity上.可强转
     */
    public Context mContext;
    protected View view;

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) view = inflater.inflate(provideLayoutId(), container, false);
        ButterKnife.bind(this, view);
        initOnCreateView();
        return view;
    }

    public abstract int provideLayoutId();

    protected abstract void initOnCreateView();

    @Override public void onResume() {
        super.onResume();
    }

    @Override public void onPause() {
        super.onPause();
    }

    @Override public void onDestroyView() {
        dismissCustomDialog();
        RefWatcher refWatcher = CoreApplication.getRefWatcher();
        if (refWatcher != null) {
            refWatcher.watch(this);
        }
        super.onDestroyView();
    }

    /**
     * 除特殊情况，存在于指定Fragment中的自定义Dialog，必须在此方法中进行关闭
     */
    protected void dismissCustomDialog() {

    }

    @Override public boolean isAlive() {
        //noinspection SimplifiableIfStatement
        if (getActivity() != null && !getActivity().isFinishing()) {
            return true;
        }
        return isAdded();
    }
}
