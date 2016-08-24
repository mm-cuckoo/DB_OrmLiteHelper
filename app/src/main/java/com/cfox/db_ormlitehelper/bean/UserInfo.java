package com.cfox.db_ormlitehelper.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : DB_OrmLiteHelper
 * <br/>PACKAGE_NAME : com.cfox.db_ormlitehelper.bean
 * <br/>AUTHOR : Machao
 * <br/>DATA : 2016/8/24 0024
 * <br/>TIME : 14:09
 * <br/>MSG :
 * <br/>************************************************
 */
@DatabaseTable(tableName = "user_info")
public class UserInfo {

    @DatabaseField(generatedId = true , columnName = "user_id")
    private int userId;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "addr")
    private String addr;

    public UserInfo(){}

    public UserInfo(String name,String addr){
        this.name = name;
        this.addr = addr;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
