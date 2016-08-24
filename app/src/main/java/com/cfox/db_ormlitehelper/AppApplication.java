package com.cfox.db_ormlitehelper;

import android.app.Application;
import android.content.Context;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : DB_OrmLiteHelper
 * <br/>PACKAGE_NAME : com.cfox.db_ormlitehelper
 * <br/>AUTHOR : Machao
 * <br/>DATA : 2016/8/24 0024
 * <br/>TIME : 14:10
 * <br/>MSG :
 * <br/>************************************************
 */
public class AppApplication extends Application {

    public static Context sCtx;
    @Override
    public void onCreate() {
        super.onCreate();
        this.sCtx = this;
    }
}
