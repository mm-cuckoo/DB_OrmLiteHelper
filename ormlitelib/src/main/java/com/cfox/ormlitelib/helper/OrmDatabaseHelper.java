package com.cfox.ormlitelib.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : DB_OrmLiteHelper
 * <br/>PACKAGE_NAME : com.cfox.ormlitelib.helper
 * <br/>AUTHOR : Machao
 * <br/>DATA : 2016/8/24 0024
 * <br/>TIME : 11:37
 * <br/>MSG :
 * <br/>************************************************
 */
public abstract class OrmDatabaseHelper<T> extends OrmLiteSqliteOpenHelper {

    private static OrmDatabaseHelper sHelper;

    private static final String DB_NAME = "ormlite_db.db";

    private static final int DB_VERSION = 1;
    private static String DATABASE_PATH = null;

    private static String DATABASE_PATH_JOURN = null;

    private Context mContext;

    private List<Class<T>> DBtables = new ArrayList<Class<T>>();

    public OrmDatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {
            DBtables.clear();
            createTables(DBtables);
            for (Class table : DBtables) {
                TableUtils.createTable(connectionSource, table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, final ConnectionSource connectionSource, int i, int i1) {
        try {
            TransactionManager.callInTransaction(connectionSource, new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    DBtables.clear();
                    updateTables(DBtables);
                    try {
                        for (Class table : DBtables) {
                            TableUtils.dropTable(connectionSource, table, true);
                            TableUtils.createTable(connectionSource, table);
                        }
                    }catch (SQLException e){
                        return false;
                    }
                    return true;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract void createTables(List<Class<T>> tables);

    public abstract void updateTables(List<Class<T>> tables);

    public abstract OrmDatabaseHelper getHelper(Context context);

}

