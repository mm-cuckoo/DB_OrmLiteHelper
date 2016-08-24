package com.cfox.db_ormlitehelper.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.cfox.db_ormlitehelper.bean.BookInfo;
import com.cfox.db_ormlitehelper.bean.UserInfo;
import com.cfox.ormlitelib.helper.OrmDatabaseHelper;

import java.util.List;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : DB_OrmLiteHelper
 * <br/>PACKAGE_NAME : com.cfox.db_ormlitehelper.helper
 * <br/>AUTHOR : Machao
 * <br/>DATA : 2016/8/24 0024
 * <br/>TIME : 14:07
 * <br/>MSG :
 * <br/>************************************************
 */
public class MyDbHelper extends OrmDatabaseHelper {

    private static final String DEF_DB_NAME = "def_db";
    private static final int DB_VERSION = 2;


    public MyDbHelper(Context context) {
        super(context, DEF_DB_NAME, null, DB_VERSION);
    }

    @Override
    public void createTables(List tables) {
        tables.add(UserInfo.class);
        tables.add(BookInfo.class);
    }

    @Override
    public void updateTables(List tables) {
        tables.add(UserInfo.class);
        tables.add(BookInfo.class);
    }
}
