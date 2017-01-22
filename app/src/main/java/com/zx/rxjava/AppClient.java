package com.zx.rxjava;

import android.content.Context;

import com.zx.rxjava.env.ApiEnv;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/1/19.
 */
public class AppClient {
    public static Retrofit retrofit = null;
    private static  Context mContext;

    public static Retrofit retrofit(Context context) {
        mContext = context;
        if (retrofit == null) {
            RetrofitFactory factory = new RetrofitFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            /**
             *设置缓存，代码略
             */
            Cache cache = factory.initCache(context);
            Interceptor interceptor = factory.initCacheInterceptor(context);
            builder.cache(cache).addInterceptor(interceptor);

            /**
             *  公共参数，代码略
             */
            builder.addInterceptor(factory.initQuery(context));

            /**
             * 设置头，代码略
             */
            //builder.addInterceptor(factory.initHeader());

            /**
             * Log信息拦截器，代码略
             */
            builder.addInterceptor(factory.initLogInterceptor());

            /**
             * 设置cookie，代码略
             */
           /* CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            builder.cookieJar(new JavaNetCookieJar(cookieManager));*/


            /**
             * 设置超时和重连，代码略
             */

            //设置超时
            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);
            //以上设置结束，才能build(),不然设置白搭
            OkHttpClient okHttpClient = builder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiEnv.getURL())
                            //增加返回值为String的支持
                    .addConverterFactory(ScalarsConverterFactory.create())
                            //增加返回值为Gson的支持(以实体类返回)
                    //.addConverterFactory(GsonConverterFactory.create())
                    //.addConverterFactory(JacksonConverterFactory.create())
                    //.addConverterFactory(MoshiConverterFactory.create())
                    .addConverterFactory(FastJsonConverterFactory.create())
                            //增加返回值为Oservable<T>的支持
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}