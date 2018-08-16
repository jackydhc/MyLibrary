package com.sinoshem.corelib;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @author jackydu
 * @date 2018/8/15
 */
public class CoreApplication extends MultiDexApplication {

    public static CoreApplication instance;
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        refWatcher = LeakCanary.install(this);
    }
    @Nullable
    public static RefWatcher getRefWatcher() {
        return instance.refWatcher;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
