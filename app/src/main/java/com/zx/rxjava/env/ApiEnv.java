package com.zx.rxjava.env;

public class ApiEnv {

    public static final int PREPARE = -1;
    public static final int ONLINE = 0;
    public static final int DEBUG = 1;

    private static final String URL_DEBUG = "http://122.224.171.198/interface/";
//    private static final String URL_DEBUG = "http://122.224.171.198/interface/DataService.aspx";
    private static final String URL_ONLINE = "http://interface.app315.net/";
    //private static final String URL_ONLINE = "http://appinterface.app315.net/JsonDataService.ashx";
    private static final String URL_PREPARE = "http://tinterface.app315.net/DataService.aspx/";
    private static int env = ONLINE;

    public static String getURL() {
        if (env == ONLINE) {
            return URL_ONLINE;
        } else if (env == PREPARE) {
            return URL_PREPARE;
        } else {
            return URL_DEBUG;
        }
    }

    public static int getEnv() {
        return env;
    }

    public static void setEnv(int env) {
        ApiEnv.env = env;
    }
}
