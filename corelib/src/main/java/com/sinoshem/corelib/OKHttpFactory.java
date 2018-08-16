package com.sinoshem.corelib;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * author: jackydu
 * date: on 16/5/16 16:43
 * description:
 */
public enum OKHttpFactory {
    INSTANCE;

    @SuppressWarnings("ImmutableEnumChecker") private final OkHttpClient okHttpClient;

    private static final int TIMEOUT_READ = 25;
    private static final int TIMEOUT_CONNECTION = 25;

    OKHttpFactory() {

//        File mC = StorageUtil.getUnderCacheDirectory("mC", false);
//        Cache cache = new Cache(mC, 10 * 1024 * 1024);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                //添加UA
                .addInterceptor(new UserAgentInterceptor("ua"))

                //添加缓存
//                .cache(cache)
                .addInterceptor(new CachedControlInterceptor())
                .addNetworkInterceptor(new CachedControlInterceptor())
                //失败重连
                .retryOnConnectionFailure(true)

                //time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(
                            HttpLoggingInterceptor.Level.BODY));
        }

        okHttpClient = builder.build();
    }


    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
