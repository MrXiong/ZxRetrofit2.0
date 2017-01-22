package com.zx.rxjava;

import android.app.Application;
import android.content.Context;


/**
 * Created by Administrator on 2017/1/18.
 */
public class IApplication extends Application {


/*    @Query，@QueryMap，@Field，@FieldMap，@FormUrlEncoded，@Path，@Url
    这七种注解应该是最常用的了。
    https://api.douban.com/v2/movie/top250?start=0&count=2
    */
private static final String BASE_URL = "https://api.douban.com";
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
