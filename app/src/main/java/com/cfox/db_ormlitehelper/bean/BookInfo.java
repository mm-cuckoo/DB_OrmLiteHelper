package com.cfox.db_ormlitehelper.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : DB_OrmLiteHelper
 * <br/>PACKAGE_NAME : com.cfox.db_ormlitehelper.bean
 * <br/>AUTHOR : Machao
 * <br/>DATA : 2016/8/24 0024
 * <br/>TIME : 14:10
 * <br/>MSG :
 * <br/>************************************************
 */
@DatabaseTable(tableName = "book_info")
public class BookInfo {

    @DatabaseField(generatedId = true,columnName = "book_id")
    private int bookId;

    @DatabaseField(columnName = "book_name")
    private String bookName;

    public BookInfo(){}
    public BookInfo(String bookName){
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
