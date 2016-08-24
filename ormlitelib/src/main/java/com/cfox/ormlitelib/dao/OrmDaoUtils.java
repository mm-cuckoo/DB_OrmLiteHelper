package com.cfox.ormlitelib.dao;

import android.content.ContentValues;

import com.cfox.ormlitelib.helper.OrmDatabaseHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : DB_OrmLiteHelper
 * <br/>PACKAGE_NAME : com.cfox.ormlitelib.dao
 * <br/>AUTHOR : Machao
 * <br/>DATA : 2016/8/24 0024
 * <br/>TIME : 11:39
 * <br/>MSG :
 * <br/>************************************************
 */
public abstract class OrmDaoUtils<T> {

    private static final int INSERT = 1;
    private static final int UPDATE = 2;
    private static final int DELETE = 3;


    private OrmDatabaseHelper mHelper;
    private Dao mDao;

    protected abstract OrmDatabaseHelper getHelper();


    public OrmDaoUtils(Class cls) throws SQLException {
        if (mHelper == null) {
            mHelper = getHelper();
        }
        this.mDao = mHelper.getDao(cls);
    }

    public T queryById(Integer objId) throws SQLException {
        return (T) mDao.queryForId(objId);
    }

    public List<T> queryAll() throws SQLException {
        return mDao.queryForAll();
    }

    public QueryBuilder queryBuilder(Map<String, Object> whereMap) throws SQLException {

        QueryBuilder<T, Integer> queryBuilder = mDao.queryBuilder();
        if (whereMap != null && !whereMap.isEmpty()) {
            Where<T, Integer> wheres = queryBuilder.where();
            Set<String> keys = whereMap.keySet();
            ArrayList<String> keyss = new ArrayList<String>();
            keyss.addAll(keys);
            for (int i = 0; i < keyss.size(); i++) {
                if (i == 0) {
                    wheres.eq(keyss.get(i), whereMap.get(keyss.get(i)));
                } else {
                    wheres.and().eq(keyss.get(i), whereMap.get(keyss.get(i)));
                }
            }
        }

        return queryBuilder;
    }

    public QueryBuilder queryOrderBy(String orderColumn ,boolean isASC) throws SQLException {
        return queryOrderBy(null,orderColumn,isASC);
    }

    /**
     *
     * @param isASC 参数 false 表示降序，true 表示升序。
     * @return
     * @throws SQLException
     */
    public QueryBuilder queryOrderBy(QueryBuilder queryBuilder,String orderColumn ,boolean isASC) throws SQLException {

        if(queryBuilder == null){
            queryBuilder = mDao.queryBuilder();
        }

        if(orderColumn != null){
            queryBuilder.orderBy(orderColumn,isASC);
        }
        return queryBuilder;
    }

    public List<T> query(QueryBuilder builder) throws SQLException{
        PreparedQuery<T> preparedQuery = builder.prepare();
        return mDao.query(preparedQuery);
    }


    /**
     * 新增数据
     *
     * @param obj
     * @throws SQLException
     */
    public int insert(T obj) throws SQLException {
        return mDao.create(obj);
    }

    public int insert(Collection<T> objs) throws SQLException {
        return mDao.create(objs);
    }

    public boolean insertInTransaction(final List<T> objs) throws SQLException {
        return baseTransaction(INSERT, objs);
    }


    /**
     * 删除数据
     *
     * @param obj
     * @throws SQLException
     */
    public void delete(T obj) throws SQLException {
        mDao.delete(obj);
    }

    public int delete(Collection<T> objs) throws SQLException {
        return mDao.delete(objs);
    }

    public void deleteById(Integer objId) throws SQLException {
        mDao.deleteById(objId);
    }

    public boolean deleteInTransaction(final List<T> objs) throws SQLException {
        return baseTransaction(DELETE, objs);
    }

    public boolean deleteInTransactionById(final List<Integer> objIds) throws SQLException {
        Boolean b = TransactionManager.callInTransaction(mHelper.getConnectionSource(), new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    for (Integer objId : objIds) {
                        mDao.deleteById(objId);
                    }
                } catch (SQLException e) {
                    return false;
                }
                return true;
            }
        });
        return b;
    }


    public int update(T obj) throws SQLException {
        return mDao.update(obj);
    }

    public int update(Map<String, Object> whereMap, String columnName, Object columnValue) throws SQLException {
        Map<String, Object> updateValue = new HashMap<String, Object>();
        updateValue.put(columnName, columnValue);
        return this.update(whereMap, updateValue);

    }


    public int update(Map<String, Object> whereMap, Map<String, Object> updateValue) throws SQLException {
        UpdateBuilder updateBuilder = mDao.updateBuilder();
        if (updateValue != null && !updateValue.isEmpty()) {
            Set<String> valueSet = updateValue.keySet();
            for (String valueKey : valueSet) {
                updateBuilder.updateColumnValue(valueKey, updateValue.get(valueKey));
            }
        } else {
            return -1;
        }

        if (whereMap != null && !whereMap.isEmpty()) {
            Where<T, Long> where = updateBuilder.where();
            Set<String> whereKeySet = whereMap.keySet();
            List<String> keys = new ArrayList<String>();
            keys.addAll(whereKeySet);
            for (int i = 0; i < keys.size(); i++) {
                if (i == 0) {
                    where.eq(keys.get(i), whereMap.get(keys.get(i)));
                } else {
                    where.and().eq(keys.get(i), whereMap.get(keys.get(i)));
                }
            }
        }
        return mDao.update(updateBuilder.prepare());
    }

    public Dao.CreateOrUpdateStatus createOrUpdate(T obj) throws SQLException {
        return mDao.createOrUpdate(obj);
    }

    public Dao.CreateOrUpdateStatus createOrUpdate(Collection<T> objs) throws SQLException {
        return mDao.createOrUpdate(objs);
    }

    public boolean updateInTransaction(final List<T> objs) throws SQLException {
        return baseTransaction(UPDATE, objs);
    }

    private boolean baseTransaction(final int type, final List<T> objs) throws SQLException {
        Boolean b = TransactionManager.callInTransaction(mHelper.getConnectionSource(), new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {

                    switch (type) {
                        case INSERT:
                        case UPDATE:
                            for (T obj : objs) {
                                mDao.createOrUpdate(obj);
                            }
                            break;

                        case DELETE:
                            for (T obj : objs) {
                                mDao.delete(obj);
                            }
                            break;
                    }
                } catch (SQLException e) {
                    return false;
                }
                return true;
            }
        });
        return b;
    }


}
