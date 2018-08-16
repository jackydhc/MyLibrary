package com.sinoshem.corelib;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * created by jackydu
 */
public enum SinoRetrofit  {
    INSTANCE(1);

    @SuppressWarnings("ImmutableEnumChecker")
    private final Retrofit retrofit;

    SinoRetrofit(int type) {
        retrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(OKHttpFactory.INSTANCE.getOkHttpClient())

                //baseUrl
                .baseUrl("BASEURL")

                //gson转化器
                .addConverterFactory(GsonConverterFactory.create())

                //Rx转换
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                //创建
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public <T> T create(Class<T> tClass) {
        return retrofit.create(tClass);
    }

}
