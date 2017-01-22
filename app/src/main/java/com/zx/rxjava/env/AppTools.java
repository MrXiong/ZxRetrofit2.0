package com.zx.rxjava.env;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;


import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * Created by zx on 2016/5/10.
 */
public class AppTools {
    private static final String TAG = AppTools.class.getSimpleName();
    public static String INTERFACE_VERSION_NEW = "2.0";
    public static String INTERFACE_VERSION_OLD = "1.0";
    public static String getAppVersion(Context ctx) {
        String appVersion;
        // 获取APP版本信息
        PackageManager packageManager = ctx.getPackageManager();
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
            appVersion = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            appVersion = "2.0";
        }

        return appVersion;
    }
    public static int getVersionCode(Context ctx) {
        int versionCode = -1;
        PackageManager packageManager = ctx.getPackageManager();
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return versionCode;
    }

    public static String getInterfaceAppVersion() {
        String appVersion = "2.0";

        return appVersion;
    }

    public static String getClient(Context ctx) {
        String client = "supercode," + getAppVersion(ctx) + ",android";
        return client;
    }
    public static String getToken(Context ctx) {
       // return PreferencesUtils.getString(ctx, GetLoginRespons.TOKEN);
        return "0dc74517-ecaf-4451-b126-9f97fbf06449";
    }
    public static int dp2px(Context context,int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    /*
* check the app is installed
*/
    public static boolean isAviliblePackage( Context context, String packageName )
    {
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for ( int i = 0; i < pinfo.size(); i++ )
        {
            if(pinfo.get(i).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    public static void install(File apkFile, Context context){
        String[] command1 = {"chmod", "777", apkFile.getParentFile().getAbsolutePath()};
        String[] command2 = {"chmod", "777", apkFile.getAbsolutePath()};
        try {
            ProcessBuilder builder = new ProcessBuilder(command1);
            builder.start();
            builder = new ProcessBuilder(command2);
            builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(install);
    }

    public static boolean isApkDebugable(Context ctx) {
        try {
            PackageInfo pkginfo;
            pkginfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            if (pkginfo != null) {
                ApplicationInfo info = pkginfo.applicationInfo;
                return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
            }
        } catch (Exception e) {
            ;
        }
        return false;
    }


    public static boolean isTestApk(Context ctx) {
        if (isApkDebugable(ctx)) {
            return true;
        }
        if (!TextUtils.isEmpty(getAppVersion(ctx)) && getAppVersion(ctx).toLowerCase().contains("beta")) {
            return true;
        }
        return false;
    }

    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
}
