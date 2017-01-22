package com.zx.rxjava;

import android.content.Context;

import com.zx.rxjava.env.AppTools;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2017/1/19.
 */
public class RetrofitFactory {
    public static final String METHOD = "function";
    public static final String CLIENT = "client";
    public static final String VERSION = "version";
    public static final String TOKEN = "Token";

    //设置缓存
    public Cache initCache(Context context) {
        File cacheFile = new File(context.getExternalCacheDir(), "WuXiaolongCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        return cache;
    }

    //设置缓存Interceptor
    public Interceptor initCacheInterceptor(final Context context) {
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkTools.isConnected(context)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetWorkTools.isConnected(context)) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("WuXiaolong")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("nyn")
                            .build();
                }
                return response;
            }
        };
        return cacheInterceptor;
    }

    //公共参数
    public Interceptor initQuery(final Context context) {
        Interceptor addQueryParameterInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request;
                String method = originalRequest.method();
                Headers headers = originalRequest.headers();
                HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                        // Provide your custom parameter here
                        .addQueryParameter(CLIENT, AppTools.getClient(context))
                        .addQueryParameter(VERSION, AppTools.getAppVersion(context))
                        .addQueryParameter(TOKEN, AppTools.getToken(context))
                        .build();
                request = originalRequest.newBuilder().url(modifiedUrl).build();
                return chain.proceed(request);
            }
        };
        return addQueryParameterInterceptor;
    }

    //设置头
    public Interceptor initHeader() {
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .header("AppType", "TPOS")
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        return headerInterceptor;
    }

    //设置日志
    public HttpLoggingInterceptor initLogInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = null;
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
        }
        return loggingInterceptor;

    }


}
