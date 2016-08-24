package com.cfox.db_ormlitehelper.dao;

import com.cfox.db_ormlitehelper.AppApplication;
import com.cfox.db_ormlitehelper.helper.MyDbHelper;
import com.cfox.ormlitelib.dao.OrmDaoUtils;
import com.cfox.ormlitelib.helper.OrmDatabaseHelper;

import java.sql.SQLException;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : DB_OrmLiteHelper
 * <br/>PACKAGE_NAME : com.cfox.db_ormlitehelper.dao
 * <br/>AUTHOR : Machao
 * <br/>DATA : 2016/8/24 0024
 * <br/>TIME : 14:10
 * <br/>MSG :
 * <br/>************************************************
 */
public class MyDao extends OrmDaoUtils {
    public MyDao(Class cls) throws SQLException {
        super(cls);
    }

    @Override
    protected OrmDatabaseHelper getHelper() {
        return new MyDbHelper(AppApplication.sCtx);
    }
}
